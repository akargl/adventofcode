package com.akargl.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
      return null;
    }

    return grid.get(y).get(x);
  }

  public T getElement(Coordinate coordinate) {
    return getElement(coordinate.x, coordinate.y);
  }

  public Coordinate findFirstCoordinates(T element) {
    List<Coordinate> coordinates = getCoordinatesWhere(t -> t.equals(element));

    if (coordinates.isEmpty()) {
      return null;
    }
    return coordinates.get(0);
  }

  public List<Coordinate> getCoordinatesWhere(Predicate<T> filter) {
    List<Coordinate> results = new ArrayList<>();

    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        if (filter.test(getElement(x, y))) {
          results.add(new Coordinate(x, y));
        }
      }
    }

    return results;
  }

  public List<T> getElementsWhere(Predicate<T> filter) {
    return getCoordinatesWhere(filter).stream().map(this::getElement).toList();
  }

  public void forEach(BiConsumer<T, Coordinate> f) {
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        Coordinate coordinate = new Coordinate(x, y);
        f.accept(getElement(coordinate), coordinate);
      }
    }
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
