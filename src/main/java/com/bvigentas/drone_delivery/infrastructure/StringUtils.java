package com.bvigentas.drone_delivery.infrastructure;

public class StringUtils {

    public static String cleanSpecialCharacter(String input) {
        return input.replaceAll("[\\[\\]]", "");
    }

}
