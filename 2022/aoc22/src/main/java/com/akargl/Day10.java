package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.akargl.utils.InputUtils;

public class Day10 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines("inputs/d10_1.txt");
    int part1 = part1(inputLines);
    System.out.println("Part 1: " + part1);
  }

  protected static int part1(List<String> lines) {
    List<Integer> registerXValuesAfterCycle = getRegisterXValuesAfterCycle(lines);

    return registerXValuesAfterCycle.get(19 -1) * 20 +
        registerXValuesAfterCycle.get(59 -1) * 60 +
        registerXValuesAfterCycle.get(99 -1) * 100 +
        registerXValuesAfterCycle.get(139 -1) * 140 +
        registerXValuesAfterCycle.get(179 -1) * 180 +
        registerXValuesAfterCycle.get(219 -1) * 220;
  }

  protected static List<Integer> getRegisterXValuesAfterCycle(List<String> lines) {
    List<Integer> xValues = new ArrayList<>();
    int x = 1;

    for (String line : lines) {
      if ("noop".equals(line)) {
        xValues.add(x);
      } else if (line.startsWith("addx")) {
        int amount = Integer.parseInt(line.split(" ")[1]);
        xValues.add(x);
        x += amount;
        xValues.add(x);
      }
    }

    return xValues;
  }
}
