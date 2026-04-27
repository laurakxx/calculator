package service;

import java.util.Map;

import static java.lang.Math.abs;

public class WordNumberParser {
  private static final Map<String, Integer> NUMBERS = Map.ofEntries(
      Map.entry("одиннадцать", 11),
      Map.entry("двенадцать", 12),
      Map.entry("тринадцать", 13),
      Map.entry("четырнадцать", 14),
      Map.entry("пятнадцать", 15),
      Map.entry("шестнадцать", 16),
      Map.entry("семнадцать", 17),
      Map.entry("восемнадцать", 18),
      Map.entry("девятнадцать", 19),
      Map.entry("ноль", 0),
      Map.entry("один", 1),
      Map.entry("два", 2),
      Map.entry("три", 3),
      Map.entry("четыре", 4),
      Map.entry("пять", 5),
      Map.entry("шесть", 6),
      Map.entry("семь", 7),
      Map.entry("восемь", 8),
      Map.entry("девять", 9),
      Map.entry("десять", 10),
      Map.entry("двадцать", 20),
      Map.entry("тридцать", 30),
      Map.entry("сорок", 40),
      Map.entry("пятьдесят", 50),
      Map.entry("шестьдесят", 60),
      Map.entry("семьдесят", 70),
      Map.entry("восемьдесят", 80),
      Map.entry("девяносто", 90),
      Map.entry("сто", 100),
      Map.entry("двести", 200),
      Map.entry("триста", 300),
      Map.entry("четыреста", 400),
      Map.entry("пятьсот", 500),
      Map.entry("шестьсот", 600),
      Map.entry("семьсот", 700),
      Map.entry("восемьсот", 800),
      Map.entry("девятьсот", 900),
      Map.entry("тысяча", 1000)
  );


  public String[] wordsToNumber(String str[], int index) {
    String firstNumber = recognizeWord(str, 0, index);
    String secondNumber = recognizeWord(str, index+1, str.length);
    if(firstNumber==null || secondNumber==null) {
      return new String[]{"invalid"};
    }
    return new String[]{firstNumber, str[index], secondNumber};
  }
  private String recognizeWord(String[] parts, int from, int to){
    if(from>=to) {
      return null;
    }
    if(to-from==1 &&(Validator.isNumeric(parts[from]))) {
      return parts[from];
    }
    int sum=0;
    for(int i=from; i<to; i++) {
      Integer value = NUMBERS.get(parts[i]);
      if(value==null) return null;
      sum += value;
    }
    return String.valueOf(sum);
  }

  public String numberToString(int number) {
    int hundreds = (number/100)*100;
    int tens = ((number%100)/10)*10;
    int units = number%10;
    if(abs(tens)==10) {
      tens=number%100;
      units = 0;
      System.out.println(tens);
    }

    String h, t, u;
    String result="";
    if(hundreds<0||tens<0||units<0) { //если отрицательный результат
      result += "минус ";
      hundreds = -hundreds;
      tens = - tens;
      units = -units;
    }
    if(hundreds!=0) {
      h = getValue(NUMBERS, hundreds);
      if(h!=null) result+=h +" ";
    }
    if(tens!=0) {
      t = getValue(NUMBERS, tens);
      if(t!=null) result+=t+" ";

    }
    if(units!=0) {
      u = getValue(NUMBERS, units);
      if(u!=null) result+=u;
    }
    if((hundreds+tens+units)==0) {
      u = getValue(NUMBERS, units);
      if(u!=null) result+=u;
    }
    return result;
  }
  private String getValue(Map<String, Integer> map, Integer value) {
    for(Map.Entry<String, Integer> entry: map.entrySet()) {
      if(entry.getValue().equals(value)) {
        return entry.getKey();
      }
    }
    return null;
  }
}
