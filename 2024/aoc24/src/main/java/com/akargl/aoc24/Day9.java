package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = InputUtils.getInputLines("inputs/d9_sample.txt").getFirst();

        List<Block> blocks = parseInput(input);

        ListIterator<Block> it = blocks.listIterator(blocks.size());

        it.previous();
        System.out.println(it.previousIndex());
        blocks.addFirst(new Block(0, 0));
        System.out.println(it.previousIndex());

        System.out.println();
    }

    protected static List<Block> parseInput(String input) {
        List<Block> blocks = new ArrayList<>();

        String[] blockStrings = input.split("");
        for (int i = 0; i < blockStrings.length; i++) {
            Integer id = i % 2 == 0 ? i/2 : null;
            blocks.add(new Block(id, Integer.parseInt(blockStrings[i])));
        }

        return blocks;
    }

    @Data
    @AllArgsConstructor
    protected static class Block {
        Integer fileId;
        int size;
    }
}
