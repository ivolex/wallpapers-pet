package org.pet;

import org.pet.service.CalculationService;
import org.pet.service.InputReader;

public class WallpapersApp {

    public static final String AVAILABLE_COMMANDS = "Available commands - total/cubic/twins";

    public static void main(String... args) {
        if (args.length != 2) {
            throw new RuntimeException("Usage:\n" +
                    "  java -jar WallpapersApp.jar <INPUT_FILE> <COMMAND>\n" +
                    AVAILABLE_COMMANDS);
        }
        InputReader reader = new InputReader(args);
        CalculationService service = new CalculationService(reader);

        switch (args[1]){
            case "total":
                System.out.println(service.totalSquareFeet());
                break;
            case "cubic":
                System.out.println(service.totalCubic());
                break;
            case "twins":
                System.out.println(service.totalTwins());
                break;
            default:
                System.out.println(AVAILABLE_COMMANDS);
        }
    }

}
