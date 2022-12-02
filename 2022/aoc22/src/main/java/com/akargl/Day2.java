package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import com.akargl.utils.InputUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Day2 {

  @AllArgsConstructor
  @Getter
  public enum Outcome {
    WIN(6, "Z"),
    LOSE(0, "X"),
    DRAW(3, "Y");

    private final int points;
    private final String alias;

    public static Outcome findByAlias(String alias) {
      for (Outcome outcome : values()) {
        if (outcome.getAlias().equals(alias)) {
          return outcome;
        }
      }
      return null;
    }
  }

  @Getter
  public enum Choice {
    ROCK(1, "A", "X"),
    PAPER(2, "B", "Y"),
    SCISSORS(3, "C", "Z");

    private final List<String> values;
    private final int score;
    Choice(int score, String ...values) {
      this.score = score;
      this.values = List.of(values);
    }

    public static Choice find(String s) {
      for (Choice choice : Choice.values()) {
        if (choice.getValues().contains(s)) {
          return choice;
        }
      }
      return null;
    }
  }

  public static void main(String[] args) throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d2_1.txt"), false);
    int p1 = getTotalOwnScore(inputLines, parseChoiceP1);
    System.out.println("Part 1: " + p1);

    int p2 = getTotalOwnScore(inputLines, parseChoiceP2);
    System.out.println("Part 2: " + p2);
  }

  protected static BiFunction<Choice, String, Choice> parseChoiceP1 = (o, t) -> Choice.find(t);
  protected static BiFunction<Choice, String, Choice> parseChoiceP2 = (o, t) -> getRequiredChoice(o, Outcome.findByAlias(t));

  protected static int getTotalOwnScore(List<String> lines, BiFunction<Choice, String, Choice> getOwnChoiceStrategy) {
    return lines.stream()
        .map(l -> getScoreFromLine(l, getOwnChoiceStrategy))
        .mapToInt(Integer::intValue).sum();
  }

  protected static int getScoreFromLine(String line, BiFunction<Choice, String, Choice> parseOwnChoiceFn) {
    String[] tokens = line.split(" ");
    Choice opponentChoice = Choice.find(tokens[0]);
    Choice ownChoice = parseOwnChoiceFn.apply(opponentChoice, tokens[1]);

    int outcomePoints = getOutcome(opponentChoice, ownChoice);
    return outcomePoints + ownChoice.getScore();
  }

  protected static int getOutcome(Choice opponentChoice, Choice ownChoice) {
    if (opponentChoice == ownChoice) {
      return 3;
    }

    if ((opponentChoice == Choice.ROCK && ownChoice == Choice.PAPER) ||
        (opponentChoice == Choice.PAPER && ownChoice == Choice.SCISSORS) ||
        (opponentChoice == Choice.SCISSORS && ownChoice == Choice.ROCK)) {
      return 6;
    }

    return 0;
  }

  protected static Choice getRequiredChoice(Choice opponentsChoice, Outcome requiredOutcome) {
    switch (requiredOutcome) {
      case DRAW:
        return opponentsChoice;
      case LOSE:
        return getLosingChoice(opponentsChoice);
      case WIN:
        return getWinningChoice(opponentsChoice);
    }

    throw new RuntimeException("Unknown Token: " + requiredOutcome);
  }

  private static Choice getLosingChoice(Choice opponentsChoice) {
    switch (opponentsChoice) {
      case ROCK:
        return Choice.SCISSORS;
      case PAPER:
        return Choice.ROCK;
      case SCISSORS:
        return Choice.PAPER;
    }

    throw new RuntimeException("Unhandled choice: " + opponentsChoice);
  }

  private static Choice getWinningChoice(Choice opponentsChoice) {
    switch (opponentsChoice) {
      case ROCK:
        return Choice.PAPER;
      case PAPER:
        return Choice.SCISSORS;
      case SCISSORS:
        return Choice.ROCK;
    }

    throw new RuntimeException("Unhandled choice: " + opponentsChoice);
  }
}
