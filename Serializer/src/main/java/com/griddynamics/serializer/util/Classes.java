package com.griddynamics.serializer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Classes {

    public static Set<Class<?>> find(String path) {

        final InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(path.replaceAll("[.]", "/"));
        final Set<Class<?>> classes = new HashSet<>();
        if (stream == null) {
            return classes;
        }
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            reader.lines().filter(line -> line.endsWith(".class")).map(line -> getClass(line, path)).filter(Objects::nonNull).forEach(classes::add);
        }
        catch (IOException e) {
            System.out.println("Error occurred while trying to read classes!");
            return classes;
        }
        return classes;
    }

    private static Class<?> getClass(String className, String packageName) {

        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        }
        catch (ClassNotFoundException e) {
            System.out.println("Could not get class from name " + className + " in package " + packageName);
        }
        return null;
    }

}
