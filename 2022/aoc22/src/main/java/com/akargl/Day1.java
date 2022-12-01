package com.akargl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.akargl.utils.InputUtils;

public class Day1 {
  public static void main(String[] args) throws IOException {
    int p1 = part1(InputUtils.getInput("inputs/d1_1.txt"));
    System.out.println("Part 1:" + p1);

    int p2 = part2(InputUtils.getInput("inputs/d1_1.txt"));
    System.out.println("Part 2:" + p2);
  }

  protected static int part1(String input) {
    List<Integer> sums = inputToSums(input);
    return Collections.max(sums);
  }

  protected static int part2(String input) {
    List<Integer> sums = inputToSums(input);
    Collections.sort(sums, Collections.reverseOrder());
    return sums.get(0) + sums.get(1) + sums.get(2);
  }

  protected static List<Integer> inputToSums(String input) {
    return Arrays.stream(input.split("\\r?\\n\\r?\\n"))
        .map(c -> Arrays.stream(c.split("\\r?\\n"))
            .mapToInt(Integer::valueOf)
            .sum())
        .collect(Collectors.toList());
  }
}
