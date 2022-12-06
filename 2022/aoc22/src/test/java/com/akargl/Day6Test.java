package com.akargl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

  @Test
  void findMarkerPosition() {
    assertEquals(7, Day6.findMarkerPosition("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4));
    assertEquals(5, Day6.findMarkerPosition("bvwbjplbgvbhsrlpgdmjqwftvncz", 4));
    assertEquals(6, Day6.findMarkerPosition("nppdvjthqldpwncqszvftbrmjlhg", 4));
    assertEquals(10, Day6.findMarkerPosition("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4));
    assertEquals(11, Day6.findMarkerPosition("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4));

    assertEquals(19, Day6.findMarkerPosition("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14));
    assertEquals(23, Day6.findMarkerPosition("bvwbjplbgvbhsrlpgdmjqwftvncz", 14));
    assertEquals(23, Day6.findMarkerPosition("nppdvjthqldpwncqszvftbrmjlhg", 14));
    assertEquals(29, Day6.findMarkerPosition("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14));
    assertEquals(26, Day6.findMarkerPosition("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14));
  }
}