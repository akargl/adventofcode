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
    public static final String FREE_FIELD = ".";

    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d6_1.txt");
        StringGrid grid = new StringGrid(inputLines);

        GuardPosition guardStartPosition = getGuardStartPosition(grid);

        part1(guardStartPosition, grid);

        part2(guardStartPosition, grid, inputLines);
    }

    protected static GuardPosition getGuardStartPosition(StringGrid grid) {
        List<Coordinate> guardCoordinates = grid.getCoordinatesWhere(GUARD_MARKERS::contains);

        assert guardCoordinates.size() == 1;

        Coordinate guardCoords = guardCoordinates.getFirst();
        return new GuardPosition(guardCoordinates.getFirst(), parseDirection(grid.getElement(guardCoords)));
    }

    protected static Integer part1(GuardPosition guardStartPosition, StringGrid grid) {
        long start = System.nanoTime();
        Integer numVisitedPositions = simulateGuardMovement(guardStartPosition, grid);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Part 1 runtime: " + timeElapsed + "ns");

        System.out.println("Part 1 num visited positions: " + numVisitedPositions);

        return numVisitedPositions;
    }

    protected static int part2(GuardPosition guardStartPosition, StringGrid ogGrid, List<String> inputLines) {
        long start = System.nanoTime();

        Set<Coordinate> obstaclePositionsForLoop = new HashSet<>();

        for (int x = 0; x < ogGrid.getWidth(); x++) {
            for (int y = 0; y < ogGrid.getHeight(); y++) {
                //System.out.println("Trying obstacle on " + x + ", " + y);
                if (ogGrid.getElement(x, y).equals(FREE_FIELD)) {
                    StringGrid tempGrid = new StringGrid(inputLines);
                    tempGrid.setElement(x, y, OBSTACLE);

                    boolean loopDetected = simulateGuardMovement(guardStartPosition, tempGrid) == null;
                    if (loopDetected) {
                        obstaclePositionsForLoop.add(new Coordinate(x, y));
                    }
                }
            }
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Part 2 runtime: " + timeElapsed/1000000 + "ms");

        int numObstaclePositions = obstaclePositionsForLoop.size();

        System.out.println("Part 2 num obstacle positions: " + numObstaclePositions);

        return numObstaclePositions;
    }

    private static Integer simulateGuardMovement(GuardPosition guardStartPosition, StringGrid grid) {
        GuardPosition guardPosition = new GuardPosition(guardStartPosition);

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
                guardPosition.turnRight();
            } else {
                guardPosition.setCoordinate(nextCoords);
                if (!visitedPositions.add(new GuardPosition(guardPosition))) {
                    //already visited -> loop detected
                    return null;
                }
            }
        }
    }

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
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

        public GuardPosition(GuardPosition og) {
            coordinate = og.getCoordinate();
            direction = og.getDirection();
        }
    }
}
