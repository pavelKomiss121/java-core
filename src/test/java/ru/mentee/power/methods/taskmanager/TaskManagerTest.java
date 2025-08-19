package ru.mentee.power.methods.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса TaskManager
 */
class TaskManagerTest {

  private TaskManager taskManager;

  /**
   * Вспомогательный метод для создания даты
   */
  private static Date createDate(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  @BeforeEach
  void setUp() {
    taskManager = new TaskManager();
    taskManager.addTask("Срочная задача", "Выполнить скорее", createDate(2023, 5, 15),
        Task.Priority.HIGH);
    taskManager.addTask("Обычная задача", "В течение недели", createDate(2023, 6, 1),
        Task.Priority.MEDIUM);
    taskManager.addTask("Несрочная задача", "Когда будет время", createDate(2023, 7, 1),
        Task.Priority.LOW);
    taskManager.addTask("Задача без описания");
  }

  /**
   * Тест добавления задачи
   */
  @Test
  void testAddTask() {
    Task task = taskManager.addTask("Новая задача", "Описание", createDate(2023, 8, 1),
        Task.Priority.MEDIUM);
    assertThat(task).isNotNull();
    assertThat(task.getTitle()).isEqualTo("Новая задача");
    assertThat(task.getDescription()).isEqualTo("Описание");
    assertThat(task.getDueDate()).isEqualTo(createDate(2023, 8, 1));
    assertThat(task.getPriority()).isEqualTo(Task.Priority.MEDIUM);
    assertThat(taskManager.getAllTasks()).contains(task);
  }

  /**
   * Тест получения задачи по ID
   */
  @Test
  void testGetTaskById() {
    Task existing = taskManager.getTaskById(1);
    assertThat(existing).isNotNull();
    assertThat(existing.getTitle()).isEqualTo("Срочная задача");

    Task notFound = taskManager.getTaskById(999);
    assertThat(notFound).isNull();
  }

  /**
   * Тест получения задач по приоритету
   */
  @Test
  void testGetTasksByPriority() {
    List<Task> high = taskManager.getTasksByPriority(Task.Priority.HIGH);
    assertThat(high).hasSize(1);
    assertThat(high.get(0).getTitle()).isEqualTo("Срочная задача");

    List<Task> none = taskManager.getTasksByPriority(null);
    assertThat(none).isEmpty();
  }

  /**
   * Тест поиска задач
   */
  @Test
  void testSearchTasks() {
    List<Task> results = taskManager.searchTasks("скорее");
    assertThat(results).hasSize(1);
    assertThat(results.get(0).getTitle()).isEqualTo("Срочная задача");

    List<Task> none = taskManager.searchTasks("несуществующее слово");
    assertThat(none).isEmpty();
  }

  /**
   * Тест сортировки задач по приоритету
   */
  @Test
  void testSortTasksByPriority() {
    List<Task> sorted = taskManager.sortTasksByPriority();

    assertThat(sorted).hasSize(4);
    assertThat(sorted.get(0).getPriority()).isEqualTo(Task.Priority.LOW);
    assertThat(sorted.get(1).getPriority()).isEqualTo(Task.Priority.MEDIUM);
    assertThat(sorted.get(2).getPriority()).isEqualTo(Task.Priority.HIGH);
    assertThat(sorted.get(3).getPriority()).isNull(); // задача без приоритета
  }
}