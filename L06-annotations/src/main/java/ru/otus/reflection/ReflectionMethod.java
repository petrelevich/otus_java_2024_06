package ru.otus.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings({"java:S106", "java:S125"})
public class ReflectionMethod {
    public static void main(String[] args) throws Exception {

        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name:" + clazz.getSimpleName());

        Method method = clazz.getMethod("toString");

        System.out.println("--- annotations:");
        Annotation[] annotations = method.getAnnotations();
        System.out.println(Arrays.toString(annotations));

        for (var methodR : clazz.getDeclaredMethods()) {

            System.out.println(methodR.getName());
            System.out.println(Arrays.toString(methodR.getParameterTypes()));
            System.out.println("----------");
        }

        var params = clazz.getMethod("setValuePrivate", String.class).getParameterTypes();
        System.out.println("!!!!");
        System.out.println(Arrays.toString(params));

        //        System.out.println("--- modifiers:");
        //        int modifiers = method.getModifiers();
        //        System.out.println("modifiers:" + Modifier.toString(modifiers));
        //        System.out.println("isPublic:" + Modifier.isPublic(modifiers));
        //        System.out.println("isFinal:" + Modifier.isFinal(modifiers));
        //        System.out.println("isStatic:" + Modifier.isStatic(modifiers));
        //
        //        System.out.println("--- execution:");
        //        var result = method.invoke(new DemoClass("demoVal"));
        //        System.out.println("result:" + result);
    }
}
