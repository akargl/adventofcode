package com.akargl.aoc24;

import com.akargl.aoc24.utils.InputUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String input = InputUtils.getInput("inputs/d3_1.txt");

        List<List<Integer>> mulPairs = parseInputToMultiplications(input);
        int p1Result = p1CalcResult(mulPairs);
        System.out.println("Part 1: " + p1Result);

        int p2CalcResult = p2CalcResult(input);
        System.out.println("Part 2: " + p2CalcResult);
    }

    protected static int p1CalcResult(List<List<Integer>> mulPairs) {
        return mulPairs.stream()
                .map(p -> p.get(0) * p.get(1))
                .reduce(0, Integer::sum);
    }

    protected static int p2CalcResult(String input) {
        List<String> conditionalSections = parseInputToConditionalSections(input);

        return conditionalSections.stream()
                .map(Day3::parseInputToMultiplications)
                .map(Day3::p1CalcResult)
                .reduce(0, Integer::sum);
    }

    protected static List<List<Integer>> parseInputToMultiplications(String input) {
        List<List<Integer>> result = new ArrayList<>();

        final String regex = "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(List.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }

        return result;
    }

    protected static List<String> parseInputToConditionalSections(String input) {
        List<String> result = new ArrayList<>();

        final String regex = "(?>do\\(\\)|^).*?(mul\\(\\d+,\\d+\\))*.*?(?>don't\\(\\)|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            result.add(matcher.group(0));
        }

        return result;
    }
}
