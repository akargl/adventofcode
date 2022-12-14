package com.akargl.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputUtils {
  public static List<String> getInputLines(String inputPath) throws IOException {
    return getInputLines(inputPath, false);
  }

  public static List<String> getInputLines(String inputPath, boolean includeEmptyLines) throws IOException {
    return Files.lines(Path.of(inputPath))
        .filter(l -> includeEmptyLines || !l.trim().equals(""))
        .toList();
  }

  public static String getInput(String path) throws IOException {
    return Files.readString((Path.of(path)));
  }
}
