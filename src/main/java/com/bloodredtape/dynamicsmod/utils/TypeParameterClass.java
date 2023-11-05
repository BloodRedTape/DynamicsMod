package com.bloodredtape.dynamicsmod.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeParameterClass {
    private static class Helper<T>{
        public Class<?> getActualClass() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                if (typeArguments.length > 0) {
                    Type typeArgument = typeArguments[0];
                    if (typeArgument instanceof Class) {
                        return (Class<?>) typeArgument;
                    }
                }
            }
            return null;
        }
    }
    public static <T> Class<T> getGenericClass() {
        return (Class<T>)(new Helper<T>().getActualClass());
    }
}
