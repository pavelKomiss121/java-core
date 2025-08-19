package ru.mentee.power.methods.taskmanager;

import java.util.Calendar;
import java.util.Date;

/**
 * Демонстрационный класс для проверки работы Task Manager
 */
public class TaskManagerDemo {

  public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();

    taskManager.addTask("Подготовить отчёт", "Финансовый отчёт за Q2", createDate(2023, 7, 1),
        Task.Priority.HIGH);
    taskManager.addTask("Позвонить клиенту", "Обсудить условия сделки", createDate(2023, 6, 25),
        Task.Priority.MEDIUM);
    taskManager.addTask("Уборка стола", "Почистить рабочее место", createDate(2023, 8, 10),
        Task.Priority.LOW);
    taskManager.addTask("Просто задача без даты и приоритета");

    System.out.println("=== Все задачи ===");
    taskManager.printAllTasks();

    taskManager.printTasks(taskManager.searchTasks("отчёт"), "Результаты поиска");
    taskManager.printTasks(taskManager.getTasksByPriority(Task.Priority.HIGH), "HIGH Priority");
    taskManager.printTasks(taskManager.sortTasksByPriority(), "Отсортировано по приоритету");
    taskManager.printTasks(taskManager.sortTasksByDueDate(), "Отсортировано по сроку");

    taskManager.markTaskAsCompleted(2);
    taskManager.printTasks(taskManager.getCompletedTasks(), "Выполненные");
    taskManager.printTasks(taskManager.getIncompleteTasks(), "Невыполненные");
    taskManager.printTasks(taskManager.getOverdueTasks(), "Просроченные");
  }

  private static Date createDate(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }
}