package org.pet.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    private static final String INPUT_FILE_NAME = "input.txt";

    @Test
    void getInputFileName() {
        String[] inputs = {INPUT_FILE_NAME, "someCommand"};
        InputReader reader = new InputReader(inputs);

        assertDoesNotThrow(reader::getInputFileName);
        assertEquals(INPUT_FILE_NAME, reader.getInputFileName());
    }

    @Test
    void noInput() {
        String[] inputs1 = {null, "someCommand"};
        String[] inputs2 = {"", "someCommand"};
        String[] inputs3 = {"inputFile", ""};

        assertThrows(InputReaderException.class,
                () -> new InputReader(inputs1),
                "Empty input file name.");

        assertThrows(InputReaderException.class,
                () -> new InputReader(inputs2),
                "Empty input file name.");

        assertThrows(InputReaderException.class,
                () -> new InputReader(inputs3),
                "Empty command name.");
    }

    @Test
    void fetchDimensionsHappyPath() {
        String[] input = {"src/test/resources/input-h-p.txt", "someCommand"};

        InputReader reader = new InputReader(input);

        assertDoesNotThrow(reader::fetchDimensions);
        assertEquals(4, reader.fetchDimensions().size());
    }

    @Test
    void fetchDimensionsNoInputFile() {
        String inputFile = "src/test/resources/no-input-file.txt";

        String[] input = {inputFile, "someCommand"};

        InputReader reader = new InputReader(input);

        assertThrows(RuntimeException.class,
                reader::fetchDimensions,
                inputFile);
    }
}