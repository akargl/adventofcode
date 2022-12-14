package com.akargl;

import java.io.IOException;
import java.util.Arrays;
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
    private List<Integer> items;

    private int testDivisibleBy;
    private int throwToIfTrue;
    private int throwToIfFalse;

    private UnaryOperator<Integer> operation;

    private int itemsHandled;

    public static Monkey fromInput(String input) {
      String[] lines = input.split("\\r?\\n");
      List<Integer> startingItems = Arrays.stream(lines[1].replaceAll("\s+Starting items:\s+", "")
          .split(", "))
          .map(Integer::parseInt).collect(Collectors.toList());
      String[] operationComponents = lines[2].replaceAll("\s+Operation: new =\s+", "").split("\s+");
      int testDivisibleBy = Integer.parseInt(lines[3].replaceAll("\s+Test: divisible by ", ""));
      int throwToIfTrue = Integer.parseInt(lines[4].replaceAll("\s+If true: throw to monkey ", ""));
      int throwToIfFalse = Integer.parseInt(lines[5].replaceAll("\s+If false: throw to monkey ", ""));

      UnaryOperator<Integer> operation = old -> {
        int rightPart = operationComponents[2].equals("old") ? old : Integer.parseInt(operationComponents[2]);
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
    String input = InputUtils.getInput("inputs/d11_sample.txt");
    int part1 = part1(input);
    System.out.println("Part 1: " + part1);
  }

  public static int part1(String input) {
    List<Monkey> monkeys = parseInput(input);

    List<Integer> itemsHandled = Collections.nCopies(monkeys.size(), 0);

    for (int i = 0; i < 20; i++) {
      for (Monkey monkey : monkeys) {
        monkey.setItemsHandled(monkey.getItemsHandled() + monkey.getItems().size());
      }
      simulateRound(monkeys);
    }

    return -1;
  }

  public static List<Monkey> parseInput(String input) {
    String[] inputSections = input.split("\\r?\\n\\r?\\n");
    return Arrays.stream(inputSections).map(Monkey::fromInput).toList();
  }

  public static void simulateRound(List<Monkey> monkeys) {
    for (Monkey monkey : monkeys) {
      for (Integer item : monkey.getItems()) {
        item = monkey.operation.apply(item);
        item /= 3;
        if (item % monkey.getTestDivisibleBy() == 0) {
          monkeys.get(monkey.getThrowToIfTrue()).getItems().add(item);
        } else {
          monkeys.get(monkey.getThrowToIfFalse()).getItems().add(item);
        }
      }

      monkey.getItems().clear();
    }
  }
}
