package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.Grid;
import com.akargl.aoc24.utils.InputUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day12 {
    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d12_1.txt");

        Grid<GridEntry> grid = new Grid<>(inputLines.stream()
                .map(l -> Arrays.stream(l.split(""))
                        .map(x -> new GridEntry(x, false))
                        .toList())
                .toList());

        List<Set<Coordinate>> areas = new ArrayList<>();

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Set<Coordinate> area = getArea(new Coordinate(x, y), grid);
                if (area != null) {
                    areas.add(area);
                }
            }
        }

        long totalPriceP1 = areas.stream()
                .mapToLong(area -> getPerimeterSize(area) * area.size())
                .sum();

        System.out.println("Part1 " + totalPriceP1);
    }

    protected static Set<Coordinate> getArea(Coordinate coord, Grid<GridEntry> grid) {
        GridEntry current = grid.getElement(coord);
        if (current == null || current.isVisited()) {
            return null;
        }

        current.setVisited(true);

        Set<Coordinate> area = new HashSet<>();
        area.add(coord);

        for (Coordinate c : List.of(coord.getTop(), coord.getRight(), coord.getBottom(), coord.getLeft())) {
            if (c == null) {
                continue;
            }

            GridEntry element = grid.getElement(c);
            if (element != null && !element.isVisited() && element.getPlantType().equals(current.getPlantType())) {
                Set<Coordinate> newArea = getArea(c, grid);
                if (newArea != null) {
                    area.addAll(newArea);
                }
            }
        }

        return area;
    }

    protected static long getPerimeterSize(Set<Coordinate> coords) {
        //count neighbours not in the set of area coordinates
        return coords.stream()
                .mapToLong(coord -> Stream.of(coord.getTop(), coord.getRight(), coord.getBottom(), coord.getLeft())
                    .filter(c -> !coords.contains(c))
                    .count())
                .sum();
    }

    @Data
    @AllArgsConstructor
    protected static final class GridEntry {
        private String plantType;
        private boolean visited;
    }

}
