package ru.mentee.power.tdd.notes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Note /* implements Serializable */ {

  private final int id; // Уникальный ID
  private final LocalDate creationDate; // Дата создания
  private String title; // Заголовок
  private String text; // Текст заметки
  private Set<String> tags; // Набор тегов (уникальные строки)


  public Note(int id, String title, String text) {
    if (title == null) {
      throw new IllegalArgumentException();
    }
    if (text == null) {
      text = "";
    }
    this.id = id;
    this.title = title;
    this.text = text;
    creationDate = LocalDate.now();
    tags = new HashSet<>();
  }

  // Принимает id, title, text.
  // Устанавливает creationDate текущей датой (LocalDate.now()).
  // Инициализирует tags пустым HashSet.
  // Проверяет title и text на null (можно бросать IllegalArgumentException или использовать значение по умолчанию).

  // Для tags возвращать неизменяемую копию: Collections.unmodifiableSet(tags)

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null) {
      throw new IllegalArgumentException();
    }
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    if (text == null) {
      text = "";
    }
    this.text = text;
  }

  // Тоже с проверкой на null.

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public Set<String> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  // Добавляет тег в множество tags.
  // Тег должен быть не null и не пустым. Привести к нижнему регистру перед добавлением.
  public void addTag(String tag) {
    if (tag == null || tag.isEmpty()) {
      throw new IllegalArgumentException();
    }
    tags.add(tag.toLowerCase());
  }

  // Удаляет тег из множества (сравнение без учета регистра).
  public boolean removeTag(String tag) {
    return tags.remove(tag.toLowerCase());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Note note = (Note) o;
    return id == note.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }


}