package ru.mentee.power.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для управления каталогом книг в библиотеке
 */
public class BookCatalog {

  private List<Book> books;

  /**
   * Создает пустой каталог книг
   */
  public BookCatalog() {
    this.books = new ArrayList<>();
  }

  /**
   * @return компаратор для сортировки по названию (по алфавиту)
   */
  public static Comparator<Book> byTitle() {
    return Comparator.comparing(Book::getTitle);
  }

  /**
   * @return компаратор для сортировки по автору (по алфавиту)
   */
  public static Comparator<Book> byAuthor() {
    return Comparator.comparing(Book::getAuthor);
  }

  /**
   * @return компаратор для сортировки по году издания (от старых к новым)
   */
  public static Comparator<Book> byYearPublished() {
    return Comparator.comparing(Book::getYearPublished);
  }

  /**
   * @return компаратор для сортировки по количеству страниц (от меньшего к большему)
   */
  public static Comparator<Book> byPageCount() {
    return Comparator.comparing(Book::getPageCount);
  }

  // Статические компараторы для удобства использования

  /**
   * Создает сложный компаратор для сортировки по нескольким критериям
   *
   * @param comparators список компараторов в порядке приоритета
   * @return композитный компаратор
   */
  public static Comparator<Book> multipleComparators(List<Comparator<Book>> comparators) {
    return (b1, b2) -> {
      for (Comparator<Book> comp : comparators) {
        int result = comp.compare(b1, b2);
        if (result != 0) {
          return result;
        }
      }
      return 0;
    };
  }

  /**
   * Добавляет книгу в каталог
   *
   * @param book книга для добавления
   */
  public void addBook(Book book) {
    if (book != null) {
      this.books.add(book);
    }
  }

  /**
   * Возвращает неизменяемый список всех книг в каталоге
   *
   * @return список книг
   */
  public List<Book> getAllBooks() {
    return Collections.unmodifiableList(books);
  }

  /**
   * Сортирует книги по заданному компаратору
   *
   * @param comparator компаратор для сортировки
   * @return новый отсортированный список книг (исходный список не меняется)
   */
  public List<Book> sortBooks(Comparator<Book> comparator) {
    List<Book> sorted = new ArrayList<>(books);
    sorted.sort(comparator);
    return sorted;
  }

  /**
   * Фильтрует книги по заданному предикату
   *
   * @param predicate условие фильтрации
   * @return новый список книг, удовлетворяющих условию
   */
  public List<Book> filterBooks(Predicate<Book> predicate) {
    List<Book> filter = new ArrayList<>();
    for (Book book : books) {
      if (predicate.test(book)) {
        filter.add(book);
      }
    }
    return filter;
  }
}