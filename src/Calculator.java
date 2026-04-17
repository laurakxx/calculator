import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
  Expression expression;

  public boolean processInput(Scanner scanner) {
    System.out.print("> ");
    String input = readInput(scanner);
    if(input.equals("exit")) return false;
    String[] parsedInput = parseInput(input);
    if(isValidFormat(parsedInput)) {
      expression = new Expression(parsedInput);
      if(isMathMistake(expression)) {
        double result = calculate(expression);
        printResult(result);
      }
    }
    return true;
  }

  public String readInput(Scanner scanner) {
    String input = scanner.nextLine();
    return input;
  }

  public String[] parseInput(String input) {
    input = input.replaceAll("\\s+", "");
    String regex = "(?<=[+*/-])|(?=[+*/-])";
    String[] parsedInput = input.split(regex);
    return (parsedInput);
  }
  public boolean isMathMistake(Expression expression1) {
    if(expression1.getNum2()==0&& expression1.getOperator().equals("/")) {
      System.out.println("Ошибка: деление на ноль");
      return false;
    }
    return true;
  }
  public boolean isValidFormat(String[] str) {
    String formatError = "Ошибка: неверный формат. Используйте: число оператор число";
    if(str.length!=3) {
      System.out.println(formatError);
      return false;
    }
    if(!(isNumeric(str[0]) && isNumeric(str[2]))) {
      System.out.println(formatError);
      return false;
    }
    return true;
  }
  public static boolean isNumeric(String num) {
    try{
      Integer.parseInt(num);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }

  public double calculate(Expression expression) {
    double result=0.0;
    int num1 = expression.getNum1();
    int num2 = expression.getNum2();
    String operator = expression.getOperator();

    switch (operator) {
      case "+": result = num1+num2; break;
      case "-": result = num1-num2; break;
      case "*": result = num1*num2; break;
      case "/": result = num1/num2; break;
    }
    return result;
  }
  public void printResult(Double result) {
    int num1 = expression.getNum1();
    int num2 = expression.getNum2();
    String operator = expression.getOperator();
    System.out.println(num1 + " "+operator+" " + num2+" = "+result);
  }

}
