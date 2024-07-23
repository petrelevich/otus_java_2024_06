package ru.otus.reflection;

@SuppressWarnings({"java:S106", "java:S1481", "java:S1854"})
public class ReflectionIntro {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<ReflectionIntro> ff = ReflectionIntro.class;

        Class<?> classString = Class.forName("java.lang.String");
        System.out.println("simpleName:" + classString.getSimpleName());
        System.out.println("canonicalName:" + classString.getCanonicalName());

        Class<Integer> classInt = int.class;
        System.out.println("TypeName int:" + classInt.getTypeName());

        Class<Integer> classInteger = Integer.class;
        System.out.println("TypeName integer:" + classInteger.getTypeName());
    }
}
