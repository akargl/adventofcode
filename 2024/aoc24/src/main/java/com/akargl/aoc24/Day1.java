package com.akargl.aoc24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.akargl.aoc24.utils.InputUtils;

public class Day1 {
  public static void main(String[] args) throws IOException {
    List<List<Integer>> lists = parseInputLists(InputUtils.getInputLines("inputs/d1_1.txt"));
    assert lists.size() == 2;

    List<Integer> left = lists.get(0);
    List<Integer> right = lists.get(1);

    int totalDistance = p1GetTotalDistance(left, right);
    System.out.println("Part1: " + totalDistance);

    int similarityScore = p2GetSimilarityScore(left, right);
    System.out.println("Part2: " + similarityScore);
  }

  protected static int p1GetTotalDistance(List<Integer> left, List<Integer> right) {
    left.sort(Integer::compareTo);
    right.sort(Integer::compareTo);

    int totalDistance = 0;

    for (int i = 0; i < left.size(); i++) {
      totalDistance += Math.abs(left.get(i) - right.get(i));
    }

    return totalDistance;
  }

  protected static int p2GetSimilarityScore(List<Integer> left, List<Integer> right) {
    return left.stream()
        .reduce(0, (subTotal, x) -> subTotal + x * Collections.frequency(right, x));
  }

  protected static List<List<Integer>> parseInputLists(List<String> inputLines) {
    List<List<Integer>> lists = List.of(new ArrayList<>(), new ArrayList<>());

    for (String inputLine : inputLines) {
      String[] split = inputLine.split("\\s+");

      lists.get(0).add(Integer.parseInt(split[0]));
      lists.get(1).add(Integer.parseInt(split[1]));
    }

    return lists;
  }

}
