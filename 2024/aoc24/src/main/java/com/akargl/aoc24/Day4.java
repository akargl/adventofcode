package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day4 {
    public static void main(String[] args) throws IOException {
        StringGrid grid = inputToGrid("inputs/d4_1.txt");

        int count = findInGrid(grid, "XMAS");
        System.out.println(count);

        int xmasCount = findXmas(grid);
        System.out.println(xmasCount);
    }

    protected static int findXmas(StringGrid grid) {
        int count = 0;

        // just hardcoded since there are only four target variations and there's no time left
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.getElement(x, y).equals("A")) {
                    if (("M".equals(grid.getElement(x - 1, y - 1)) &&
                            "S".equals(grid.getElement(x + 1, y - 1)) &&
                            "M".equals(grid.getElement(x - 1, y + 1)) &&
                            "S".equals(grid.getElement(x + 1, y + 1))) ||
                        ("S".equals(grid.getElement(x - 1, y - 1)) &&
                            "S".equals(grid.getElement(x + 1, y - 1)) &&
                            "M".equals(grid.getElement(x - 1, y + 1)) &&
                            "M".equals(grid.getElement(x + 1, y + 1))) ||
                        ("M".equals(grid.getElement(x - 1, y - 1)) &&
                            "M".equals(grid.getElement(x + 1, y - 1)) &&
                            "S".equals(grid.getElement(x - 1, y + 1)) &&
                            "S".equals(grid.getElement(x + 1, y + 1))) ||
                        ("S".equals(grid.getElement(x - 1, y - 1)) &&
                            "M".equals(grid.getElement(x + 1, y - 1)) &&
                            "S".equals(grid.getElement(x - 1, y + 1)) &&
                            "M".equals(grid.getElement(x + 1, y + 1)))) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    protected static int findInGrid(StringGrid grid, String word) {
        List<String> searchList = List.of(word.split(""));

        int count = 0;

        List<Coordinate> directions = List.of(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1), new Coordinate(-1, 1),
                new Coordinate(-1, 0), new Coordinate(-1, -1), new Coordinate(0, -1), new Coordinate(1, -1));

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                for (Coordinate direction : directions) {
                    count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), direction) ? 1 : 0;
                }
            }
        }

        return count;
    }

    protected static boolean findInDirection(StringGrid grid, Coordinate c, List<String> search, Coordinate searchDirection) {
        if (search.isEmpty()) {
            return true;
        }
        String s = search.removeFirst();
        if (!Objects.equals(s, grid.getElement(c))) {
            return false;
        }

        return findInDirection(grid, new Coordinate(c.x + searchDirection.getX(), c.y + searchDirection.getY()), search, searchDirection);
    }

    protected static StringGrid inputToGrid(String inputPath) throws IOException {
        List<String> inputLines = InputUtils.getInputLines(inputPath);
        return new StringGrid(inputLines);
    }
}
