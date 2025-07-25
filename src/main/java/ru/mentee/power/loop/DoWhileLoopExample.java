package ru.mentee.power.loop;

import java.util.Scanner; // Импортируем Scanner

public class DoWhileLoopExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        do {
            System.out.println("Выполняем важное действие...");
            System.out.print("Повторить? (да/нет): ");
            answer = scanner.nextLine();
        } while (answer.equalsIgnoreCase("да")); // Повторяем, пока ответ "да" (игнорируя регистр)

        System.out.println("Завершение.");
        scanner.close(); // Не забываем закрыть Scanner
    }
}