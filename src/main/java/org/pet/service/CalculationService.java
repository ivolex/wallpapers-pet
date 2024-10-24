package org.pet.service;

import org.pet.model.RoomDimensions;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculationService {

    private final Predicate<RoomDimensions> isCubic = dim -> (dim.height() == dim.length()) && (dim.length() == dim.width());

    private final InputReader reader;

    public CalculationService(InputReader reader) {
        this.reader = reader;
    }

    public Long totalSquareFeet() {

        return reader.fetchDimensions().stream()
                .map(this::calculate)
                .mapToLong(Long::longValue)
                .sum();
    }

    public Map<Long, List<RoomDimensions>> totalCubic() {
        Map<Long, List<RoomDimensions>> grouped = reader.fetchDimensions().stream()
                .filter(isCubic)
                .collect(Collectors.groupingBy(this::calculate));

        return grouped.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, conflict) -> first,
                        LinkedHashMap::new));
    }

    public List<RoomDimensions> totalTwins() {
        Map<RoomDimensions, Long> grouped = reader.fetchDimensions().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return grouped.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    protected Long calculate(RoomDimensions dimensions) {
        int totalSquare = 2 * dimensions.length() * dimensions.width()
                + 2 * dimensions.width() * dimensions.height()
                + 2 * dimensions.height() * dimensions.length();
        return (long) (totalSquare + minimalSurface(dimensions));
    }

    private int minimalSurface(RoomDimensions dimensions) {
        return Stream.of(dimensions.height(), dimensions.length(), dimensions.width())
                .min(Integer::compare)
                .get();
    }
}
