package ru.mentee.power.loop;

import java.util.List;

public class ForEachListExample {
    public static void main(String[] args) {
        List<String> colors = List.of("Красный", "Зеленый", "Синий");

        System.out.println("Цвета:");
        for (String color : colors) {
            System.out.println(color);
        }
    }
}