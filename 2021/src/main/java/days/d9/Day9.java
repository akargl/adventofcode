package days.d9;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import utils.InputUtils;

public class Day9 {
  static Visualizer visualizer;

  static {
    try {
      visualizer = new Visualizer("outputs/day9/");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void doFloodFill(List<List<Node>> heightmap, int col, int row, int currentBasin) throws IOException {
    visualizer.saveImg(visualizer.imgFromHeightMap(heightmap, row, col));
    if (heightmap.get(row).get(col).getInBasin() != null || heightmap.get(row).get(col).getValue() == 9) {
      return;
    }

    heightmap.get(row).get(col).setInBasin(currentBasin);

    if (row < heightmap.size() -1) {
      doFloodFill(heightmap, col, row +1, currentBasin);
    }
    if (row > 0) {
      doFloodFill(heightmap, col, row -1, currentBasin);
    }
    if (col > 0) {
      doFloodFill(heightmap, col -1, row, currentBasin);
    }
    if (col < heightmap.get(row).size() -1) {
      doFloodFill(heightmap, col +1, row, currentBasin);
    }
  }

  public static void main(String[] args) throws IOException {
    List<List<Node>> heightmap = InputUtils.getInputLines(Path.of("inputs/input9_1.txt"), false).stream()
        .map(l -> Arrays.stream(l.split(""))
            .map(x -> new Node(Integer.parseInt(x))).toList())
        .toList();

    int sumOfRiskLevels = 0;
    int basinCounter = 0;

    for (int row = 0; row < heightmap.size(); row++) {
      for (int col = 0; col < heightmap.get(0).size(); col++) {
        Node currentElement = heightmap.get(row).get(col);

        if ((row > 0 && heightmap.get(row -1).get(col).getValue() <= currentElement.getValue()) ||
          (row < heightmap.size() -1 && heightmap.get(row +1).get(col).getValue() <= currentElement.getValue()) ||
          (col > 0 && heightmap.get(row).get(col -1).getValue() <= currentElement.getValue()) ||
          (col < heightmap.get(row).size() -1 && heightmap.get(row).get(col +1).getValue() <= currentElement.getValue())) {
          continue;
        }

        currentElement.setLowPoint(true);

        sumOfRiskLevels += currentElement.getValue() +1;

        doFloodFill(heightmap, col, row, basinCounter++);
      }
    }

    //visualizer.saveImg(visualizer.imgFromHeightMap(heightmap, null, null));

    System.out.println("Sum of risk levels: " + sumOfRiskLevels);

    List<Long> basinSizes = new ArrayList<>();
    for (int basinIndex = 0; basinIndex < basinCounter; basinIndex++) {
      Integer finalBasinIndex = basinIndex;
      long basinCount = heightmap.stream().flatMap(Collection::stream).filter(n -> finalBasinIndex.equals(n.getInBasin())).count();
      basinSizes.add(basinCount);
    }

    Collections.sort(basinSizes);
    Collections.reverse(basinSizes);

    long resultP2 = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);

    System.out.println("Part2: "+ resultP2);
  }
}
