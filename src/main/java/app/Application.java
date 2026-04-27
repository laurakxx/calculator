package app;

import model.Expression;
import service.*;
import ui.ConsolePrinter;

import java.util.Scanner;

public class Application {
  private final Scanner scanner = new Scanner(System.in);
  private final InputParser inputParser = new InputParser();
  private final ConsolePrinter consolePrinter = new ConsolePrinter();
  private final History history = new History();

  private static final String EXIT_COMMAND = "exit";
  private static final String HISTORY_COMMAND = "history";
  private static final String CLEAR_COMMAND = "clear";
  private static final String LAST_COMMAND = "last";
  private static final String HELP_COMMAND = "help";

  private final WordNumberParser wordNumberParser = new WordNumberParser();
  public void run(){
    while (true) {
      String input = readInput();
      if(input.equalsIgnoreCase(EXIT_COMMAND)) {
        break;
      }
      else if(hasOtherCommand(input)) {
        continue;
      }
      processInput(input);
    }
    scanner.close();
  }
  private boolean hasOtherCommand(String input) {
    if(input.equalsIgnoreCase(HISTORY_COMMAND)) {
      consolePrinter.printHistory(history.getHistoryList());
      return true;
    }
    else if(input.equalsIgnoreCase(LAST_COMMAND)) {
      consolePrinter.printLast(history.getLastResult());
      return true;
    }
    else if(input.equalsIgnoreCase(CLEAR_COMMAND)) {
      history.clearHistory();
      consolePrinter.printClearHistory();
      return true;
    }
    else if(input.equalsIgnoreCase(HELP_COMMAND)) {
      consolePrinter.printHelp();
      return true;
    }
    return false;
  }
  private String readInput() {
    consolePrinter.printPrompt();
    return scanner.nextLine();
  }
  private void processInput(String input) {
    String[] inputElements = inputParser.parseInput(input);
    String formatError = Validator.validateFormat(inputElements);
    if(formatError!=null){
      consolePrinter.printError(formatError);
      return;
    }
    Expression expression = new Expression(inputElements);
    String divisionError = Validator.validateDivision(expression);
    if(divisionError!=null) {
      consolePrinter.printError(divisionError);
      return;
    }
    double result = CalculatorService.calculate(expression);
    print(expression, result);
  }
  private void print(Expression exp, double result) {
    if(result == (int) result) {
      int res = (int) result;
      String strResult = wordNumberParser.numberToString(res);
      consolePrinter.printResult(strResult);
    }
    else{
      consolePrinter.printResultByNumbers(exp.getFirstNumber(), exp.getOperator(), exp.getSecondNumber(), result);
    }
    history.addExpression(exp, result);
  }
}
