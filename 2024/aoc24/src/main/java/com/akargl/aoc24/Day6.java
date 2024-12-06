package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

    public static final List<String> GUARD_MARKERS = List.of("^", "v", "<", ">");
    public static final String OBSTACLE = "#";

    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d6_1.txt");
        StringGrid grid = new StringGrid(inputLines);

        List<Coordinate> guardCoordinates = grid.getCoordinatesWhere(GUARD_MARKERS::contains);

        assert guardCoordinates.size() == 1;

        Coordinate guardPosition = guardCoordinates.getFirst();
        Direction guardDirection = getDirection(grid.getElement(guardPosition));

        int numVisitedPositions = simulateGuardMovement(guardPosition, guardDirection, grid);

        System.out.println(numVisitedPositions);
    }

    private static int simulateGuardMovement(Coordinate guardPosition, Direction guardDirection, StringGrid grid) {
        Set<Coordinate> visitedPositions = new HashSet<>();

        while (true) {
            visitedPositions.add(guardPosition);

            Coordinate nextPosition = getNextPosition(guardPosition, guardDirection);
            String nextGridElement = grid.getElement(nextPosition);

            if (nextGridElement == null) {
                //reached map boundary
                return visitedPositions.size();
            } else if (nextGridElement.equals(OBSTACLE)) {
                //turn right
                guardDirection = turnRight(guardDirection);
            } else {
                guardPosition = nextPosition;
            }
        }
    }

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    protected static Direction getDirection(String direction) {
        return switch (direction) {
            case "^" -> Direction.UP;
            case "v" -> Direction.DOWN;
            case "<" -> Direction.LEFT;
            case ">" -> Direction.RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    protected static Coordinate getNextPosition(Coordinate currentPosition, Direction direction) {
        return switch (direction) {
            case UP -> currentPosition.getTop();
            case DOWN -> currentPosition.getBottom();
            case LEFT -> currentPosition.getLeft();
            case RIGHT -> currentPosition.getRight();
        };
    }

    protected static Direction turnRight(Direction direction) {
        return switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }
}
