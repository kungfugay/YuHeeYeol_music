package com.mc.recommend.infra.api.llm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResponseFormat<T> {
    private final Class<T> clazz;

    public ResponseFormat(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String formatToJson() {
        Gson gson = new GsonBuilder().serializeNulls().create(); // null 값을 디폴트로 넣어줌

        // Class class에는 생성자가 있음
        // 클래스를 동적으로 만들어줌
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T instance = constructor.newInstance();
            return gson.toJson(instance)
                    .replaceAll("\"", "'")
                    .replaceAll("null", "''");
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            // 사실상 예외가 나는 건 한가지 케이스 -- 생성자가 없는 경우
            e.printStackTrace();    
            throw new RuntimeException(e);
        }
    }
}
