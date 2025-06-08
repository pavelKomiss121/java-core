package ru.mentee.power.conditions;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CarEcoRating {

    // TODO: Задайте значения констант для кода ошибки, минимального и максимального рейтинга
    private static final int ERROR_CODE = -1;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 100;
    // TODO: Задайте год, с которого начинается штраф за возраст (например, 2020)
    private static final int EURO_STANDARD_YEAR_THRESHOLD = 2020; // Замените на корректное значение

    // TODO: Задайте значения констант для базовых рейтингов различных типов топлива
    private static final int BASE_RATING_ELECTRIC = 90;
    private static final int BASE_RATING_HYBRID = 70;
    private static final int BASE_RATING_DIESEL = 40;
    private static final int BASE_RATING_PETROL = 30;

    // TODO: Заполните список допустимых типов топлива
    private static final List<String> VALID_FUEL_TYPES = Arrays.asList("Бензин", "Дизель", "Гибрид", "Электро");

    /**
     * Рассчитывает экологический рейтинг автомобиля на основе его характеристик
     *
     * @param fuelType тип топлива ("Бензин", "Дизель", "Гибрид", "Электро")
     * @param engineVolume объем двигателя в литрах
     * @param fuelConsumption расход топлива в л/100км или кВтч/100км
     * @param yearOfManufacture год выпуска автомобиля
     * @param isEuroCompliant соответствует ли автомобиль стандарту Евро-6
     * @return эко-рейтинг автомобиля от MIN_RATING до MAX_RATING или ERROR_CODE в случае ошибки
     */
    public int calculateEcoRating(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture,
                                  boolean isEuroCompliant) {
        // TODO: Реализуйте метод согласно требованиям
        if (!validateInput(fuelType, engineVolume, fuelConsumption, yearOfManufacture)) {
            return ERROR_CODE;
        }
        int baseRating = getBaseFuelTypeRating(fuelType);
        if (baseRating == ERROR_CODE) {
            return ERROR_CODE;
        }
        int finalRating = applyRatingModifiers(baseRating, fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);
        return clampRating(finalRating);
    }

    /**
     * Проверяет корректность входных данных
     *
     * @param fuelType тип топлива
     * @param engineVolume объем двигателя
     * @param fuelConsumption расход топлива
     * @param yearOfManufacture год выпуска
     * @return true, если все данные корректны, иначе false
     */
    private boolean validateInput(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture) {
        // TODO: Реализуйте проверку входных данных
        if (!VALID_FUEL_TYPES.contains(fuelType)) return false;
        if (engineVolume < 0 || fuelConsumption < 0) return false;
        if (yearOfManufacture > Year.now().getValue()) return false;
        if (fuelType.equals("Электро") && engineVolume != 0) return false;
        return true;
    }

    /**
     * Определяет базовый рейтинг в зависимости от типа топлива
     *
     * @param fuelType тип топлива
     * @return базовый рейтинг или ERROR_CODE, если тип топлива неизвестен
     */
    private int getBaseFuelTypeRating(String fuelType) {
        // TODO: Реализуйте метод определения базового рейтинга с помощью switch
        switch (fuelType) {
            case "Электро": return BASE_RATING_ELECTRIC;
            case "Гибрид": return BASE_RATING_HYBRID;
            case "Дизель": return BASE_RATING_DIESEL;
            case "Бензин": return BASE_RATING_PETROL;
            default: return ERROR_CODE;
        }
    }

    /**
     * Применяет модификаторы рейтинга на основе характеристик автомобиля
     *
     * @param baseRating базовый рейтинг
     * @param fuelType тип топлива
     * @param engineVolume объем двигателя
     * @param fuelConsumption расход топлива
     * @param yearOfManufacture год выпуска
     * @param isEuroCompliant соответствие стандарту Евро-6
     * @return итоговый рейтинг после применения модификаторов
     */
    private int applyRatingModifiers(int baseRating, String fuelType, double engineVolume,
                                     double fuelConsumption, int yearOfManufacture,
                                     boolean isEuroCompliant) {
        // TODO: Реализуйте метод применения модификаторов
        double rating = baseRating;
        if (fuelType.equals("Бензин") || fuelType.equals("Дизель")) {
            rating -= engineVolume * 5;
            rating -= fuelConsumption * 2;
            if (isEuroCompliant) rating += 10;
        } else if (fuelType.equals("Гибрид")) {
            if (fuelConsumption < 5) rating += 15;
        } else if (fuelType.equals("Электро")) {
            rating -= fuelConsumption * 0.5;
        }
        int agePenalty = Math.max(0, yearOfManufacture < EURO_STANDARD_YEAR_THRESHOLD ? EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture : 0);
        rating -= agePenalty;
        return (int) Math.round(rating);
    }

    /**
     * Ограничивает рейтинг в диапазоне от MIN_RATING до MAX_RATING
     *
     * @param rating рейтинг для ограничения
     * @return рейтинг в диапазоне от MIN_RATING до MAX_RATING
     */
    private int clampRating(int rating) {
        // TODO: Реализуйте метод ограничения рейтинга с помощью Math.max и Math.min
        return Math.max(MIN_RATING, Math.min(MAX_RATING, rating));
    }

    public static void main(String[] args) {
        CarEcoRating ecoRating = new CarEcoRating();
        Scanner scanner = new Scanner(System.in);

        // TODO: Запросите у пользователя тип топлива, объем, расход, год, Евро-6
        // TODO: Вызовите метод calculateEcoRating и выведите результат
        System.out.println("Тип топлива (Бензин, Дизель, Гибрид, Электро):");
        String type = scanner.next();
        System.out.println("Объем двигателя (л):");
        double volume = scanner.nextDouble();
        System.out.println("Расход топлива (л/100км или кВтч/100км):");
        double consumption = scanner.nextDouble();
        System.out.println("Год выпуска:");
        int year = scanner.nextInt();
        System.out.println("Соответствует Евро-6? (true/false):");
        boolean isEuro = scanner.nextBoolean();

        int rating = ecoRating.calculateEcoRating(type, volume, consumption, year, isEuro);
        if (rating == ERROR_CODE) {
            System.out.println("Ошибка: некорректные входные данные.");
        } else {
            System.out.println("Эко-рейтинг автомобиля: " + rating);
        }

        scanner.close();
    }
}