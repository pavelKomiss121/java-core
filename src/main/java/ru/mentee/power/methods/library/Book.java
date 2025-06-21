package ru.mentee.power.methods.library;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean available;

    /**
     * Создает новую книгу
     * @param title название книги
     * @param author автор книги
     * @param year год издания
     */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    /**
     * Устанавливает статус доступности книги
     * @param available true, если книга доступна, false, если выдана
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Получает
     * @return состояние available
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * @return Строковое представление информации о книге
     */
    @Override
    public String toString() {
        String status = available ? "доступна" : "выдана";
        return "Книга: \"" + title + "\"\n"
                + "Автор: " + author + "\n"
                + "Год издания: " + year + "\n"
                + "Статус: " + status;
    }
}

