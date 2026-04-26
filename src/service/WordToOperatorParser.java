package service;

import java.util.Map;

public class WordToOperatorParser {
  private static final Map<String, String> OPERATORS = Map.of(
      "умножить", "*",
      "на", "*",
      "разделить", "/",
      "делить", "/",
      "плюс", "+",
      "минус", "-"
  );

  public static Map<String, String> getOperators() {
    return OPERATORS;
  }
}
