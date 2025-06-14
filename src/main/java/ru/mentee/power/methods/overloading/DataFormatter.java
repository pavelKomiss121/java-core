package ru.mentee.power.methods.overloading;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Класс для форматирования различных типов данных в строку
 */
public class DataFormatter {

    /**
     * Форматирует целое число, разделяя его по разрядам
     *
     * @param value Целое число
     * @return Отформатированное представление числа
     */
    public static String format(int value) {
        String str = Integer.toString(Math.abs(value));
        String result = "";
        int count = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            result = str.charAt(i) + result;
            count++;
            if (count % 3 == 0 && i > 0) result = " " + result;
        }
        if (value < 0) result = "-" + result;
        return result;
    }

    /**
     * Форматирует целое число с указанием префикса и суффикса
     *
     * @param value Целое число
     * @param prefix Префикс (например, символ валюты)
     * @param suffix Суффикс (например, код валюты)
     * @return Отформатированное представление числа с префиксом и суффиксом
     */
    public static String format(int value, String prefix, String suffix) {
        String str = format(value);
        return prefix + str + " " + suffix;
    }

    /**
     * Форматирует число с плавающей точкой, используя два десятичных знака
     *
     * @param value Число с плавающей точкой
     * @return Отформатированное представление числа
     */
    public static String format(double value) {
        boolean isNegative = value < 0;
        value = Math.abs(value);

        long wholePart = (long) value;
        long decimalPart = Math.round((value - wholePart) * 100);

        String leftPart = format((int) wholePart);
        String formattedDecimal = decimalPart < 10 ? "0" + decimalPart : Long.toString(decimalPart);

        String result = leftPart + "," + formattedDecimal;
        if (isNegative) {
            result = "-" + result;
        }
        return result;
    }

    /**
     * Форматирует число с плавающей точкой с указанным количеством десятичных знаков
     *
     * @param value Число с плавающей точкой
     * @param decimalPlaces Количество знаков после запятой
     * @return Отформатированное представление числа
     */
    public static String format(double value, int decimalPlaces) {
        boolean isNegative = value < 0;
        long decimalPart = 0;
        value = Math.abs(value);

        long wholePart = (long) value;
        if (decimalPlaces==0){
            decimalPart = Math.round(value - wholePart);
            wholePart += decimalPart;
        } else{
            decimalPart = Math.round((value - wholePart) * Math.pow(10, decimalPlaces));
        }

        String leftPart = format((int) wholePart);
        String formattedDecimal = decimalPart < 10 ? "0" + decimalPart : Long.toString(decimalPart);
        if (isNegative) {
            leftPart = "-" + leftPart;
        }
        if (decimalPlaces == 0) {
            return leftPart;
        }
        return leftPart + "," + formattedDecimal;
    }

    /**
     * Форматирует дату в формате "дд.мм.гггг"
     *
     * @param date Дата
     * @return Отформатированное представление даты
     */
    public static String format(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    /**
     * Форматирует дату согласно указанному шаблону
     *
     * @param date Дата
     * @param pattern Шаблон форматирования (как в SimpleDateFormat)
     * @return Отформатированное представление даты
     */
    public static String format(Date date, String pattern) {
        if (date == null ) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * Форматирует список строк, объединяя их через запятую
     *
     * @param items Список строковых элементов
     * @return Объединенная строка
     */
    public static String format(List<String> items) {
        if (items.size() == 0) return "";
        String result = "";
        for (int i=0; i<items.size()-1; i++){
            result += items.get(i) + ", ";
        }
        result += items.get(items.size()-1);
        return result;
    }

    /**
     * Форматирует список строк, объединяя их через указанный разделитель
     *
     * @param items Список строковых элементов
     * @param delimiter Разделитель
     * @return Объединенная строка
     */
    public static String format(List<String> items, String delimiter) {
        if (items.size() == 0) return "";
        String result = "";
        for (int i=0; i<items.size()-1; i++){
            result += items.get(i) + delimiter ;
        }
        result += items.get(items.size()-1);
        return result;
    }
}