package days.d9;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node {
  @NonNull
  private Integer value;
  private Integer inBasin;
  private boolean isLowPoint;
}
