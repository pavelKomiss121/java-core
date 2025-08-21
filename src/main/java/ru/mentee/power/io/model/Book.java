package ru.mentee.power.io.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Book implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private String isbn;
  private String title;
  private Set<String> authors;
  private Genre genre;
  private int publicationYear;
  private int pageCount;
  private boolean available;

  public Book(String isbn, String title, int publicationYear, Genre genre) {
    this.isbn = isbn;
    this.title = title;
    this.publicationYear = publicationYear;
    this.genre = genre;
    this.authors = new java.util.HashSet<>();
    this.available = true;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<String> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<String> authors) {
    this.authors = authors;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public void addAuthor(String author) {
    if (author == null || author.isEmpty()) {
      throw new IllegalArgumentException();
    }
    authors.add(author);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(isbn);
  }

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

  public enum Genre implements Serializable {
    FICTION, NON_FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE, ROMANCE, BIOGRAPHY, CHILDREN
  }
}