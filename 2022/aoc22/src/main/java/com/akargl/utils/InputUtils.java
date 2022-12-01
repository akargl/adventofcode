package com.akargl.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtils {
  public static List<String> getInputLines(Path inputPath, boolean includeEmptyLines) throws IOException {
    return Files.lines(inputPath)
        .filter(l -> includeEmptyLines || !l.trim().equals(""))
        .collect(Collectors.toList());
  }

  public static String getInput(String path) throws IOException {
    return Files.readString((Path.of(path)));
  }
}
