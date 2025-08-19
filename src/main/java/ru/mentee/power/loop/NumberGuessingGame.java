package ru.mentee.power.loop;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

  private final Random random;
  private Scanner scanner;

  // Статистика
  private int gamesPlayed = 0;
  private int minAttempts = Integer.MAX_VALUE;
  private int maxAttempts = 0;
  private int totalAttempts = 0;

  /**
   * Конструктор по умолчанию
   */
  public NumberGuessingGame(Random random) {
    this.random = random;
  }

  public static void main(String[] args) {
    Random random = new Random();
    NumberGuessingGame game = new NumberGuessingGame(random);

    try {
      game.startGame();
    } finally {
      game.close();
    }
  }

  /**
   * Создает объект Random для генерации случайных чисел Метод выделен для возможности тестирования
   *
   * @return новый объект Random
   */
  protected Random createRandom() {
    return new Random();
  }

  /**
   * Запускает игру "Угадай число"
   */
  public void startGame() {
    this.scanner = new Scanner(System.in); // Здесь создаём Scanner, когда System.in уже подменён

    boolean playAgain;
    do {
      int attempts = playRound();
      updateStatistics(attempts);
      playAgain = askPlayAgain();
    } while (playAgain);

    showStatistics();
  }

  /**
   * Запускает один раунд игры
   *
   * @return количество попыток, потребовавшихся для угадывания
   */
  public int playRound() {
    // Загадываем число от 1 до 100
    int secretNumber = random.nextInt(100) + 1;
    int attempts = 0;
    boolean guessed = false;

    System.out.println("Я загадал число");

    do {
      int guess = 0;
      boolean validInput = false;
      while (!validInput) {
        try {
          String input = scanner.next();
          guess = Integer.parseInt(input);
          if (guess < 1 || guess > 100) {
            System.out.println("Пожалуйста, введите число в диапазоне от 1 до 100.");
          } else {
            validInput = true;
          }
        } catch (NumberFormatException e) {
          System.out.println("Ошибка ввода: пожалуйста, введите целое число.");
        }
      }

      attempts++;

      if (guess < secretNumber) {
        System.out.println("больше");
      } else if (guess > secretNumber) {
        System.out.println("меньше");
      } else {
        guessed = true;
        System.out.printf("Поздравляю! Вы угадали число " + secretNumber);
        System.out.printf("за " + attempts + " попыток");
      }
    } while (!guessed);

    return attempts;
  }

  /**
   * Обновляет статистику игр
   *
   * @param attempts количество попыток в текущей игре
   */
  private void updateStatistics(int attempts) {
    gamesPlayed++;
    totalAttempts += attempts;
    minAttempts = Math.min(minAttempts, attempts);
    maxAttempts = Math.max(maxAttempts, attempts);
  }

  /**
   * Выводит текущую статистику игр
   */
  public void showStatistics() {
    System.out.println("=============== СТАТИСТИКА ===============");
    System.out.printf("Сыграно игр: %d%n", gamesPlayed);
    System.out.printf("Минимальное количество попыток: %d%n", minAttempts);
    System.out.printf("Максимальное количество попыток: %d%n", maxAttempts);
    double average = gamesPlayed > 0 ? (double) totalAttempts / gamesPlayed : 0;
    System.out.println("Среднее количество попыток: " + average);
    System.out.println("=========================================");
  }

  /**
   * Спрашивает пользователя, хочет ли он сыграть еще раз
   *
   * @return true, если пользователь хочет продолжить, иначе false
   */
  private boolean askPlayAgain() {
    System.out.print("Хотите сыграть еще раз? (да/нет): ");
    String answer = scanner.next().toLowerCase();
    return answer.equals("да") || answer.equals("yes") || answer.equals("y");
  }

  /**
   * Закрывает ресурсы
   */
  public void close() {
    scanner.close();
  }
}