package org.pet.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Validator {

    public static final String MULTIPLY_SIGN = "x";
    private static final int DIMENSIONS_NUMBER = 3;

    private static final Predicate<String> isRoomDimensions = str -> {
        List<String> line = Arrays.asList(str.split(MULTIPLY_SIGN));
        int withDigits = (int) line.stream()
                .filter(numberStr -> numberStr.chars().allMatch(Character::isDigit))
                .count();
        return line.size() == DIMENSIONS_NUMBER && withDigits == DIMENSIONS_NUMBER;
    };

    public static void validateLines(List<String> lines) {
        if (lines.isEmpty()) {
            throw new ValidatorException("Empty input file.");
        }
        if (lines.size() != countDimensions(lines)) {
            throw new ValidatorException("Wrong input data format.");
        }
    }

    private static int countDimensions(List<String> lines) {
        return (int) lines.stream()
                .filter(isRoomDimensions)
                .count();
    }

}
