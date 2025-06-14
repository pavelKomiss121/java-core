package ru.mentee.power.methods.taskmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс для управления задачами
 */
public class TaskManager {
    private List<Task> tasks;
    private int nextId = 1;

    /**
     * Конструктор
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Добавление задачи с полным набором параметров
     */
    public Task addTask(String title, String description, Date dueDate, Task.Priority priority) {
        // TODO: Создать задачу с текущим ID, добавить в список и увеличить nextId
        return null;
    }

    /**
     * Добавление задачи только с названием (перегрузка)
     */
    public Task addTask(String title) {
        // TODO: Вызвать более полный метод с дефолтными значениями
        return null;
    }

    /**
     * Добавление задачи с названием и описанием (перегрузка)
     */
    public Task addTask(String title, String description) {
        // TODO: Вызвать более полный метод с дефолтными значениями
        return null;
    }

    /**
     * Получение задачи по ID
     */
    public Task getTaskById(int id) {
        // TODO: Найти и вернуть задачу с указанным ID
        return null;
    }

    /**
     * Удаление задачи по ID
     */
    public boolean removeTask(int id) {
        // TODO: Найти и удалить задачу с указанным ID
        return false;
    }

    /**
     * Маркировка задачи как выполненной
     */
    public boolean markTaskAsCompleted(int id) {
        // TODO: Найти задачу и вызвать её метод markAsCompleted()
        return false;
    }

    /**
     * Получение всех задач
     */
    public List<Task> getAllTasks() {
        // TODO: Вернуть копию списка всех задач
        return null;
    }

    /**
     * Получение выполненных задач
     */
    public List<Task> getCompletedTasks() {
        // TODO: Вернуть список задач, где completed == true
        return null;
    }

    /**
     * Получение невыполненных задач
     */
    public List<Task> getIncompleteTasks() {
        // TODO: Вернуть список задач, где completed == false
        return null;
    }

    /**
     * Получение просроченных задач
     */
    public List<Task> getOverdueTasks() {
        // TODO: Вернуть список задач, где isOverdue() == true
        return null;
    }

    /**
     * Получение задач с заданным приоритетом
     */
    public List<Task> getTasksByPriority(Task.Priority priority) {
        // TODO: Вернуть список задач с указанным приоритетом
        return null;
    }

    /**
     * Поиск задач по фрагменту названия или описания
     */
    public List<Task> searchTasks(String query) {
        // TODO: Вернуть список задач, содержащих query в названии или описании
        return null;
    }

    /**
     * Сортировка задач по сроку выполнения
     * Использует алгоритм сортировки пузырьком из блока циклов
     */
    public List<Task> sortTasksByDueDate() {
        // TODO: Реализовать сортировку  по дате выполнения
        return null;
    }

    /**
     * Сортировка задач по приоритету
     * Использует алгоритм сортировки вставками из блока циклов
     */
    public List<Task> sortTasksByPriority() {
        // TODO: Реализовать сортировку  по приоритету
        return null;
    }

    /**
     * Вывод всех задач в консоль
     */
    public void printAllTasks() {
        // TODO: Вывести все задачи в консоль в читаемом формате
    }

    /**
     * Вывод задач с указанным заголовком
     */
    public void printTasks(List<Task> taskList, String header) {
        // TODO: Вывести задачи из списка с заголовком
    }
}