package org.pet.service;

import org.pet.model.RoomDimensions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {

    private final String inputFile;
    private final String command;

    public InputReader(String... args) {
        String inputFile = args[0];
        if (inputFile == null || inputFile.isEmpty()){
            throw new InputReaderException("Empty input file name.");
        }
        this.inputFile = inputFile;

        String command = args[1];
        if (command == null || command.isEmpty()){
            throw new InputReaderException("Empty command name.");
        }
        this.command = command;
    }

    public String getInputFileName() {
        return inputFile;
    }

    public List<RoomDimensions> fetchDimensions() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(getInputFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Validator.validateLines(lines);
        return lines.stream()
                .map(this::toDimensions)
                .toList();
    }

    private RoomDimensions toDimensions(String line){
        String[] split = line.split(Validator.MULTIPLY_SIGN);
        return new RoomDimensions(
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]));
    }
}
