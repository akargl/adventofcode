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
    }

    protected static StringGrid inputToGrid(String inputPath) throws IOException {
        List<String> inputLines = InputUtils.getInputLines(inputPath);
        return new StringGrid(inputLines);
    }

    protected static int findInGrid(StringGrid grid, String word) {
        List<String> searchList = List.of(word.split(""));

        int count = 0;

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), 1, 0) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), 1, 1) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), 0, 1) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), -1, 1) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), -1, 0) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), -1, -1) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), 0, -1) ? 1 : 0;
                count += findInDirection(grid, new Coordinate(x, y), new ArrayList<>(searchList), 1, -1) ? 1 : 0;
            }
        }

        return count;
    }

    protected static boolean findInDirection(StringGrid grid, Coordinate c, List<String> search, int searchVectorX, int searchVectorY) {
        if (search.isEmpty()) {
            return true;
        }
        String s = search.removeFirst();
        if (!Objects.equals(s, grid.getElement(c))) {
            return false;
        }

        return findInDirection(grid, new Coordinate(c.x + searchVectorX, c.y + searchVectorY), search, searchVectorX, searchVectorY);
    }
}
