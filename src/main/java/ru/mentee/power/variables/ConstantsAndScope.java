package ru.mentee.power.variables;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ConstantsAndScope {

    public static final int CLASS_CONSTANT = 100;

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int localVariable = 10;
        final int LOCAL_CONSTANT = 20;

        System.out.println("Класс-константа: " + CLASS_CONSTANT);
        System.out.println("Локальная переменная: " + localVariable);
        System.out.println("Локальная константа: " + LOCAL_CONSTANT);

        {
            int blockVariable = 30;
            System.out.println("Переменная внутри блока: " + blockVariable);

            System.out.println("Локальная переменная из блока: " + localVariable);
        }

        // Ошибка: переменная blockVariable недоступна за пределами блока
        // System.out.println(blockVariable); Это вызовет ошибку компиляции

        someMethod();

        // Ошибка: переменная из другого метода недоступна здесь
        // System.out.println(methodVariable); Это тоже вызовет ошибку
    }

    public static void someMethod() {
        int methodVariable = 50;
        System.out.println("Локальная переменная метода someMethod: " + methodVariable);
    }
}
