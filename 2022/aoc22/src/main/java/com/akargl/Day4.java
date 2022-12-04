package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import com.akargl.utils.InputUtils;

public class Day4 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d4_1.txt"), false);
    long numCompletelyOverlappingSections = Day4.getNumCompletelyOverlappingSections(inputLines);
    System.out.println("Part 1: " + numCompletelyOverlappingSections);

    long numOverlappingSections = Day4.getNumOverlappingSections(inputLines);
    System.out.println("Part 2: " + numOverlappingSections);
  }

  protected static long getNumCompletelyOverlappingSections(List<String> lines) {
    return lines.stream().filter(l -> {
      List<Integer> boundaries = parseSectionBoundaries(l);
      return (isWithin(boundaries.get(0), boundaries.get(2), boundaries.get(3)) && isWithin(boundaries.get(1), boundaries.get(2), boundaries.get(3)) ||
          (isWithin(boundaries.get(2), boundaries.get(0), boundaries.get(1)) && isWithin(boundaries.get(3), boundaries.get(0), boundaries.get(1))));
    }).count();
  }

  protected static long getNumOverlappingSections(List<String> lines) {
    return lines.stream().filter(l -> {
      List<Integer> boundaries = parseSectionBoundaries(l);

      return (isWithin(boundaries.get(0), boundaries.get(2), boundaries.get(3)) || isWithin(boundaries.get(1), boundaries.get(2), boundaries.get(3))) ||
          (isWithin(boundaries.get(2), boundaries.get(0), boundaries.get(1)) || isWithin(boundaries.get(3), boundaries.get(0), boundaries.get(1)));
    }).count();
  }

  protected static List<Integer> parseSectionBoundaries(String line) {
    return Pattern.compile("\\d+").matcher(line).results()
        .map(MatchResult::group).map(Integer::valueOf).toList();
  }

  protected static boolean isWithin(int num, int lower, int upper) {
    return num >= lower && num <= upper;
  }
}
