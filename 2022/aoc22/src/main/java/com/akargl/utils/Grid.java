package com.akargl.utils;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Grid<T> {
  private List<List<T>> grid;

  public int getWidth() {
    return grid.get(0).size();
  }

  public int getHeight() {
    return grid.size();
  }

  public List<T> getRow(int rowNumber) {
    return grid.get(rowNumber);
  }

  public List<T> getColumn(int columnNumber) {
    return grid.stream().map(row -> row.get(columnNumber)).toList();
  }

  public List<T> getSubRow(int rowNumber, int from, int to) {
    return getRow(rowNumber).subList(from, to);
  }

  public List<T> getSubColumn(int columnNumber, int from, int to) {
    return getColumn(columnNumber).subList(from, to);
  }

  public T getElement(int x, int y) {
    return grid.get(y).get(x);
  }

  public Coordinate findFirstElement(T element) {
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        if (getElement(x, y) == element) {
          return new Coordinate(x, y);
        }
      }
    }

    return null;
  }

  public List<T> getTopColumn(int x, int y) {
    return getSubColumn(x, 0, y);
  }

  public List<T> getBottomColumn(int x, int y) {
    return getSubColumn(x, y + 1, getHeight());
  }

  public List<T> getLeftRow(int x, int y) {
    return getSubRow(y, 0, x);
  }

  public List<T> getRightRow(int x, int y) {
    return getSubRow(y, x + 1, getWidth());
  }
}
