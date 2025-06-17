package com.retail.app.util;

import java.util.UUID;

public class OrderIDGenerator {
    public static String generate() {
        return "OD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
