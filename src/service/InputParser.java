package service;

public class InputParser {
  public String[] parseInput(String input) {
    input = input.replaceAll("\\s+", "");
    int operatorIndex = -1;
    for(int i=0; i<input.length(); i++) {
      if(isOperator(input.charAt(i))) {
        operatorIndex = i;
        break;
      }
    }
    if(operatorIndex==-1) {
      return new String[]{input};
    }
    return new String[] {
        input.substring(0, operatorIndex),
        input.substring(operatorIndex, operatorIndex+1),
        input.substring(operatorIndex+1)
    };
  }

  private boolean isOperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
  }


}

//    input = input.replaceAll("\\s+", "");
//    String regex = "[,;\\s]+";
//    String[] parsedInput = input.split(regex);
