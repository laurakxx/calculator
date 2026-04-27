package service;

import model.Expression;

import java.util.ArrayList;
import java.util.List;

public class History {
  private final List<String> historyList = new ArrayList<>();
  private static final int MAX_HISTORY_SIZE = 10;
  private Double lastResult;
  public List<String> getHistoryList(){
    return new ArrayList<>(historyList);
  }
  public String getLastResult() {
    if(historyList.size()==0){
      return null;
    }
    return String.valueOf(lastResult);
  }
  public void clearHistory() {
    historyList.clear();
    lastResult = null;
  }
  public void addExpression(Expression exp, double result) {
    lastResult = result;
    if(historyList.size()>=MAX_HISTORY_SIZE) {
      historyList.remove(0);
    }
    String str = exp.getFirstNumber() + " " + exp.getOperator() + " " + exp.getSecondNumber() + " = " + result;
    historyList.add(str);
  }
}
