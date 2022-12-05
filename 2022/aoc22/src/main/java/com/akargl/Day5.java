package com.akargl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.akargl.utils.InputUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Day5 {
  @AllArgsConstructor
  @Getter
  protected static class Command {
    int amount;
    int from;
    int to;
  }

  public static void main(String[] args) throws IOException {
    String topCrates = getTopCrates(InputUtils.getInput("inputs/d5_1.txt"));
    System.out.println("Part 1: " + topCrates);
  }

  protected static String getTopCrates(String input) {
    String[] inputParts = input.split("\\r?\\n\\r?\\n");

    List<List<String>> stacks = parseInitialStacks(inputParts[0]);
    List<Command> commands = parseMoves(inputParts[1]);

    for (Command command : commands) {
      //processCommandP1(stacks, command);
      processCommandP2(stacks, command);
    }

    String topCargos = stacks.stream().filter(l -> !l.isEmpty()).map(l -> l.get(0)).collect(Collectors.joining());

    return topCargos;
  }

  private static void processCommandP1(List<List<String>> stacks, Command command) {
    for (int i = 0; i < command.getAmount(); i++) {
      String cargo = stacks.get(command.getFrom()).remove(0);
      stacks.get(command.getTo()).add(0, cargo);
    }
  }

  private static void processCommandP2(List<List<String>> stacks, Command command) {
    for (int i = 0; i < command.getAmount(); i++) {
      String cargo = stacks.get(command.getFrom()).remove(0);
      stacks.get(command.getTo()).add(i, cargo);
    }
  }

  protected static List<List<String>> parseInitialStacks(String input) {
    List<String> lines = Arrays.stream(input.split("\\r?\\n")).toList();

    int numStacks = Arrays.stream(lines.get(lines.size() - 1)
        .split("\\s+")).filter(l -> !l.isBlank())
        .mapToInt(Integer::valueOf)
        .max().getAsInt();

    lines = lines.stream()
        .limit(lines.size() -1) //remove the stack numbers as we can assume it's always starting from 1 in consecutive order
        .toList();

    List<List<String>> stacks = new ArrayList<>();
    for (int i = 0; i < numStacks; i++) {
      stacks.add(new ArrayList<>());
    }

    for (String line : lines) {
      final Pattern pattern = Pattern.compile(".(?<cargo>.).\\s?");
      final Matcher matcher = pattern.matcher(line);

      int stackIndex = 0;
      while (matcher.find()) {
        String cargo = matcher.group("cargo");
        if (!cargo.isBlank()) {
          stacks.get(stackIndex).add(cargo);
        }
        stackIndex++;
      }
    }

    return stacks;
  }

  protected static List<Command> parseMoves(String input) {
    return Pattern.compile("move (?<amount>\\d+) from (?<from>\\d+) to (?<to>\\d+)", Pattern.MULTILINE)
        .matcher(input)
        .results()
        .map(r -> new Command(Integer.parseInt(r.group(1)), Integer.parseInt(r.group(2)) -1, Integer.parseInt(r.group(3)) -1))
        .toList();
  }
}
