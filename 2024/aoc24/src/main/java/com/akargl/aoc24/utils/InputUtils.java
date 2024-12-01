package com.akargl.aoc24.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class InputUtils {
  public static List<String> getInputLines(String inputPath) throws IOException {
    return getInputLines(inputPath, false);
  }

  public static List<String> getInputLines(String inputPath, boolean includeEmptyLines) throws IOException {
    try (Stream<String> linesStream = getLines(inputPath)) {
      return linesStream
          .filter(l -> includeEmptyLines || !l.trim().isEmpty())
          .toList();
    }
  }

  private static Stream<String> getLines(String inputPath) throws IOException {
    return Files.lines(Path.of(inputPath));
  }

  public static String getInput(String path) throws IOException {
    return Files.readString((Path.of(path)));
  }
}
