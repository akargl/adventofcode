package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.StringGrid;

import java.io.IOException;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws IOException {
        StringGrid grid = inputToGrid("inputs/d4_sample.txt");

        int count = findInGrid(grid, "XMAS");
        System.out.println(count);
    }

    protected static StringGrid inputToGrid(String inputPath) throws IOException {
        List<String> inputLines = InputUtils.getInputLines(inputPath);
        return new StringGrid(inputLines);
    }

    protected static int findInGrid(StringGrid grid, String word) {
        int count = 0;

        for (int x = 0; x < grid.getWidth(); x++) {
            List<String> column = grid.getColumn(x);
            count += findWordInList(column, word);
            count += findWordInList(column.reversed(), word);
        }

        for (int y = 0; y < grid.getHeight(); y++) {
            List<String> row = grid.getRow(y);
            count += findWordInList(row, word);
            count += findWordInList(row.reversed(), word);
        }

        return count;
    }

    protected static int findWordInList(List<String> list, String word) {
        String s = String.join("", list);
        return countSubstringOccurence(s, word);
    }

    protected static int countSubstringOccurence(String s, String sub) {
        return s.split(sub, -1).length-1;
    }
}
