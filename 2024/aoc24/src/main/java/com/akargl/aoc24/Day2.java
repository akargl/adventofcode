package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        for (int i = 0; i < report.size() -1; i++) {
            // numbers must be different
            if (report.get(i).equals(report.get(i + 1))) {
                return false;
            }
            //neighbors must not differ more than three
            if (Math.abs(report.get(i) - report.get(i + 1)) > 3) {
                return false;
            }
        }

        boolean sortedAscending = report.stream().sorted().toList().equals(report);
        boolean sortedDescending = report.stream().sorted(Comparator.reverseOrder()).toList().equals(report);

        return sortedAscending || sortedDescending;
    }

    protected static List<List<Integer>> parseInput(List<String> inputLines) {
        return inputLines.stream()
                .map(l -> Arrays.stream(l.split(" "))
                        .map(Integer::parseInt).toList())
                .toList();
    }
}
