package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Day2 {
    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d2_1.txt");

        List<List<Integer>> reports = parseInput(inputLines);

        long safeReportCount = getSafeReportCount(reports, Day2::isSafeReport);
        System.out.println(safeReportCount);

        long safeReportCountP2 = getSafeReportCount(reports, Day2::isSafeReportP2);
        System.out.println(safeReportCountP2);
    }

    protected static long getSafeReportCount(List<List<Integer>> reports, Function<List<Integer>, Boolean> safeReportFunction) {
        return reports.stream()
                .map(safeReportFunction)
                .filter(x -> x)
                .count();
    }

    protected static boolean isSafeReportP2(List<Integer> report) {
        return isSafeReport(report) || isSafeWithOneBadLevel(report);
    }

    protected static boolean isSafeWithOneBadLevel(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> tempReport = new ArrayList<>(report);
            //noinspection SuspiciousListRemoveInLoop
            tempReport.remove(i);
            if (isSafeReport(tempReport)) {
                return true;
            }
        }

        return false;
    }

    protected static boolean isSafeReport(List<Integer> report) {
        int direction = Integer.signum(report.get(0) - report.get(1));

        for (int i = 0; i < report.size() -1; i++) {
            int diff = report.get(i) - report.get(i + 1);

            //values must not be the same and not differ by more than 3
            if (diff == 0 || Math.abs(diff) > 3) {
                return false;
            }

            //values have to be continuously increasing or decreasing
            if (Integer.signum(diff) != direction) {
                return false;
            }
        }

        return true;
    }

    protected static List<List<Integer>> parseInput(List<String> inputLines) {
        return inputLines.stream()
                .map(l -> Arrays.stream(l.split(" "))
                        .map(Integer::parseInt).toList())
                .toList();
    }
}
