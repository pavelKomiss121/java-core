package ru.mentee.power.collections.library;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Book {
    private String isbn;
    private String title;
    private Set<String> authors;
    private Genre genre;
    private int publicationYear;
    private int pageCount;
    private boolean available;

    public enum Genre {
        FICTION, NON_FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE, ROMANCE, BIOGRAPHY, CHILDREN
    }

    // TODO: Реализовать конструктор, который принимает ISBN, название, год публикации и жанр книги.
    // Должен инициализировать множество авторов как пустой HashSet и устанавливать available в true.
    public Book(String isbn, String title, int publicationYear, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    // TODO: Реализовать геттеры и сеттеры для всех полей
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isAvailable() {
        return available;
    }

    // TODO: Реализовать метод addAuthor(String author), который добавляет автора в множество authors
    public void addAuthor(String author) {
        if (author != null || !author.isEmpty()) throw new IllegalArgumentException();
        authors.add(author);
    }

    // TODO: Переопределить equals и hashCode, чтобы книги считались равными, если у них одинаковый ISBN
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    // TODO: Переопределить toString для удобного вывода информации о книге
    @Override
    public String toString() {
        return String.format(
                "Книга: %s\n" +
                        "Автор(ы): %s\n" +
                        "ISBN: %s\n" +
                        "Жанр: %s\n" +
                        "Год издания: %d\n" +
                        "Страниц: %d\n" +
                        "Доступность: %s",
                title,
                String.join(", ", authors),
                isbn,
                genre,
                publicationYear,
                pageCount,
                available ? "доступна" : "недоступна"
        );
    }
}