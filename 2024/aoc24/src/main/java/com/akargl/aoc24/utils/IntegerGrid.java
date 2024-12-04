package com.akargl.aoc24.utils;

import java.util.Arrays;
import java.util.List;

public class IntegerGrid extends Grid<Integer> {
  public IntegerGrid(List<String> lines) {
    super(lines.stream()
        .map(l -> Arrays.stream(l.split(""))
            .map(Integer::parseInt)
            .toList())
        .toList());
  }
}
