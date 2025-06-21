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
        Task newTask = new Task(nextId, title, description, dueDate, priority);
        tasks.add(newTask);
        nextId+=1;
        return newTask;
    }

    /**
     * Добавление задачи только с названием (перегрузка)
     */
    public Task addTask(String title) {
        Task newTask = new Task(nextId, title);
        tasks.add(newTask);
        nextId+=1;
        return newTask;
    }

    /**
     * Добавление задачи с названием и описанием (перегрузка)
     */
    public Task addTask(String title, String description) {
        Task newTask = new Task(nextId, title, description);
        tasks.add(newTask);
        nextId+=1;
        return newTask;
    }

    /**
     * Получение задачи по ID
     */
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }

    /**
     * Удаление задачи по ID
     */
    public boolean removeTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    /**
     * Маркировка задачи как выполненной
     */
    public boolean markTaskAsCompleted(int id) {
        Task task = getTaskById(id);
        if (task != null && !task.isCompleted()) {
            task.markAsCompleted();
            return true;
        }
        return false;
    }

    /**
     * Получение всех задач
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Получение выполненных задач
     */
    public List<Task> getCompletedTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks){
            if (task.isCompleted()){
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Получение невыполненных задач
     */
    public List<Task> getIncompleteTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks){
            if (!task.isCompleted()){
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Получение просроченных задач
     */
    public List<Task> getOverdueTasks() {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks){
            if (!task.isOverdue()){
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Получение задач с заданным приоритетом
     */
    public List<Task> getTasksByPriority(Task.Priority priority) {
        List<Task> result = new ArrayList<>();
        if (priority == null) return result;
        for (Task task : tasks) {
            if (priority.equals(task.getPriority())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Поиск задач по фрагменту названия или описания
     */
    public List<Task> searchTasks(String query) {
        List<Task> result = new ArrayList<>();
        String q = query.toLowerCase();

        for (Task task : tasks) {
            if ((task.getTitle() != null && task.getTitle().toLowerCase().contains(q)) ||
                    (task.getDescription() != null && task.getDescription().toLowerCase().contains(q))) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Сортировка задач по сроку выполнения
     * Использует алгоритм сортировки пузырьком из блока циклов
     */
    public List<Task> sortTasksByDueDate() {
        List<Task> sorted = new ArrayList<>(tasks);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                Date d1 = sorted.get(j).getDueDate();
                Date d2 = sorted.get(j + 1).getDueDate();
                if (d1 != null && d2 != null && d1.after(d2)) {
                    Task temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }

    /**
     * Сортировка задач по приоритету
     * Использует алгоритм сортировки вставками из блока циклов
     */
    public List<Task> sortTasksByPriority() {
        List<Task> sorted = new ArrayList<>(tasks);

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                Task t1 = sorted.get(j);
                Task t2 = sorted.get(j + 1);
                if (comparePriority(t1.getPriority(), t2.getPriority()) > 0) {
                    sorted.set(j, t2);
                    sorted.set(j + 1, t1);
                }
            }
        }
        return sorted;
    }

    private int comparePriority(Task.Priority p1, Task.Priority p2) {
        if (p1 == null && p2 == null) return 0;
        if (p1 == null) return 1;
        if (p2 == null) return -1;
        return p1.ordinal() - p2.ordinal();
    }

    /**
     * Вывод всех задач в консоль
     */
    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Нет задач");
        }
        for (Task task: tasks) {
            System.out.println(task);
            System.out.println();
        }
    }

    /**
     * Вывод задач с указанным заголовком
     */
    public void printTasks(List<Task> taskList, String header) {
        System.out.println("=== " + header + " ===");
        if (taskList.isEmpty()) {
            System.out.println("Нет задач");
        } else {
            for (Task task : taskList) {
                System.out.println(task);
                System.out.println();
            }
        }
    }
}