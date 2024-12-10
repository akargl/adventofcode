package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = InputUtils.getInputLines("inputs/d9_1.txt").getFirst();

        List<Integer> blocks = parseInput(input);

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Integer id = blocks.get(i);
            if (id != null) {
                for (int j = 0; j < i; j++) {
                    if (blocks.get(j) == null) {
                        blocks.set(j, id);
                        blocks.set(i, null);
                        break;
                    }
                }
            }
        }

        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) != null) {
                checksum += blocks.get(i) * i;
            }
        }

        System.out.println("Part1: " + checksum);
    }

    protected static List<Integer> parseInput(String input) {
        List<Integer> blocks = new ArrayList<>();

        String[] blockStrings = input.split("");
        for (int i = 0; i < blockStrings.length; i++) {
            Integer id = i % 2 == 0 ? i/2 : null;
            int size = Integer.parseInt(blockStrings[i]);
            for (int j = 0; j < size; j++) {
                blocks.add(id);
            }
        }

        return blocks;
    }
}
