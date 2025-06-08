package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // TODO: Задайте константы для ходов и результатов игры
    public static final String ROCK = "камень";
    public static final String PAPER = "бумага";
    public static final String SCISSORS = "ножницы";

    public static final String WIN = "Победа игрока";
    public static final String LOSE = "Победа компьютера";
    public static final String DRAW = "Ничья";
    public static final String ERROR = "Ошибка";

    // TODO: Создайте список допустимых ходов
    private static final List<String> VALID_MOVES = Arrays.asList(ROCK, PAPER, SCISSORS);

    private Random random = new Random(); // Генератор случайных чисел

    /**
     * Определяет исход игры на основе ходов игрока и компьютера
     *
     * @param playerMove ход игрока
     * @param computerMove ход компьютера
     * @return результат игры (WIN, LOSE, DRAW или ERROR)
     */
    public String determineWinner(String playerMove, String computerMove) {
        // TODO: Реализуйте определение победителя
        playerMove = playerMove.toLowerCase();
        computerMove = computerMove.toLowerCase();
        // 1. Проверьте валидность ходов через validateMove
        if (!validateMove(playerMove) || !validateMove(computerMove)) {
            return ERROR;
        }
        // 2. Проверьте условия ничьей
        if (playerMove.equals(computerMove)) {
            return DRAW;
        }
        // 3. Используйте вложенные условия или switch для определения победителя
        // Правила:
        // - Камень бьет ножницы
        // - Ножницы бьют бумагу
        // - Бумага бьет камень
        switch (playerMove) {
            case ROCK:
                return (computerMove.equals(SCISSORS)) ? WIN : LOSE;
            case PAPER:
                return (computerMove.equals(ROCK)) ? WIN : LOSE;
            case SCISSORS:
                return (computerMove.equals(PAPER)) ? WIN : LOSE;
            default:
                return ERROR;
        }
    }

    /**
     * Проверяет, является ли ход допустимым
     *
     * @param move ход для проверки
     * @return true, если ход допустим, иначе false
     */
    private boolean validateMove(String move) {
        // TODO: Реализуйте проверку допустимости хода
        return VALID_MOVES.contains(move);
    }

    /**
     * Генерирует случайный ход компьютера
     *
     * @return случайный ход из списка допустимых
     */
    public String generateComputerMove() {
        // TODO: Реализуйте генерацию случайного хода компьютера
        // 1. Создайте объект Random
        // 2. Сгенерируйте случайный индекс в пределах размера списка VALID_MOVES
        // 3. Верните элемент списка по этому индексу
        int index = random.nextInt(VALID_MOVES.size());
        return VALID_MOVES.get(index);
    }

    /**
     * Запускает одну игровую сессию
     */
    public String playOneGame() {
        // TODO: Реализуйте одну игровую сессию
        // 1. Запросите ход у игрока через Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваш выбор (Камень, Бумага, Ножницы):");
        String playerMove = scanner.nextLine();
        playerMove = playerMove.toLowerCase();
        // 2. Сгенерируйте ход компьютера
        String computerMove = generateComputerMove();
        // 3. Определите результат
        String result = determineWinner(playerMove, computerMove);
        // 4. Выведите результат игры
        System.out.println("Компьютер выбрал: " + computerMove);
        System.out.println("Результат: " + result);
        return "Компьютер: " + computerMove + ". Результат: " + result;
    }

    /**
     * Запускает игровой цикл
     */
    public void startGameLoop() {
        // TODO: Реализуйте игровой цикл
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру Камень-Ножницы-Бумага!");
        while (true) {
            playOneGame();
            System.out.println("Хотите сыграть еще? (да/нет):");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("да")) {
                System.out.println("Спасибо за игру!");
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RockPaperScissors game = new RockPaperScissors();
        // TODO: Запустите игровой цикл
        game.startGameLoop();
    }
}