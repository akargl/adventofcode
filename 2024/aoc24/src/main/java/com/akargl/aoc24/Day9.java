package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = InputUtils.getInputLines("inputs/d9_1.txt").getFirst();

        long checksumP1 = part1(input);

        System.out.println("Part1: " + checksumP1);

        long checksumP2 = part2(input);

        System.out.println("Part2: " + checksumP2);
    }

    private static long part2(String input) {
        List<Block> blocks = parseInput(input);

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block block = blocks.get(i);
            if (block.getFileId() != null) {
                for (int j = 0; j < i; j++) {
                    Block freeSpaceBlock = blocks.get(j);
                    if (freeSpaceBlock.getFileId() == null && freeSpaceBlock.getSize() >= block.getSize()) {
                        freeSpaceBlock.setFileId(block.getFileId());
                        if (freeSpaceBlock.getSize() > block.getSize()) {
                            blocks.add(j+1, new Block(null, freeSpaceBlock.getSize() - block.getSize()));
                            i++;
                            freeSpaceBlock.setSize(block.getSize());
                        }

                        block.setFileId(null);
                        break;
                    }
                }
            }
        }

        //chop into single blocks to make checksum calc easier
        List<Integer> singleBlocks = new ArrayList<>();
        for (Block block : blocks) {
            for (int i = 0; i < block.getSize(); i++) {
                singleBlocks.add(block.getFileId());
            }
        }

        return getChecksum(singleBlocks);
    }

    private static long part1(String input) {
        List<Integer> blocks = parseInputToSingleBlocks(input);

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

        return getChecksum(blocks);
    }

    private static long getChecksum(List<Integer> blocks) {
        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) != null) {
                checksum += blocks.get(i) * i;
            }
        }
        return checksum;
    }

    protected static List<Integer> parseInputToSingleBlocks(String input) {
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
