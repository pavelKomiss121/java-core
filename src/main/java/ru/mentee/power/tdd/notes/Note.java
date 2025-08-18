package ru.mentee.power.tdd.notes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

// TODO: Сделать класс Serializable, если будешь использовать сериализацию для сохранения
public class Note /* implements Serializable */ {
    // TODO: Добавить serialVersionUID, если класс Serializable
    // private static final long serialVersionUID = 1L;

    private final int id; // Уникальный ID
    private String title; // Заголовок
    private String text; // Текст заметки
    private final LocalDate creationDate; // Дата создания
    private Set<String> tags; // Набор тегов (уникальные строки)

    // TODO: Реализовать конструктор:

    public Note(int id, String title, String text) {
        if (title == null) throw new IllegalArgumentException();
        if (text == null) text="";
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

    // TODO: Реализовать геттеры для всех полей (id, title, text, creationDate, tags)
    // Для tags возвращать неизменяемую копию: Collections.unmodifiableSet(tags)

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    // TODO: Реализовать сеттеры для title и text
    // Тоже с проверкой на null.

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    public void setText(String text) {
        if (text == null) { text=""; }
        this.text = text;
    }

    // TODO: Реализовать метод addTag(String tag)
    // Добавляет тег в множество tags.
    // Тег должен быть не null и не пустым. Привести к нижнему регистру перед добавлением.
    public void addTag(String tag) {
        if (tag == null || tag.isEmpty()) { throw new IllegalArgumentException(); }
        tags.add(tag.toLowerCase());
    }

    // TODO: Реализовать метод removeTag(String tag)
    // Удаляет тег из множества (сравнение без учета регистра).
    public boolean removeTag(String tag) {
        return tags.remove(tag.toLowerCase());
    }

    // TODO: Переопределить equals и hashCode на основе поля id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}