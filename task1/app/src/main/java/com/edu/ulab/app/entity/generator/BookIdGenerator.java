package com.edu.ulab.app.entity.generator;

import java.util.UUID;

public class BookIdGenerator {
    private static long currentId = 0;

    public static long getId() {
        return currentId++;
    }

}
