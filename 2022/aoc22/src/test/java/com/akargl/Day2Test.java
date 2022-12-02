package com.akargl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import com.akargl.utils.InputUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

  @Test
  void getTotalOwnScore() throws IOException {
    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/d2_sample.txt"), false);
    assertEquals(15, Day2.getTotalOwnScore(inputLines, Day2.parseChoiceP1));
    assertEquals(12, Day2.getTotalOwnScore(inputLines, Day2.parseChoiceP2));
  }

  @Test
  void getOutcome() {
    assertEquals(Day2.Outcome.DRAW, Day2.getOutcome(Day2.Choice.ROCK, Day2.Choice.ROCK));
    assertEquals(Day2.Outcome.WIN, Day2.getOutcome(Day2.Choice.PAPER, Day2.Choice.SCISSORS));
    assertEquals(Day2.Outcome.LOSE, Day2.getOutcome(Day2.Choice.SCISSORS, Day2.Choice.PAPER));
  }

  @Test
  void getScore() {
    assertEquals(8, Day2.getScoreFromLine("A Y", Day2.parseChoiceP1));
  }

  @Test
  void getRequiredChoice() {
    assertEquals(Day2.Choice.SCISSORS, Day2.getRequiredChoice(Day2.Choice.PAPER, Day2.Outcome.WIN));
    assertEquals(Day2.Choice.ROCK, Day2.getRequiredChoice(Day2.Choice.PAPER, Day2.Outcome.LOSE));
    assertEquals(Day2.Choice.PAPER, Day2.getRequiredChoice(Day2.Choice.PAPER, Day2.Outcome.DRAW));

    assertEquals(Day2.Choice.PAPER, Day2.getRequiredChoice(Day2.Choice.ROCK, Day2.Outcome.WIN));
    assertEquals(Day2.Choice.SCISSORS, Day2.getRequiredChoice(Day2.Choice.ROCK, Day2.Outcome.LOSE));
    assertEquals(Day2.Choice.ROCK, Day2.getRequiredChoice(Day2.Choice.ROCK, Day2.Outcome.DRAW));

    assertEquals(Day2.Choice.ROCK, Day2.getRequiredChoice(Day2.Choice.SCISSORS, Day2.Outcome.WIN));
    assertEquals(Day2.Choice.PAPER, Day2.getRequiredChoice(Day2.Choice.SCISSORS, Day2.Outcome.LOSE));
    assertEquals(Day2.Choice.SCISSORS, Day2.getRequiredChoice(Day2.Choice.SCISSORS, Day2.Outcome.DRAW));
  }
}