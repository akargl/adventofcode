package com.akargl.utils;

import com.akargl.Day9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Coordinate {
  public int x = 0;
  public int y = 0;

  public Coordinate(Coordinate orig) {
    x = orig.getX();
    y = orig.getY();
  }
}
