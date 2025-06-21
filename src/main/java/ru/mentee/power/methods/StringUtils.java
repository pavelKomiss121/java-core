package ru.mentee.power.methods;

/**
 * Утилитарный класс для работы со строками.
 */
public class StringUtils {

    /**
     * Подсчитывает количество вхождений символа в строке.
     *
     * @param str Исходная строка
     * @param target Искомый символ
     * @return Количество вхождений символа
     */
    public static int countChars(String str, char target) {
        if (str == null) return 0;
        int sum=0;
        for (int i=0; i< str.length(); i++){
            if (str.charAt(i) == target){
                sum+=1;
            }
        }
        return sum;
    }

    /**
     * Обрезает строку до указанной максимальной длины.
     * Если строка длиннее maxLength, добавляет "..." в конце.
     *
     * @param str Исходная строка
     * @param maxLength Максимальная длина
     * @return Обрезанная строка
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() < maxLength) return str;
        String newLine = "";
        newLine = str.substring(0, maxLength);
        if (str.length() > maxLength) return newLine + "...";
        else return newLine;
    }

    /**
     * Проверяет, является ли строка палиндромом.
     * Игнорирует регистр и не-буквенные символы.
     *
     * @param str Исходная строка
     * @return true, если строка является палиндромом, иначе false
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        String cleaned = str.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
        int len = cleaned.length();
        for (int i = 0; i < len / 2; i++) {
            if (cleaned.charAt(i) != cleaned.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Заменяет все последовательности пробельных символов одним пробелом.
     * Удаляет пробелы в начале и конце строки.
     *
     * @param str Исходная строка
     * @return Нормализованная строка
     */
    public static String normalizeSpaces(String str) {
        if (str == null) return "";
        String result = "";
        boolean inSpace = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c)) {
                if (!inSpace) {
                    result += " ";
                    inSpace = true;
                }
            } else {
                result += c;
                inSpace = false;
            }
        }
        return result.trim();
    }

    /**
     * Объединяет массив строк, используя указанный разделитель.
     *
     * @param strings Массив строк
     * @param delimiter Разделитель
     * @return Объединенная строка
     */
    public static String join(String[] strings, String delimiter) {
        if (strings == null || delimiter == null) return "";
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null) {
                result += strings[i];
                if (i < strings.length - 1) result += delimiter;
            }
        }
        return result;
    }
}