package model;

public class Expression {
  private final double firstNumber;
  private final double secondNumber;
  private final String operator;

  public Expression(String[] parsedInput) {
    this.firstNumber = Double.parseDouble(parsedInput[0]);
    this.secondNumber = Double.parseDouble(parsedInput[2]);
    this.operator = parsedInput[1];
  }

  public double getFirstNumber() {return firstNumber;}
  public double getSecondNumber() {
    return secondNumber;
  }
  public String getOperator() {
    return operator;
  }
}
