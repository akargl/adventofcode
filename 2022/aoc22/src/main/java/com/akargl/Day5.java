package com.akargl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
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
    String input = InputUtils.getInput("inputs/d5_1.txt");
    String topCratesP1 = getTopCrates(input, Day5::processCommandP1);
    System.out.println("Part 1: " + topCratesP1);

    String topCratesP2 = getTopCrates(input, Day5::processCommandP2);
    System.out.println("Part 1: " + topCratesP2);
  }

  protected static String getTopCrates(String input, BiConsumer<Map<Integer, List<String>>, Command> processCommand) {
    String[] inputParts = input.split("\\r?\\n\\r?\\n");

    Map<Integer, List<String>> stacks = parseInitialStacks(inputParts[0]);
    List<Command> commands = parseMoves(inputParts[1]);

    for (Command command : commands) {
      processCommand.accept(stacks, command);
    }
    return stacks.values().stream()
        .filter(l -> !l.isEmpty())
        .map(l -> l.get(0))
        .collect(Collectors.joining());
  }

  private static void processCommandP1(Map<Integer, List<String>> stacks, Command command) {
    for (int i = 0; i < command.getAmount(); i++) {
      String cargo = stacks.get(command.getFrom()).remove(0);
      stacks.get(command.getTo()).add(0, cargo);
    }
  }

  private static void processCommandP2(Map<Integer, List<String>> stacks, Command command) {
    for (int i = 0; i < command.getAmount(); i++) {
      String cargo = stacks.get(command.getFrom()).remove(0);
      stacks.get(command.getTo()).add(i, cargo);
    }
  }

  protected static Map<Integer, List<String>> parseInitialStacks(String input) {
    List<String> lines = Arrays.stream(input.split("\\r?\\n")).toList();

    List<Integer> stackNumbers = Arrays.stream(lines.get(lines.size() - 1).split("\\s+"))
        .filter(l -> !l.isBlank()).map(Integer::parseInt).toList();

    Map<Integer, List<String>> stacks = new LinkedHashMap<>(); //use a LinkedHashMap to preserve the stack order
    for (Integer stackNumber : stackNumbers) {
      stacks.put(stackNumber, new LinkedList<>());
    }

    lines = lines.stream()
        .limit(lines.size() -1L) //remove the stack numbers as we can assume it's always starting from 1 in consecutive order
        .toList();

    for (String line : lines) {
      final Pattern pattern = Pattern.compile(".(?<cargo>.).\\s?");
      final Matcher matcher = pattern.matcher(line);

      Iterator<Integer> stackNumbersIt = stackNumbers.iterator();
      while (matcher.find() && stackNumbersIt.hasNext()) {
        String cargo = matcher.group("cargo");
        Integer stackNumber = stackNumbersIt.next();
        if (!cargo.isBlank()) {
          stacks.get(stackNumber).add(cargo);
        }
      }
    }

    return stacks;
  }

  protected static List<Command> parseMoves(String input) {
    return Pattern.compile("move (\\d+) from (\\d+) to (\\d+)", Pattern.MULTILINE)
        .matcher(input)
        .results()
        .map(r -> new Command(Integer.parseInt(r.group(1)), Integer.parseInt(r.group(2)), Integer.parseInt(r.group(3))))
        .toList();
  }
}
