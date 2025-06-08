package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmartThermostat {

    // TODO: Задайте значение константы для кода ошибки температуры
    private static final double ERROR_TEMP_CODE = -100.0; // Значение можно изменить

    // TODO: Заполните списки для рабочих дней и выходных
    private static final List<String> WEEKDAYS = Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница");
    private static final List<String> WEEKENDS = Arrays.asList("Суббота", "Воскресенье");

    /**
     * Определяет целевую температуру для термостата
     *
     * @param timeOfDay время суток в часах (0-23)
     * @param dayOfWeek день недели ("Понедельник", "Вторник", ..., "Воскресенье")
     * @param isOccupied есть ли кто-то дома
     * @param currentOutsideTemperature текущая температура на улице (в градусах Цельсия)
     * @return целевую температуру для установки или ERROR_TEMP_CODE в случае некорректных входных данных
     */
    public double getTargetTemperature(int timeOfDay, String dayOfWeek, boolean isOccupied,
                                       double currentOutsideTemperature) {
        double temperature = 0;
        double energy =0;
        // TODO: Реализуйте метод согласно требованиям
        if (timeOfDay > 23 || timeOfDay < 0){
            return ERROR_TEMP_CODE;
        }
        if (!WEEKDAYS.contains(dayOfWeek) && !WEEKENDS.contains(dayOfWeek)){
            return ERROR_TEMP_CODE;
        }

        if (WEEKDAYS.contains(dayOfWeek)){
            if (timeOfDay >= 6 && timeOfDay <= 8){
                temperature =  (isOccupied) ? 22 : 18;
            } else if (timeOfDay >= 9 && timeOfDay <= 17){
                temperature =  (isOccupied) ? 20 : 16;
            } else if (timeOfDay >= 18 && timeOfDay <= 22){
                temperature =  (isOccupied) ? 22 : 17;
            } else {
                temperature =  (isOccupied) ? 19 : 16;
            }
        } else {
            if (timeOfDay >= 7 && timeOfDay <= 9){
                temperature =  (isOccupied) ? 23 : 18;
            } else if (timeOfDay >= 10 && timeOfDay <= 17){
                temperature =  (isOccupied) ? 22 : 17;
            } else if (timeOfDay >= 18 && timeOfDay <= 23){
                temperature =  (isOccupied) ? 22 : 17;
            } else {
                temperature =  (isOccupied) ? 20 : 16;
            }
        }
        if (currentOutsideTemperature>25){
            temperature +=1;
        } else if (currentOutsideTemperature < 0) {
            temperature -=1;
        }


        return temperature; // Временная заглушка - измените на правильную реализацию
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SmartThermostat thermostat = new SmartThermostat();
        Scanner scanner = new Scanner(System.in);

        // TODO: Запросите у пользователя необходимые данные (время, день, занятость, темп. снаружи)
        // TODO: Вызовите метод getTargetTemperature и выведите результат

        System.out.print("Введите время суток (0-23): ");
        int time = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите день недели: ");
        String day = scanner.nextLine();
        System.out.print("Есть ли кто то дома?: ");
        boolean occupied = scanner.nextBoolean();
        System.out.print("Погода снаружи: ");
        double outsideTemp = scanner.nextDouble();
        // ... (остальные запросы)
        double targetTemp = thermostat.getTargetTemperature(time, day, occupied, outsideTemp);
        if (targetTemp == ERROR_TEMP_CODE) {
             System.out.println("Ошибка: Некорректные входные данные.");
        } else {
             System.out.println("Рекомендуемая температура: " + targetTemp + "°C");
        }

        scanner.close();
    }
}