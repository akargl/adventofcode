package com.akargl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import com.akargl.utils.InputUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day11 {
  @Data
  @AllArgsConstructor
  public static class Monkey {
    private List<Long> items;

    private int testDivisibleBy;
    private int throwToIfTrue;
    private int throwToIfFalse;

    private UnaryOperator<Long> operation;

    private long itemsHandled;

    public static Monkey fromInput(String input) {
      String[] lines = input.split("\\r?\\n");
      List<Long> startingItems = Arrays.stream(lines[1].replaceAll("\s+Starting items:\s+", "")
          .split(", "))
          .map(Long::parseLong).collect(Collectors.toList());
      String[] operationComponents = lines[2].replaceAll("\s+Operation: new =\s+", "").split("\s+");
      int testDivisibleBy = Integer.parseInt(lines[3].replaceAll("\s+Test: divisible by ", ""));
      int throwToIfTrue = Integer.parseInt(lines[4].replaceAll("\s+If true: throw to monkey ", ""));
      int throwToIfFalse = Integer.parseInt(lines[5].replaceAll("\s+If false: throw to monkey ", ""));

      UnaryOperator<Long> operation = old -> {
        Long rightPart = operationComponents[2].equals("old") ? old : Integer.parseInt(operationComponents[2]);
        if (operationComponents[1].equals("+")) {
          return old + rightPart;
        } else if (operationComponents[1].equals("*")) {
          return old * rightPart;
        } else {
          return null;
        }
      };

      return new Monkey(startingItems, testDivisibleBy, throwToIfTrue, throwToIfFalse, operation, 0);
    }
  }

  public static void main(String[] args) throws IOException {
    String input = InputUtils.getInput("inputs/d11_1.txt");

    List<Monkey> monkeysP1 = simulateNRounds(input, 20, true);
    long part1 = multiplyTopMonkeysItemsHandled(monkeysP1, 2);
    System.out.println("Part 1: " + part1);

    List<Monkey> monkeysP2 = simulateNRounds(input, 10000, false);
    long part2 = multiplyTopMonkeysItemsHandled(monkeysP2, 2);
    System.out.println("Part 2: " + part2);
  }

  public static List<Monkey> simulateNRounds(String input, int rounds, boolean divideWorryLevels) {
    List<Monkey> monkeys = parseInput(input);

    for (int i = 0; i < rounds; i++) {
      simulateRound(monkeys, divideWorryLevels);
    }

    return monkeys;
  }

  public static long multiplyTopMonkeysItemsHandled(List<Monkey> monkeys, int numTopMonkeys) {
    return monkeys.stream()
        .map(Monkey::getItemsHandled)
        .sorted(Collections.reverseOrder())
        .limit(numTopMonkeys)
        .reduce(1L, (p, c) -> p * c);
  }

  public static List<Monkey> parseInput(String input) {
    String[] inputSections = input.split("\\r?\\n\\r?\\n");
    return Arrays.stream(inputSections).map(Monkey::fromInput).toList();
  }

  public static void simulateRound(List<Monkey> monkeys, boolean divideWorryLevels) {
    int commonMultiple = getCommonMultiple(monkeys.stream().map(m -> m.getTestDivisibleBy()).toList());
    for (Monkey monkey : monkeys) {
      monkey.setItemsHandled(monkey.getItemsHandled() + monkey.getItems().size());
      for (Long item : monkey.getItems()) {
        item = monkey.operation.apply(item);
        if (divideWorryLevels) {
          item /= 3;
        } else {
          item %= commonMultiple;
        }
        if (item % monkey.getTestDivisibleBy() == 0) {
          monkeys.get(monkey.getThrowToIfTrue()).getItems().add(item);
        } else {
          monkeys.get(monkey.getThrowToIfFalse()).getItems().add(item);
        }
      }

      monkey.getItems().clear();
    }
  }

  public static int getCommonMultiple(List<Integer> testDivisors) {
    return testDivisors.stream().reduce(1, (p, c) -> p * c);
  }
}
