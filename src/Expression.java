public class Expression {
  private int num1;
  private int num2;
  private String operator;

  public Expression(String[] parsedInput) {
    this.num1 = Integer.parseInt(parsedInput[0]);
    this.num2 = Integer.parseInt(parsedInput[2]);
    this.operator = parsedInput[1];
  }

  public int getNum1() {
    return num1;
  }

  public int getNum2() {
    return num2;
  }

  public String getOperator() {
    return operator;
  }
}
