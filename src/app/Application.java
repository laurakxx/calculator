package app;

import model.Expression;
import service.History;
import service.CalculatorService;
import ui.ConsolePrinter;
import service.InputParser;
import service.Validator;

import java.util.Scanner;

public class Application {
  private final Scanner scanner = new Scanner(System.in);
  private final InputParser inputParser = new InputParser();
  private final CalculatorService calculatorService = new CalculatorService();
  private final ConsolePrinter consolePrinter = new ConsolePrinter();
  private final Validator validator = new Validator();
  private static final String EXIT_COMMAND = "exit";
  private static final String HISTORY_COMMAND = "history";
  private static final String CLEAR_COMMAND = "clear";
  private static final String LAST_COMMAND = "last";
  private static final String HELP_COMMAND = "help";
  private final History history = new History();

  public void run(){
    while (true) {
      String input = readInput();
      if(input.equalsIgnoreCase(EXIT_COMMAND)) {
        break;
      }
      else if(input.equalsIgnoreCase(HISTORY_COMMAND)) {
        consolePrinter.printHistory(history.getHistoryList());
        continue;
      }
      else if(input.equalsIgnoreCase(LAST_COMMAND)) {
        consolePrinter.printLast(history.getLastResult());
        continue;
      }
      else if(input.equalsIgnoreCase(CLEAR_COMMAND)) {
        history.clearHistory();
        consolePrinter.printClearHistory();
        continue;
      }
      else if(input.equalsIgnoreCase(HELP_COMMAND)) {
        consolePrinter.printHelp();
        continue;
      }
      processInput(input);
    }
    scanner.close();
  }
  private String readInput() {
    consolePrinter.printPrompt();
    return scanner.nextLine();
  }
  private void processInput(String input) {
    String[] inputElements = inputParser.parseInput(input);
    String formatError = validator.validateFormat(inputElements);
    if(formatError!=null){
      consolePrinter.printError(formatError);
      return;
    }
    Expression expression = new Expression(inputElements);
    String divisionError = validator.validateDivision(expression);
    if(divisionError!=null) {
      consolePrinter.printError(divisionError);
      return;
    }
    double result = calculatorService.calculate(expression);
    history.addExpression(expression, result);
    consolePrinter.printResult(result);
  }
}
