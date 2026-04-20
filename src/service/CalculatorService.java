package service;

import model.Expression;

public class CalculatorService {
  public double calculate(Expression expression) {
    double result;
    double firstNumber = expression.getFirstNumber();
    double secondNumber = expression.getSecondNumber();
    String operator = expression.getOperator();

    switch (operator) {
      case "+": result = firstNumber + secondNumber; break;
      case "-": result = firstNumber - secondNumber; break;
      case "*": result = firstNumber * secondNumber; break;
      case "/": result = firstNumber / secondNumber; break;
      default: throw new IllegalArgumentException("Not permitted operation "+operator);
    }
    return result;
  }
}
