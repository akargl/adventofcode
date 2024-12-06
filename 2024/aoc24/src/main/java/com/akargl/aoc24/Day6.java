package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {

    public static final List<String> GUARD_MARKERS = List.of("^", "v", "<", ">");
    public static final String OBSTACLE = "#";

    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d6_sample.txt");
        StringGrid grid = new StringGrid(inputLines);

        List<Coordinate> guardCoordinates = grid.getCoordinatesWhere(GUARD_MARKERS::contains);

        assert guardCoordinates.size() == 1;

        Coordinate guardCoords = guardCoordinates.getFirst();
        GuardPosition guardPosition = new GuardPosition(guardCoordinates.getFirst(), parseDirection(grid.getElement(guardCoords)));

        long start = System.nanoTime();
        int numVisitedPositions = simulateGuardMovement(guardPosition, grid);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);

        System.out.println(numVisitedPositions);
    }

    private static int simulateGuardMovement(GuardPosition guardPosition, StringGrid grid) {
        Set<GuardPosition> visitedPositions = new HashSet<>();

        visitedPositions.add(guardPosition);

        while (true) {
            Coordinate nextCoords = guardPosition.getNextCoords();
            String nextGridElement = grid.getElement(nextCoords);

            if (nextGridElement == null) {
                //reached map boundary
                return visitedPositions.stream()
                        .map(GuardPosition::getCoordinate)
                        .collect(Collectors.toSet())
                        .size();
            } else if (nextGridElement.equals(OBSTACLE)) {
                //turn right
                guardPosition.turnRight();
            } else {
                guardPosition.setCoordinate(nextCoords);
                visitedPositions.add(new GuardPosition(guardPosition.getCoordinate(), guardPosition.getDirection()));
            }
        }
    }

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    protected static Direction parseDirection(String direction) {
        return switch (direction) {
            case "^" -> Direction.UP;
            case "v" -> Direction.DOWN;
            case "<" -> Direction.LEFT;
            case ">" -> Direction.RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    @Data
    @AllArgsConstructor
    protected static class GuardPosition {
        Coordinate coordinate;
        Direction direction;

        void turnRight() {
            direction = switch (direction) {
                case UP -> Direction.RIGHT;
                case RIGHT -> Direction.DOWN;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.UP;
            };
        }

        Coordinate getNextCoords() {
            return switch (direction) {
                case UP -> coordinate.getTop();
                case DOWN -> coordinate.getBottom();
                case LEFT -> coordinate.getLeft();
                case RIGHT -> coordinate.getRight();
            };
        }
    }
}
