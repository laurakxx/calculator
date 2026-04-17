import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Calculator calculator = new Calculator();
    while (calculator.processInput(scanner)){
    }
    scanner.close();
  }
}