package service;

import org.junit.jupiter.api.Test;
import model.Expression;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryTest {
  @Test
  void testHistoryStoresLastTen() {
    History history = new History();
    for (int i = 0; i < 11; i++) {
      String[] input = {String.valueOf(i), "+", String.valueOf(i)};
      Expression exp = new Expression(input);
      history.addExpression(exp, i + i);
    }
    List<String> list = history.getHistoryList();
    assertEquals(10, list.size());
  }
}
