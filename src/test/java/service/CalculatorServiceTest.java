package service;


import model.Expression;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {
  @Test
  void testAddition() {
    Expression expr = new Expression(new String[]{"5", "+", "3"});

    assertEquals(8.0, CalculatorService.calculate(expr));
  }
  @Test
  void testDivision() {
    Expression expr = new Expression(new String[]{"5", "/", "2"});
    assertEquals(2.5, CalculatorService.calculate(expr));
  }
  @Test
  void testDivisionByZero() {
    Expression expr = new Expression(new String[]{"5", "/", "0"});
    String error = Validator.validateDivision(expr);
    assertEquals("Ошибка: деление на ноль", error);
  }
}
