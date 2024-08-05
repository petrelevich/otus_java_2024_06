package ru.otus.aop.instrumentation.changer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S1172")
public class Agent {
    private static final Logger logger = LoggerFactory.getLogger(Agent.class);

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
                if (className.equals("ru/otus/aop/instrumentation/changer/AnyClass")) {
                    return changeMethod(classfileBuffer);
                }
                return classfileBuffer;
            }
        });
    }

    private static byte[] changeMethod(byte[] originalClass) {
        var cr = new ClassReader(originalClass);
        var cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(
                    int access, String name, String descriptor, String signature, String[] exceptions) {
                var methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("summator")) {
                    return new ChangeMethodVisitor(methodVisitor, access, name, descriptor);
                } else {
                    return methodVisitor;
                }
            }
        };
        cr.accept(cv, Opcodes.ASM7);

        byte[] finalClass = cw.toByteArray();

        try (OutputStream fos = new FileOutputStream("changer.class")) {
            fos.write(finalClass);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return finalClass;
    }

    private static class ChangeMethodVisitor extends AdviceAdapter {
        ChangeMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(Opcodes.ASM7, methodVisitor, access, name, descriptor);
        }

        @Override
        public void visitInsn(final int opcode) {
            if (IADD == opcode) {
                logger.info("replace IADD to ISUB");
                super.visitInsn(ISUB);
            } else {
                super.visitInsn(opcode);
            }
        }

        @Override
        public void visitLocalVariable(
                final String name,
                final String descriptor,
                final String signature,
                final Label start,
                final Label end,
                final int index) {
            logger.info("visited name:{}, descriptor:{}, signature:{}, index:{}", name, descriptor, signature, index);
            super.visitLocalVariable(name, descriptor, signature, start, end, index);
        }
    }
}
