package ru.mentee.power.tdd;

// Простой класс для представления книги

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Геттеры ...
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    // Для сравнения в тестах и сохранения в CSV
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && java.util.Objects.equals(title, book.title) && java.util.Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, author, year);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + '\'' + "author=" + author + '\'' + "year=" + year + '}';
    }

    // Метод для CSV-представления
    public String toCsvString(String delimiter) {
        return String.join(delimiter, title, author, String.valueOf(year));
    }

    // Статический метод для создания из CSV-строки
    public static Book fromCsvString(String csvLine, String delimiter) {
        String[] parts = csvLine.split(delimiter);
        if (parts.length == 3) {
            try {
                return new Book(parts[0], parts[1], Integer.parseInt(parts[2]));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка парсинга года в строке CSV: " + csvLine);
                return null;
            }
        }
        return null;
    }
}