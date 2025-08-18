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
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            char temp = str.charAt(i);
            sb.append(temp);
        }
        return sb.toString(); // Пример возможной реализации
    }
}