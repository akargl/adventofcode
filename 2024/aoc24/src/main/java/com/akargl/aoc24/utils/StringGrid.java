package com.akargl.aoc24.utils;

import java.util.Arrays;
import java.util.List;

public class StringGrid extends Grid<String> {
  public StringGrid(List<String> lines) {
    super(lines.stream()
        .map(l -> Arrays.stream(l.split(""))
            .toList())
        .toList());
  }
}
