package ru.otus.aop.instrumentation.proxy;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S1172")
public class Agent {
    private static final Logger logger = LoggerFactory.getLogger(Agent.class);
    private static final String STRING_FIELD_DESCRIPTOR = "(Ljava/lang/String;)V";

    private Agent() {}

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("premain");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(
                    ClassLoader loader,
                    String className,
                    Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain,
                    byte[] classfileBuffer) {
                if (className.equals("ru/otus/aop/instrumentation/proxy/MyClassImpl")) {
                    return addProxyMethod(classfileBuffer);
                }
                return classfileBuffer;
            }
        });
    }

    private static byte[] addProxyMethod(byte[] originalClass) {
        var originalMethodName = "secureAccess";
        var proxiedMethodName = "secureAccessProxied";
        var cr = new ClassReader(originalClass);
        var cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(
                    int access, String name, String descriptor, String signature, String[] exceptions) {
                if (name.equals(originalMethodName)) {
                    return super.visitMethod(access, proxiedMethodName, descriptor, signature, exceptions);
                } else {
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }
        };
        cr.accept(cv, Opcodes.ASM5);

        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, originalMethodName, STRING_FIELD_DESCRIPTOR, null, null);

        var handle = new Handle(
                H_INVOKESTATIC,
                Type.getInternalName(java.lang.invoke.StringConcatFactory.class),
                "makeConcatWithConstants",
                MethodType.methodType(
                                CallSite.class,
                                MethodHandles.Lookup.class,
                                String.class,
                                MethodType.class,
                                String.class,
                                Object[].class)
                        .toMethodDescriptorString(),
                false);

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitInvokeDynamicInsn(
                "makeConcatWithConstants", "(Ljava/lang/String;)Ljava/lang/String;", handle, "logged param:\u0001");

        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", STRING_FIELD_DESCRIPTOR, false);

        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "ru/otus/aop/instrumentation/proxy/MyClassImpl",
                proxiedMethodName,
                STRING_FIELD_DESCRIPTOR,
                false);

        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] finalClass = cw.toByteArray();

        try (OutputStream fos = new FileOutputStream("proxyASM.class")) {
            fos.write(finalClass);
        } catch (Exception e) {
            logger.error("error:", e);
        }
        return finalClass;
    }
}
