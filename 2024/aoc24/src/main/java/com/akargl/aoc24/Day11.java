package com.akargl.aoc24;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.akargl.aoc24.utils.InputUtils;

public class Day11 {
  public static void main(String[] args) throws IOException {
    String input = InputUtils.getInputLines("inputs/d11_1.txt").getFirst();

    List<Long> stones = Arrays.stream(input.split(" "))
        .map(Long::parseLong)
        .collect(Collectors.toList());

    long numStonesP1 = part1(stones, 25);
    System.out.println(numStonesP1);
  }

  protected static long part1(List<Long> stones, long numBlinks) {
    for (int i = 0; i < numBlinks; i++) {
      System.out.println("Blink " + i+1 + " stones.size " + stones.size());
      for (int j = 0; j < stones.size(); j++) {
        Long stone = stones.get(j);
        if (stone == 0L) {
          stones.set(j, 1L);
        } else {
          String s = String.valueOf(stone);
          if (s.length() % 2 == 0) {
            long left = Long.parseLong(s.substring(0, s.length()/2));
            long right = Long.parseLong(s.substring(s.length()/2));
            stones.set(j, left);
            stones.add(j+1, right);
            j++;
          } else {
            stones.set(j, stone * 2024);
          }
        }
      }
    }

    return stones.size();
  }

}
