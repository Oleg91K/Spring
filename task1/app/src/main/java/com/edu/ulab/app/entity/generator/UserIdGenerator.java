package com.edu.ulab.app.entity.generator;

public class UserIdGenerator {
    private static long currentId = 0;

    public static long getId() {
        return currentId++;
    }

}
