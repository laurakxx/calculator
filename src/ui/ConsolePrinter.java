package ui;

import model.Expression;

import java.util.List;

public class ConsolePrinter {
  public void printResult(double result) {
    System.out.println("Результат: " + result);
    //System.out.println(firstNumber + " " + operator + " " + secondNumber + " = " + result);
  }

  public void printError(String error) {
    System.out.println(error);
  }
  public void printPrompt() {
    System.out.print("> ");
  }
  public void printHistory(List<String> historyList) {
    if(historyList.size()==0) {
      System.out.println("История пуста");
    }
    for(int i=0; i<historyList.size(); i++) {
      System.out.println(i+1+") "+ historyList.get(i));
    }
  }
  public void printLast(Double result) {
    if(result==null) {
      System.out.println("История пуста");
      return;
    }
    printResult(result);
  }
  public void printClearHistory() {
    System.out.println("История очищена");
  }
  public void printHelp() {
    System.out.println("Доступные команды:\n" +
        "<число> <оператор> <число>\n");
    System.out.println("Операторы:\n" +
        "+ сложение\n" +
        "- вычитание\n" +
        "* умножение\n" +
        "/ деление\n");
    System.out.println("Дополнительные команды:\n" +
        "history\n" +
        "last\n" +
        "clear\n" +
        "exit\n");
  }
}
