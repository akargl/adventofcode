package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        List<String> inputLines = InputUtils.getInputLines("inputs/d5_1.txt", true);
        List<List<Integer>> pageOrderingRules = Day5.getPageorderingRules(inputLines);
        List<List<Integer>> updatePageNumbers = Day5.getUpdatePageNumbers(inputLines);

        int sumMiddleNumbers = part1(pageOrderingRules, updatePageNumbers);
        System.out.println(sumMiddleNumbers);
    }

    protected static int part1(List<List<Integer>> pageOrderingRules, List<List<Integer>> updatePageNumbers) {
        List<List<Integer>> updatesForPrint = findUpdatesForPrint(pageOrderingRules, updatePageNumbers);
        return sumMiddleNumbers(updatesForPrint);
    }

    protected static List<List<Integer>> findUpdatesForPrint(List<List<Integer>> pageOrderingRules, List<List<Integer>> updatePageNumbers) {
        return updatePageNumbers.stream()
                .filter(pageNumbers -> {
                    // for each number check all previous numbers for violation with the current number
                    for (int i = 0; i < pageNumbers.size(); i++) {
                        int currentPage = pageNumbers.get(i);
                        for (int j = 0; j < i; j++) {
                            Integer previousPage = pageNumbers.get(j);
                            boolean pageOrderViolation = checkPageOrder(currentPage, previousPage, pageOrderingRules);
                            if (pageOrderViolation) {
                                return false;
                            }
                        }
                    }
                    return true;
                })
                .toList();
    }

    protected static int sumMiddleNumbers(List<List<Integer>> updatePages) {
        return updatePages.stream()
                .map(l -> l.get(l.size()/2))
                .reduce(0, Integer::sum);
    }

    /**
     * check if page1 must be before page2 according to ruleset
     * @param page1
     * @param page2
     * @param pageOrderingRules
     * @return true if there's a rule
     */
    protected static boolean checkPageOrder(int page1, int page2, List<List<Integer>> pageOrderingRules) {
        return pageOrderingRules.stream()
                .anyMatch(x -> x.get(0) == page1 && x.get(1) == page2);
    }

    protected static List<List<Integer>> getPageorderingRules(List<String> inputLines) {
        return inputLines.stream()
                .takeWhile(line -> !line.isEmpty())
                .map(line -> Arrays.stream(line.split("\\|"))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }

    protected static List<List<Integer>> getUpdatePageNumbers(List<String> inputLines) {
        return inputLines.stream()
                .dropWhile(line -> !line.isEmpty())
                .skip(1)
                .map(line -> Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }
}
