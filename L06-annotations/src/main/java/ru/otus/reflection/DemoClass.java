package ru.otus.reflection;

@SuppressWarnings({"java:S106", "java:S1104", "java:S1144"})
public class DemoClass {

    public int publicFieldForDemo;

    private String valuePrivate = "initValue";

    public DemoClass(String valuePrivate) {
        this.valuePrivate = valuePrivate;
    }

    public String getValuePrivate() {
        return valuePrivate;
    }

    public void setValuePrivate(String valuePrivate) {
        this.valuePrivate = valuePrivate;
    }

    private void privateMethod() {
        System.out.println("privateMethod executed");
    }

    @Override
    @SimpleAnnotation
    public String toString() {
        return "DemoClass{" + "publicFieldForDemo=" + publicFieldForDemo + ", value='" + valuePrivate + '\'' + '}';
    }
}
