package days.d9;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Visualizer {
  final static Color HEIGHT_START_COLOR = Color.white;
  final static Color CURRENT_NODE_COLOR = Color.red;
  final static Color LOW_POINT_COLOR = Color.orange;
  final static Color BASIN_COLOR = Color.blue;
  final static int MAX_HEIGHT = 9;

  private Color[] heightColors;
  private int imgCount = 0;
  private String outputDirectory;
  private String imageExtension = "png";
  private String namePrefix  = "";

  public Visualizer(String outputDirectory) throws IOException {
    initHeightColors();
    this.outputDirectory = outputDirectory;

    clearOutputDirectory();
  }

  public void clearOutputDirectory() throws IOException {
    List<Path> oldFilesToDelete = Files.list(Path.of(outputDirectory)).filter(p -> p.toString().endsWith("." + imageExtension)).toList();
    for (Path path : oldFilesToDelete) {
      Files.deleteIfExists(path);
    }
  }

  private void initHeightColors() {
    this.heightColors = new Color[MAX_HEIGHT +1];

    heightColors[0] = HEIGHT_START_COLOR;
    for (int i = 1; i < heightColors.length; i++) {
      heightColors[i] = heightColors[i-1].darker();
    }
  }

  public BufferedImage imgFromHeightMap(List<List<Node>> heightmap, Integer currentRow, Integer currentCol) {
    BufferedImage img = new BufferedImage(heightmap.get(0).size(), heightmap.size(), BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < heightmap.size(); row++) {
      for (int col = 0; col < heightmap.get(row).size(); col++) {
        Node node = heightmap.get(row).get(col);

        Color color = heightColors[node.getValue()];
        if (node.getInBasin() != null) {
          color = BASIN_COLOR;
        }
        if (node.isLowPoint()) {
          color = LOW_POINT_COLOR;
        }
        img.setRGB(col, row, color.getRGB());
      }
    }

    if (currentRow != null && currentCol != null) {
      img.setRGB(currentCol, currentRow, CURRENT_NODE_COLOR.getRGB());
    }

    return img;
  }

  public void saveImg(BufferedImage img) throws IOException {
    String fName = namePrefix + imgCount + "." + imageExtension;
    Path fPath = Path.of(outputDirectory, fName);
    imgCount++;
    File file = new File(fPath.toString());
    ImageIO.write(img, imageExtension, file);
  }
}
