package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String eq = scanner.nextLine();
        scanner.close();
        calc(eq);
    }

    public static void calc(String equation){
        int result;
        String[] input = equation.split("\\s+");
        if(input.length < 3){
            System.out.println("Неверный формат входящего выражения");
            throw new FormatException();
        }
        String firstNumFromInput = input[0];
        String sign = input[1];
        String secondNumFromInput = input[2];

        if(isSign(sign)){
            if(isArabNum(firstNumFromInput) && isArabNum(secondNumFromInput)){
                int first = Integer.parseInt(firstNumFromInput);
                int second = Integer.parseInt(secondNumFromInput);
                result = calculate(first, second, sign);
                System.out.println(result);
            }else if(isRomanNum(firstNumFromInput) && isRomanNum(secondNumFromInput)){
                Convert convert = new Convert();
                int first = convert.romanToArab(firstNumFromInput);
                int second = convert.romanToArab(secondNumFromInput);
                result = calculate(first, second, sign);
                if(result < 1){
                    System.out.println("В римской системе нет отрицательных чисел");
                    throw new FormatException();
                }else {
                    System.out.println(convert.arabToRoman(result));
                }
            }else {
                System.out.println("Неверный формат цифр в выражении");
                throw new FormatException();
            }
        }else {
            System.out.println("Неверный формат знака операции");
            throw new FormatException();
        }

    }

    public static int calculate(int first, int second, String sign){
        int result = 0;

        switch (sign) {
            case "+" -> result = Math.addition(first, second);
            case "-" -> result = Math.subtraction(first, second);
            case "*" -> result = Math.multiplication(first, second);
            case "/" -> result = Math.division(first, second);
        }

        return result;
    }

    public static boolean isSign(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static boolean isRomanNum(String s){
        return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") || s.equals("V") || s.equals("VI") || s.equals("VII") || s.equals("VIII") || s.equals("IX") || s.equals("X") ;
    }

    public static boolean isArabNum(String s){
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

}
