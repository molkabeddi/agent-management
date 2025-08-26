package com.example.agent_management.Utils;


import java.security.SecureRandom;

public final class UserIdGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS  = "123456789";
    private static final SecureRandom RND = new SecureRandom();

    private UserIdGenerator() {}

    public static String generate() {
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 2; i++) {
            sb.append(LETTERS.charAt(RND.nextInt(LETTERS.length())));
        }
        for (int i = 0; i < 5; i++) {
            sb.append(DIGITS.charAt(RND.nextInt(DIGITS.length())));
        }
        return sb.toString();
    }
}
