package service;

public class InputParser {

  private static final WordToOperatorParser wordToOperatorParser = new WordToOperatorParser();
  private static final WordNumberParser wordNumberParser = new WordNumberParser();

  public String[] parseInput(String input) {
    input = input.toLowerCase();
    String[] parts = input.trim().split("\\s+");
    int index = findIndex(input, parts); //индекс оператора
    if(index<0) {
      return new String[]{"invalid"};
    }
    if(findIndexInMass(parts)>=0) {
      parts = normalizeMass(parts, index); //преобразование оператора и приведение к правильной форме
    }
    else {
      parts = normalizeStr(input, index); //преобразование оператора и приведение к правильной форме
    }
    index = findIndexInMass(parts); //пересчет индекса для случаев, оператор склеивался и искали его в строке
    parts = wordNumberParser.wordsToNumber(parts, index); //преобразование строковых чисел в обычные
    return parts;
  }

  private String[] normalizeStr(String str, int index) { //для случаев, когда у символьного оператора нет пробела
    String newStr = str.substring(0, index) +" " + str.substring(index, index+1)+ " "+ str.substring(index+1, str.length());
    String [] resultMass = newStr.split("\\s+");
    return resultMass;

  }
  private String[] normalizeMass(String[]mass, int index) { //для обычных случае
    if(isOperator(mass[index])) {
      return mass;
    }
    mass[index] = wordToOperatorParser.getOperators().get(mass[index]);
    if(hasWordToDelete(mass, index)) {
      mass = deleteByIndex(mass, index);
    }
    return mass;
  }

  private boolean isOperator(String operator) {
    return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
  }

  private int findIndexOfSymbolOperatorInStr(String str) { //поиск в подобных случаях: "118+ 1"
    for(int i=0; i<str.length(); i++){
      if(isOperator(String.valueOf(str.charAt(i)))) {
        return i;
      }
    }
    return -1;
  }
  private int findIndex(String str, String[] strings) {
    int index = findIndexInMass(strings);
    if(index<0) {
      index = findIndexOfSymbolOperatorInStr(str);
    }
    return index;
  }
  private int findIndexInMass(String[] input) { //поиск оператора в массиве
    for(int i=0; i<input.length; i++){
      if(isOperator(input[i])) {
        return i;
      }
      String str = wordToOperatorParser.getOperators().get(input[i]);
      if(str!=null) {
        return i;
      }
    }
    return -1;
  }

  private boolean hasWordToDelete(String[] str, int index){ //для слов "на"
    if((index + 1 < str.length) && (str[index+1].equals("на")) && (str[index].equals("/")||str[index].equals("*"))) {
      return true;
    }
    return false;
  }
  private String[] deleteByIndex(String[] input, int index) { //для удаления в случаях "делить на"
    String[] changedInput = new String[input.length-1];
    for(int i=0; i<changedInput.length; i++) {
      if (i == index+1) {
        changedInput[i] = input[i + 1];
        continue;
      }
      changedInput[i] = input[i];
    }
    return changedInput;
  }
}



//    input = input.replaceAll("\\s+", "");
//    String regex = "[,;\\s]+";
//    String[] parsedInput = input.split(regex);
