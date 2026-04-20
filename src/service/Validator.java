package service;

import model.Expression;

public class Validator {
  private static final String[] SUPPORTED_OPERATORS = {"+", "-", "*", "/"};
  private static final String FORMAT_ERROR = "Ошибка: неверный формат. Используйте: число оператор число";
  private static final String MATH_ERROR = "Ошибка: деление на ноль";
  public String validateFormat(String[] str) {

    if(str.length!=3) {
      return FORMAT_ERROR;
    }
    else if(!(isNumeric(str[0]) && isNumeric(str[2]))) {
      return FORMAT_ERROR;
    }
    else if(!isPermittedSign(str[1])) {
      return FORMAT_ERROR;
    }
    return null;
  }
  private static boolean isNumeric(String num) {
    try{
      Double.parseDouble(num);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  private boolean isPermittedSign(String str) {
    for(String sign: SUPPORTED_OPERATORS) {
      if(str.equals(sign)) {
        return true;
      }
    }
    return false;
  }
  public String validateDivision(Expression expression) {
    if(expression.getSecondNumber()==0.0 && expression.getOperator().equals("/")) {
      return MATH_ERROR;
    }
    return null;
  }
}
