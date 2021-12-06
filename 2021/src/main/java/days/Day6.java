package days;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.StopWatch;

import utils.InputUtils;

public class Day6 {

  static long breedFishv1(List<Integer> fish, int days) {
    Instant startTime = Instant.now();

    for (int day = 0; day < days; day++) {
      fish = fish.stream().<Integer>mapMulti((f, consumer) -> {
        f--;
        if (f < 0) {
          f = 6;
          consumer.accept(8);
        }
        consumer.accept(f);
      }).collect(Collectors.toList());
    }

    Instant stopTime = Instant.now();
    System.out.println("Took " + Duration.between(startTime, stopTime).toMillis() + "ms");

    return fish.size();
  }

  static long breedFishv2(List<Integer> fish, int days) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    long[] fishPerDaysUntilBreeding = new long[10];

    for (Integer integer : fish) {
      fishPerDaysUntilBreeding[integer]++;
    }

    for (int day = 0; day < days; day++) {
      long amountOfFishBreedingToday = fishPerDaysUntilBreeding[0];
      fishPerDaysUntilBreeding[0] = fishPerDaysUntilBreeding[1];
      fishPerDaysUntilBreeding[1] = fishPerDaysUntilBreeding[2];
      fishPerDaysUntilBreeding[2] = fishPerDaysUntilBreeding[3];
      fishPerDaysUntilBreeding[3] = fishPerDaysUntilBreeding[4];
      fishPerDaysUntilBreeding[4] = fishPerDaysUntilBreeding[5];
      fishPerDaysUntilBreeding[5] = fishPerDaysUntilBreeding[6];
      fishPerDaysUntilBreeding[6] = fishPerDaysUntilBreeding[7] + amountOfFishBreedingToday;
      fishPerDaysUntilBreeding[7] = fishPerDaysUntilBreeding[8];
      fishPerDaysUntilBreeding[8] = amountOfFishBreedingToday;
    }

    //Long amountOfFish = Arrays.stream(fishPerDaysUntilBreeding).sum();
    long amountOfFish = 0L;
    for (int i = 0; i < 9; i++) {
      amountOfFish += fishPerDaysUntilBreeding[i];
    }

    stopWatch.stop();
    System.out.println("Took " + stopWatch.getNanoTime() + " ns");

    return amountOfFish;
  }

  public static void main(String[] args) throws IOException {

    List<String> inputLines = InputUtils.getInputLines(Path.of("inputs/input6_1.txt"), false);
    List<Integer> fish = Arrays.stream(inputLines.get(0).split(",")).map(Integer::parseInt).toList();

    final int DAYS_TO_SIMULATE = 256;
    long amountOfFishv2 = breedFishv2(fish, DAYS_TO_SIMULATE);

    System.out.println("Number of fish v2: " + amountOfFishv2);
  }
}
