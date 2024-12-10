package com.akargl.aoc24;

import com.akargl.aoc24.utils.Coordinate;
import com.akargl.aoc24.utils.InputUtils;
import com.akargl.aoc24.utils.IntegerGrid;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d10_1.txt");
        IntegerGrid grid = new IntegerGrid(inputLines);

        List<Coordinate> trailheads = grid.getCoordinatesWhere(h -> h == 0);

        Integer sumTrailheadScores = trailheads.stream()
                .map(th -> findPeaks(grid, th).size())
                .reduce(0, Integer::sum);

        System.out.println("Part 1: " + sumTrailheadScores);
    }

    protected static Set<Coordinate> findPeaks(IntegerGrid grid, Coordinate currentPosition) {
        if (currentPosition == null) {
            return null;
        }

        Integer currentHeight = grid.getElement(currentPosition);
        if (currentHeight == 9) {
            return Set.of(currentPosition);
        }

        List<Coordinate> reachablePositions = List.of(currentPosition.getTop(), currentPosition.getRight(), currentPosition.getBottom(), currentPosition.getLeft()).stream()
                .filter(p -> grid.getElement(p) != null && grid.getElement(p) == currentHeight + 1)
                .toList();

        return reachablePositions.stream()
                .map(p -> findPeaks(grid, p))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
