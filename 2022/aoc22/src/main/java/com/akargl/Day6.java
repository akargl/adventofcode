package com.akargl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.akargl.utils.InputUtils;

public class Day6 {
  public static void main(String[] args) throws IOException {
    int markerPositionP1 = findMarkerPosition(InputUtils.getInput("inputs/d6_1.txt"), 4);
    System.out.println("Part 1: " + markerPositionP1);

    int markerPositionP2 = findMarkerPosition(InputUtils.getInput("inputs/d6_1.txt"), 14);
    System.out.println("Part 2: " + markerPositionP2);
  }

  protected static int findMarkerPosition(String input, int numUniqueChars) {
    List<Integer> chars = input.chars().boxed().toList();
    for (int i = numUniqueChars-1; i < chars.size(); i++) {
      Set<Integer> currentSet = new HashSet<>(chars.subList(i - (numUniqueChars - 1), i+1));

      if (currentSet.size() == numUniqueChars) {
        return i+1;
      }
    }

    return 0;
  }
}
