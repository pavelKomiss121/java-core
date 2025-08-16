package ru.mentee.power.tdd;

/**
 * Утилитарный класс для работы со строками.
 */
public class StringUtils {

    /**
     * Переворачивает переданную строку.
     *
     * @param str Строка для переворота.
     * @return Перевернутая строка, или null если на входе был null.
     */
    public String reverse(String str) {
        // TODO: Реализуй этот метод, следуя циклу TDD.
        // Сначала напиши код, чтобы прошел тест shouldReverseNormalString.
        // Затем добавь обработку пустой строки и null, чтобы прошли остальные тесты.
        // Подумай о рефакторинге, если это возможно.
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString(); // Пример возможной реализации
    }
}