package com.griddynamics.serializer.base.serializers;

import com.griddynamics.serializer.api.Serializer;
import com.griddynamics.serializer.base.Attribute;
import com.griddynamics.serializer.base.Serializable;
import com.griddynamics.serializer.util.Classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class JSONSerializer implements Serializer {

    private final static Set<Class<?>> SUPPORTED_TYPES = Set.of(boolean.class, Boolean.class, byte.class, Byte.class, short.class, Short.class,
            int.class, Integer.class, long.class, Long.class, float.class, Float.class, double.class, Double.class, String.class, char.class,
            Character.class);

    @Override
    public <T> String serialize(T value) throws IllegalArgumentException {
        final Class<?> typeClass = value.getClass();
        final StringBuilder stringBuilder = new StringBuilder();
        if (SUPPORTED_TYPES.contains(typeClass)) {
            if (typeClass == String.class) {
                return "\"" + value + "\"";
            }
            return value.toString();
        } else if (value instanceof final Object[] array) {
            stringBuilder.append("[");
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                final Object element = array[i];
                stringBuilder.append(serialize(element));
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        } else if (value instanceof final Iterable<?> iterable) {
            stringBuilder.append("[");
            final Iterator<?> iterator = iterable.iterator();
            boolean isFirst = true;
            while (iterator.hasNext()) {
                if (!isFirst) {
                    stringBuilder.append(",");
                }
                final Object element = iterator.next();
                serialize(element);
                isFirst = false;
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        } else if (value instanceof final Map<?, ?> map) {
            final Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
            stringBuilder.append("{");
            boolean isFirst = true;
            for (final Map.Entry<?, ?> entry : entries) {
                if (!isFirst) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(serialize(entry.getKey())).append(":").append(serialize(entry.getValue()));
                isFirst = false;
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
        final Optional<Annotation> optionalSerializable = Arrays.stream(typeClass.getAnnotations())
                                                                .filter(annotation -> annotation.annotationType().equals(Serializable.class))
                                                                .findAny();
        if (optionalSerializable.isEmpty()) {
            throw new IllegalArgumentException("Class of this object is not marked with Serializable Annotation " + typeClass);
        }
        final Field[] fields = Arrays.stream(typeClass.getDeclaredFields())
                                     .filter(declaredField -> !Modifier.isTransient(declaredField.getModifiers()))
                                     .toArray(Field[]::new);
        for (final Field field : fields) {
            field.setAccessible(true);
        }
        stringBuilder.append("{");
        for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final Class<?> fieldType = field.getType();
            try {
                final Object fieldValue = field.get(value);
                final Optional<Annotation> optionalAttribute = Arrays.stream(field.getAnnotations())
                                                                     .filter(annotation -> annotation.annotationType().equals(Attribute.class))
                                                                     .findAny();
                String attributeName = field.getName();
                if (optionalAttribute.isPresent()) {
                    final Attribute fieldAnnotation = (Attribute) optionalAttribute.get();
                    attributeName = fieldAnnotation.key();
                }
                if (isSupported(fieldType)) {
                    stringBuilder.append("\"")
                                 .append(attributeName)
                                 .append("\":")
                                 .append(serialize(fieldValue))
                                 .append((i + 1 < fields.length && isSupported(fields[i + 1].getType())) ? "," : "");
                } else {
                    stringBuilder.append(serialize(fieldValue));
                }
            }
            catch (IllegalAccessException e) {
                System.out.println("Could not access the value of field!");
                System.out.println("Field " + field);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private boolean isSupported(Class<?> type) {
        return SUPPORTED_TYPES.contains(type);
    }

    public Set<Class<?>> getMarkedClassesInClassPath(String classPath) {
        return Classes.find(classPath);
    }

    public Set<Class<?>> getMarkedClassesInPackage(String packageName) {
        return Classes.find(packageName);
    }

}
