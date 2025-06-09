package ru.mentee.power.datatypes;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class dataTypes
{
    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte b = 10;
        short s = 300;
        int i = 5000;
        long l = 1_000_000L;
        float f = 5.75f;
        double d = 123.456;
        char c = 'Z';
        boolean bool = true;

        String str1 = "Hello";
        String str2 = "World";
        int[] numbers = {1, 2, 3, 4, 5};
        String[] words = {"Java", "is", "fun"};

        int sum = b + s;
        double mixedCalc = i / f + d;
        long mult = l * i;

        double castedDouble = i;
        int castedInt = (int) d;
        int parsedInt = Integer.parseInt("123");
        String numberToStr = Integer.toString(456);

        System.out.println("Примитивы:");
        System.out.println("byte: " + b + ", short: " + s + ", int: " + i + ", long: " + l);
        System.out.println("float: " + f + ", double: " + d);
        System.out.println("char: '" + c + "', boolean: " + bool);

        System.out.println("\nСтроки и массивы:");
        System.out.println("Строки: " + str1 + " " + str2);
        System.out.println("Массив чисел: " + Arrays.toString(numbers));
        System.out.println("Массив слов: " + String.join(" ", words));

        System.out.println("\nМатематические операции:");
        System.out.println("Сумма byte и short: " + sum + " (" + ((Object)sum).getClass().getSimpleName() + ")");
        System.out.println("Комбинированное выражение (int / float + double): " + mixedCalc + " (" + ((Object)mixedCalc).getClass().getSimpleName() + ")");
        System.out.println("long * int: " + mult + " (" + ((Object)mult).getClass().getSimpleName() + ")");

        System.out.println("\nПреобразования:");
        System.out.println("int в double: " + castedDouble + " (" + ((Object)castedDouble).getClass().getSimpleName() + ")");


        System.out.println("double в int: " + castedInt + " (" + ((Object)castedInt).getClass().getSimpleName() + ")");
        System.out.println("String \"123\" в int: " + parsedInt + " (" + ((Object)parsedInt).getClass().getSimpleName() + ")");
        System.out.println("int 456 в String: \"" + numberToStr + "\" (" + numberToStr.getClass().getSimpleName() + ")");

    }
}
