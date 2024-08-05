package ru.otus.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClassLoaderDemo {
    private static final Logger logger = LoggerFactory.getLogger(MyClassLoaderDemo.class);

    public static void main(String[] args)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
                    IllegalAccessException {
        new MyClassLoaderDemo().start();
    }

    private void start()
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        var loader = new MyClassLoader();
        Class<?> clazz = loader.defineClass("ru.otus.classloader.ClassForLoading");
        logger.info("methods:");
        Arrays.stream(clazz.getMethods()).forEach(method -> logger.info(method.getName()));
        Constructor<?> constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        logger.info("------");

        clazz.getMethod("action").invoke(object);
    }

    private static class MyClassLoader extends ClassLoader {
        Class<?> defineClass(String className) throws IOException {
            var file = new File(getFileName(className));
            byte[] bytecode = Files.readAllBytes(file.toPath());
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }

        String getFileName(String className) {
            return "myClass" + File.separator + className.substring(className.lastIndexOf('.') + 1) + ".class";
        }
    }
}
