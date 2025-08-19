package ru.mentee.power.collections.library;

import java.util.Objects;

public class Reader {

  private String id;
  private String name;
  private String email;
  private ReaderCategory category;

  // TODO: Реализовать конструктор, который принимает ID, имя, email и категорию читателя
  public Reader(String id, String name, String email, ReaderCategory category) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.category = category;
  }

  // TODO: Реализовать геттеры и сеттеры для всех полей
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ReaderCategory getCategory() {
    return category;
  }

  public void setCategory(ReaderCategory category) {
    this.category = category;
  }

  // TODO: Переопределить equals и hashCode, чтобы читатели считались равными, если у них одинаковый ID
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reader reader = (Reader) o;
    return Objects.equals(id, reader.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Reader{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", category=" + category +
        '}';
  }

  public enum ReaderCategory {
    STUDENT, TEACHER, REGULAR, VIP
  }
}