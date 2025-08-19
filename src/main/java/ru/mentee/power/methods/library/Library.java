package ru.mentee.power.methods.library;

import java.util.Objects;

public class Library {

  // обратите внимание тут books - это не список а массив
  private Book[] books;
  private int bookCount = 0;
  private int availableBooks = 0;

  /**
   * Создает новую библиотеку с заданной вместимостью
   *
   * @param capacity максимальное количество книг
   */
  public Library(int capacity) {
    books = new Book[capacity];
  }

  /**
   * Добавляет книгу в библиотеку
   *
   * @param book книга для добавления
   * @return true, если книга добавлена успешно, false, если библиотека переполнена
   */
  public boolean addBook(Book book) {
    if (bookCount < books.length) {
      for (int i = 0; i < books.length; i++) {
        if (books[i] == null) {
          books[i] = book;
          bookCount += 1;
          availableBooks += 1;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Ищет книгу по названию
   *
   * @param title название книги
   * @return найденная книга или null, если книга не найдена
   */
  public Book findBookByTitle(String title) {
    for (Book book : books) {
      if (book != null && Objects.equals(book.getTitle(), title)) {
        return book;
      }
    }
    return null;
  }

  /**
   * Выдает книгу читателю
   *
   * @param title название книги
   * @return true, если книга успешно выдана, false, если книга не найдена или уже выдана
   */
  public boolean checkoutBook(String title) {
    for (Book book : books) {
      if (book != null && Objects.equals(book.getTitle(), title) && book.isAvailable()) {
        book.setAvailable(false);
        availableBooks -= 1;
        return true;
      }
    }
    return false;
  }

  /**
   * Возвращает книгу в библиотеку
   *
   * @param title название книги
   * @return true, если книга успешно возвращена, false, если книга не найдена или уже доступна
   */
  public boolean returnBook(String title) {
    for (Book book : books) {
      if (book != null && Objects.equals(book.getTitle(), title) && !book.isAvailable()) {
        book.setAvailable(true);
        availableBooks += 1;
        return true;
      }
    }
    return false;
  }

  /**
   * Возвращает массив доступных книг
   *
   * @return массив доступных книг
   */
  public Book[] listAvailableBooks() {
    Book[] availableBooks = new Book[this.availableBooks];
    int index = 0;
    for (Book book : books) {
      if (book != null && book.isAvailable()) {
        availableBooks[index] = book;
        index += 1;
      }
    }
    return availableBooks;
  }

  /**
   * Возвращает массив выданных книг
   *
   * @return массив выданных книг
   */
  public Book[] listCheckedOutBooks() {
    Book[] checkedOut = new Book[bookCount - availableBooks];
    int index = 0;
    for (Book book : books) {
      if (book != null && !book.isAvailable()) {
        checkedOut[index] = book;
        index += 1;
      }
    }
    return checkedOut;
  }
}