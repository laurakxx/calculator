package service;

import model.Expression;

public class Validator {
  private static final String[] SUPPORTED_OPERATORS = {"+", "-", "*", "/"};
  private static final String FORMAT_ERROR = "Ошибка: неверный формат. Используйте: число оператор число";
  private static final String MATH_ERROR = "Ошибка: деление на ноль";
  public static String validateFormat(String[] strings) {

    if(strings ==null || strings.length!=3) {
      return FORMAT_ERROR;
    }
    else if(!(isNumeric(strings[0]) && isNumeric(strings[2]))) {
      return FORMAT_ERROR;
    }
    else if(!isPermittedSign(strings[1])) {
      return FORMAT_ERROR;
    }
    return null;
  }
  static boolean isNumeric(String num) {
    try{
      Double.parseDouble(num);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }

  private static boolean isPermittedSign(String str) {
    for(String sign: SUPPORTED_OPERATORS) {
      if(str.equals(sign)) {
        return true;
      }
    }
    return false;
  }
  public static String validateDivision(Expression expression) {
    if(expression.getSecondNumber()==0.0 && expression.getOperator().equals("/")) {
      return MATH_ERROR;
    }
    return null;
  }
}
