package ru.appline.framework.classes;

public class Converter {

    public static Integer convertСurrencyFromStringToInteger(String stringCurrency){

        String str = stringCurrency.replaceAll("[^0-9]", "");
        Integer integerCurrency = Integer.parseInt(str);

        return integerCurrency;
    }
}
