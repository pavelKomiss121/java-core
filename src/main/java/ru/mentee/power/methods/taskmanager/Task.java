package ru.mentee.power.methods.taskmanager;

import java.util.Date;

/**
 * Класс, представляющий задачу
 */
public class Task {
    private int id;             // Уникальный идентификатор
    private String title;       // Название задачи
    private String description; // Описание задачи
    private Date dueDate;       // Срок выполнения
    private Priority priority;  // Приоритет
    private boolean completed;  // Статус выполнения

    /**
     * Приоритет задачи
     */
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    /**
     * Конструктор с полным набором параметров
     */
    public Task(int id, String title, String description, Date dueDate, Priority priority) {
        // TODO: Реализовать конструктор
    }

    /**
     * Конструктор с минимальным набором параметров (перегрузка)
     */
    public Task(int id, String title) {
        // TODO: Реализовать конструктор с установкой значений по умолчанию
    }

    /**
     * Конструктор с частичным набором параметров (перегрузка)
     */
    public Task(int id, String title, String description) {
        // TODO: Реализовать конструктор с установкой значений по умолчанию
    }

    // TODO: Реализовать геттеры и сеттеры для всех полей

    /**
     * Метод для получения ID задачи
     */
    public int getId() {
        // TODO: Реализовать метод
        return 0;
    }

    /**
     * Метод для получения названия задачи
     */
    public String getTitle() {
        // TODO: Реализовать метод
        return null;
    }

    // TODO: Реализовать остальные геттеры и сеттеры

    /**
     * Метод для маркировки задачи как выполненной
     */
    public void markAsCompleted() {
        // TODO: Реализовать метод
    }

    /**
     * Метод для проверки, просрочена ли задача
     */
    public boolean isOverdue() {
        // TODO: Сравнить текущую дату с датой выполнения
        // Если дата выполнения раньше текущей, задача просрочена
        return false;
    }

    /**
     * Переопределение метода toString для удобного отображения задачи
     */
    @Override
    public String toString() {
        // TODO: Реализовать метод
        return null;
    }
}