package ru.mentee.power.loop;

public class FizzBuzz {

    /**
     * Метод возвращает строковое представление чисел от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     * @return массив строк с результатами
     */
    public String[] generateFizzBuzz(int n) {
        // Создаем массив строк длиной n
        String[] result = new String[n];

        // Заполняем массив, обрабатывая каждое число от 1 до n
        for (int index = 1; index <= n; index++) {
            if (index % 3 == 0 && index % 5 == 0) {
                // Число делится и на 3, и на 5
                result[index-1] = "FizzBuzz";
            } else if (index % 3 == 0) {
                // Число делится только на 3
                result[index-1] = "Fizz";
            } else if (index % 5 == 0) {
                // Число делится только на 5
                result[index-1] = "Buzz";
            } else {
                // Число не делится ни на 3, ни на 5
                result[index-1] = String.valueOf(index);
            }
        }
        return result;
    }

    /**
     * Метод выводит на экран числа от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     */
    public void printFizzBuzz(int n) {
        // Получаем массив результатов
        String[] results = generateFizzBuzz(n);

        // Выводим каждый элемент в консоль
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        // Создаем экземпляр класса
        FizzBuzz fizzBuzz = new FizzBuzz();

        // Выводим результаты для n = 15
        System.out.println("FizzBuzz для чисел от 1 до 15:");
        fizzBuzz.printFizzBuzz(15);
    }
}