package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.akargl.utils.InputUtils;

public class Day3 {
  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d3_1.txt"), false);
    int prioritySum = getPrioritySum(inputLines);
    System.out.println("Part 1:" + prioritySum);

    int priorityBadgeSum = getBadgePrioritySum(inputLines);
    System.out.println("Part 2:" + priorityBadgeSum);
  }

  protected static int getPrioritySum(List<String> lines) {
    return lines.stream().mapToInt(l -> {
      int[] priorities = l.chars().toArray();
      Set<Integer> firstCompartment = Arrays.stream(Arrays.copyOf(priorities, priorities.length / 2)).boxed().collect(Collectors.toSet());
      int[] secondCompartment = Arrays.copyOfRange(priorities, (priorities.length / 2), priorities.length);
      for (int i : secondCompartment) {
        if (firstCompartment.contains(i)) {
          return getPriority(i);
        }
      }
      return 0;
    }).sum();
  }

  protected static int getBadgePrioritySum(List<String> lines) {
    int prioritySum = 0;
    for (int i = 0; i < lines.size(); i += 3) {
      List<Integer> member1 = lines.get(i).chars().boxed().toList();
      List<Integer> member2 = lines.get(i + 1).chars().boxed().toList();
      List<Integer> badge = lines.get(i + 2).chars().filter(x -> member1.contains(x) && member2.contains(x)).boxed().toList();
      prioritySum += getPriority(badge.get(0));
    }

    return prioritySum;
  }

  private static int getPriority(int i) {
    return i >= 'a' && i <= 'z' ? i - 96 : i - 38;
  }

}
