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
   * Конструктор с полным набором параметров
   */
  public Task(int id, String title, String description, Date dueDate, Priority priority) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.priority = priority;

  }

  /**
   * Конструктор с минимальным набором параметров (перегрузка)
   */
  public Task(int id, String title) {
    this.title = title;
    this.id = id;
  }

  /**
   * Конструктор с частичным набором параметров (перегрузка)
   */
  public Task(int id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public Priority getPriority() {
    return priority;
  }

  public boolean isCompleted() {
    return completed;
  }

  /**
   * Метод для маркировки задачи как выполненной
   */
  public void markAsCompleted() {
    completed = true;
  }

  /**
   * Метод для проверки, просрочена ли задача
   */
  public boolean isOverdue() {
    if (dueDate == null) {
      return false;
    }
    return dueDate.before(new Date());
  }

  /**
   * Переопределение метода toString для удобного отображения задачи
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Задача #").append(id).append("\n");
    sb.append("Название: ").append(title).append("\n");
    if (description != null && !description.isEmpty()) {
      sb.append("Описание: ").append(description).append("\n");
    }
    if (dueDate != null) {
      sb.append("Срок: ").append(dueDate).append("\n");
    }
    if (priority != null) {
      sb.append("Приоритет: ").append(priority).append("\n");
    }
    sb.append("Статус: ").append(completed ? "выполнена" : "не выполнена");
    return sb.toString();
  }

  /**
   * Приоритет задачи
   */
  public enum Priority {
    LOW, MEDIUM, HIGH
  }
}