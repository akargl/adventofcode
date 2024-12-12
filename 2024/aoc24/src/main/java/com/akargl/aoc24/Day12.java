package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.Grid;
import com.akargl.aoc24.utils.InputUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day12 {
    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d12_1.txt");

        Grid<GridEntry> grid = new Grid<>(inputLines.stream()
                .map(l -> Arrays.stream(l.split(""))
                        .map(x -> new GridEntry(x, false))
                        .toList())
                .toList());

        List<Area> areas = new ArrayList<>();

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Set<Coordinate> areaCoords = getArea(new Coordinate(x, y), grid);
                if (areaCoords != null && !areaCoords.isEmpty()) {
                    String plantType = grid.getElement(areaCoords.stream().findFirst().get()).getPlantType();
                    areas.add(new Area(areaCoords, plantType));
                }
            }
        }

        long totalPriceP1 = areas.stream()
                .mapToLong(Area::getPriceP1)
                .sum();

        System.out.println("Part1 " + totalPriceP1);


        for (int x = 0; x < grid.getWidth(); x++) {
            for (Area area : areas) {
                //get all coordinates in area that have no horizontal neighbour in area
                countVerticalEdges(area, x, Coordinate::getLeft);
                countVerticalEdges(area, x, Coordinate::getRight);
            }
        }

        for (int y = 0; y < grid.getHeight(); y++) {
            for (Area area : areas) {
                //get all coordinates in area that have no horizontal neighbour in area
                countHorizontalEdges(area, y, Coordinate::getTop);
                countHorizontalEdges(area, y, Coordinate::getBottom);
            }
        }

        long totalPriceP2 = areas.stream().mapToLong(a -> a.getNumEdges() * a.getCoords().size()).sum();

        System.out.println("Part2 " + totalPriceP2);
    }

    private static void countVerticalEdges(Area area, int x, Function<Coordinate, Coordinate> edgeToCheck) {
        final int finalX = x;
        List<Coordinate> sortedEdgeCoords = area.getCoords().stream()
            .filter(c -> c.getX() == finalX)
            .filter(c -> !area.getCoords().contains(edgeToCheck.apply(c)))
            .sorted(Comparator.comparingInt(Coordinate::getY))
            .toList();

        if (sortedEdgeCoords.isEmpty()) {
            return;
        }

        if (sortedEdgeCoords.size() == 1) {
            area.setNumEdges(area.getNumEdges() + 1);
        } else {
            List<Integer> yDiffs = IntStream.range(0, sortedEdgeCoords.size() - 1)
                .mapToObj(i -> sortedEdgeCoords.get(i + 1).getY() - sortedEdgeCoords.get(i).getY())
                .toList();
            long yGaps = yDiffs.stream().filter(diff -> diff > 1).count();
            area.setNumEdges(area.getNumEdges() + yGaps + 1);
        }
    }

    private static void countHorizontalEdges(Area area, int y, Function<Coordinate, Coordinate> edgeToCheck) {
        final int finalY = y;
        List<Coordinate> sortedEdgeCoords = area.getCoords().stream()
            .filter(c -> c.getY() == finalY)
            .filter(c -> !area.getCoords().contains(edgeToCheck.apply(c)))
            .sorted(Comparator.comparingInt(Coordinate::getX))
            .toList();

        if (sortedEdgeCoords.isEmpty()) {
            return;
        }

        if (sortedEdgeCoords.size() == 1) {
            area.setNumEdges(area.getNumEdges() + 1);
        } else {
            List<Integer> xDiffs = IntStream.range(0, sortedEdgeCoords.size() - 1)
                .mapToObj(i -> sortedEdgeCoords.get(i + 1).getX() - sortedEdgeCoords.get(i).getX())
                .toList();
            long xGaps = xDiffs.stream().filter(diff -> diff > 1).count();
            area.setNumEdges(area.getNumEdges() + xGaps + 1);
        }
    }

    protected static Set<Coordinate> getArea(Coordinate coord, Grid<GridEntry> grid) {
        GridEntry current = grid.getElement(coord);
        if (current == null || current.isAssignedToArea()) {
            return null;
        }

        current.setAssignedToArea(true);

        Set<Coordinate> area = new HashSet<>();
        area.add(coord);

        for (Coordinate c : List.of(coord.getTop(), coord.getRight(), coord.getBottom(), coord.getLeft())) {
            if (c == null) {
                continue;
            }

            GridEntry element = grid.getElement(c);
            if (element != null && !element.isAssignedToArea() && element.getPlantType().equals(current.getPlantType())) {
                Set<Coordinate> newArea = getArea(c, grid);
                if (newArea != null) {
                    area.addAll(newArea);
                }
            }
        }

        return area;
    }

    @Data
    @AllArgsConstructor
    protected static class Area {
        private String plantType;
        private long numEdges = 0L;
        private Set<Coordinate> coords;

        public Area(Set<Coordinate> coords, String plantType) {
            this.coords = coords;
            this.plantType = plantType;
        }

        protected long getPerimeterSize() {
            //count neighbours not in the set of area coordinates
            return coords.stream()
                .mapToLong(coord -> Stream.of(coord.getTop(), coord.getRight(), coord.getBottom(), coord.getLeft())
                    .filter(c -> !coords.contains(c))
                    .count())
                .sum();
        }

        protected long getPriceP1() {
            return getPerimeterSize() * coords.size();
        }
    }

    @Data
    @AllArgsConstructor
    protected static final class GridEntry {
        private String plantType;
        private boolean assignedToArea;
    }
}
