import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
  final private Solution sol = new Solution();
  @Test
  void alienOrderExample1() {
    assertEquals("wertf", sol.alienOrder(new String[]{
        "wrt","wrf","er","ett","rftt"
    }));
  }
  @Test
  void alienOrderExample2() {
    assertEquals("zx", sol.alienOrder(new String[]{
        "z","x"
    }));
  }
}