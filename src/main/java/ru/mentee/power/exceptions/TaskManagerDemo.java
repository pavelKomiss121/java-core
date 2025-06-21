package ru.mentee.power.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Демонстрационное приложение для работы с TaskManager.
 */
public class TaskManagerDemo {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)){
            TaskManager account = null;
            while (account == null) {
                try {
                    System.out.print("Введите ID счёта: ");
                    String id = scanner.nextLine();
                    System.out.print("Введите начальный баланс: ");
                    double initialBalance = scanner.nextDouble();

                    account = new TaskManager(id, initialBalance);
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введите корректное id.");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                    scanner.nextLine();
                }
            }

            boolean run = true;
            while (run){
                printMenu();
                try{
                    System.out.println("Выбор: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Введите сумму для внесения: ");
                            double deposit = scanner.nextDouble();
                            account.deposit(deposit);
                            System.out.println("Сумма внесена успешно.");
                            break;
                        case 2:
                            System.out.print("Введите сумму для снятия: ");
                            double withdraw = scanner.nextDouble();
                            account.withdraw(withdraw);
                            System.out.println("Сумма снята успешно.");
                            break;
                        case 3:
                            System.out.println("Текущий баланс: " + account.getBalance());
                            break;
                        case 4:
                            run = false;
                            break;
                        default:
                            System.out.println("Неверный пункт меню.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: ожидается числовой ввод.");
                    scanner.nextLine();
                } catch (TaskValidationException e) {
                    System.out.println("Ошибка снятия: " + e.getMessage());
                    System.out.println("Баланс: " + e.getBalance());
                    System.out.println("Хотели снять: " + e.getWithdrawAmount());
                    System.out.println("Не хватило: " + e.getDeficit());
                } catch (IllegalArgumentException e){
                    System.out.println("Ошибка: " + e.getMessage());
                    scanner.nextLine();
                }
            }
        }
        System.out.println("Программа завершена");
    }

    private static void printMenu() {
        System.out.println("=== МЕНЮ ===");
        System.out.println("1. Внести деньги");
        System.out.println("2. Снять деньги");
        System.out.println("3. Проверить баланс");
        System.out.println("4. Выход");
        System.out.println("==========================");
    }
}