package org.pet.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateLinesHappyPath() {
        List<String> input = List.of("3x11x24", "13x5x19", "1x9x27");

        assertDoesNotThrow(() -> Validator.validateLines(input));
    }

    @Test
    void validateLinesEmptyInput() {
        List<String> input = new ArrayList<>();

        assertThrows(ValidatorException.class, () -> Validator.validateLines(input), "Empty input file.");
    }

    @Test
    void validateLinesWrongFormat() {
        List<String> withWrongMultiply = List.of("3x11x24", "13A5x19", "1x9x27");
        List<String> withNonDigits = List.of("3x11xs4", "13A5x19", "1x9x27");

        assertThrows(ValidatorException.class,
                () -> Validator.validateLines(withWrongMultiply),
                "Wrong input data format.");
        assertThrows(ValidatorException.class,
                () -> Validator.validateLines(withNonDigits),
                "Wrong input data format.");
    }
}