package ru.mentee.power.collections.library;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LibraryManagerTest {

  private LibraryManager libraryManager;
  private Book book1, book2, book3;
  private Reader reader1, reader2;

  @BeforeEach
  void setUp() {
    libraryManager = new LibraryManager();

    book1 = new Book("ISBN001", "Java Fundamentals", 2020, Book.Genre.SCIENCE);
    book1.setPageCount(300);
    book1.setAuthors(Set.of("Alice Johnson"));
    book1.setAvailable(true);

    book2 = new Book("ISBN002", "History of Europe", 2018, Book.Genre.HISTORY);
    book2.setPageCount(400);
    book2.setAuthors(Set.of("Bob Smith"));
    book2.setAvailable(true);

    book3 = new Book("ISBN003", "Fantasy Worlds", 2021, Book.Genre.FANTASY);
    book3.setPageCount(500);
    book3.setAuthors(Set.of("John Doe", "Jane Roe"));
    book3.setAvailable(true);

    reader1 = new Reader("R001", "Ivan Ivanov", "ivan@example.com", Reader.ReaderCategory.STUDENT);
    reader2 = new Reader("R002", "Anna Petrova", "anna@example.com", Reader.ReaderCategory.TEACHER);

    libraryManager.addBook(book1);
    libraryManager.addBook(book2);
    libraryManager.addBook(book3);

    libraryManager.addReader(reader1);
    libraryManager.addReader(reader2);
  }

  @Nested
  @DisplayName("Тесты CRUD операций с книгами")
  class BookCrudTests {

    @Test
    @DisplayName("Должен корректно добавлять книгу в библиотеку")
    void shouldAddBookCorrectly() {
      Book newBook = new Book("ISBN_NEW", "New Book", 2022, Book.Genre.FICTION);
      newBook.setAuthors(Set.of("Test Author"));
      newBook.setAvailable(true);

      boolean added = libraryManager.addBook(newBook);

      assertThat(added).isTrue();
      assertThat(libraryManager.getBookByIsbn("ISBN_NEW")).isEqualTo(newBook);
    }

    @Test
    @DisplayName("Не должен добавлять дубликат книги")
    void shouldNotAddDuplicateBook() {
      Book duplicate = new Book("ISBN001", "Duplicate Book", 2020, Book.Genre.SCIENCE);
      duplicate.setAuthors(Set.of("Alice Johnson"));
      boolean added = libraryManager.addBook(duplicate);

      assertThat(added).isFalse();
    }

    @Test
    @DisplayName("Должен возвращать книгу по ISBN")
    void shouldReturnBookByIsbn() {
      Book found = libraryManager.getBookByIsbn("ISBN002");

      assertThat(found.getTitle()).isEqualTo("History of Europe");
      assertThat(found.getAuthors()).contains("Bob Smith");
    }

    @Test
    @DisplayName("Должен возвращать null при поиске книги по несуществующему ISBN")
    void shouldReturnNullForNonExistingIsbn() {
      Book result = libraryManager.getBookByIsbn("NON_EXISTENT");
      assertThat(result).isNull();
    }

    @Test
    @DisplayName("Должен корректно удалять книгу из библиотеки")
    void shouldRemoveBookCorrectly() {
      boolean removed = libraryManager.removeBook("ISBN003");
      assertThat(removed).isTrue();
      assertThat(libraryManager.getBookByIsbn("ISBN003")).isNull();
    }

    @Test
    @DisplayName("Должен возвращать false при попытке удалить несуществующую книгу")
    void shouldReturnFalseWhenRemovingNonExistingBook() {
      boolean removed = libraryManager.removeBook("FAKE_ISBN");
      assertThat(removed).isFalse();
    }
  }

  @Nested
  @DisplayName("Тесты поиска и фильтрации книг")
  class BookSearchAndFilterTests {

    @Test
    @DisplayName("Должен возвращать список всех книг")
    void shouldReturnAllBooks() {
      List<Book> allBooks = libraryManager.getAllBooks();
      assertThat(allBooks)
          .hasSize(3)
          .containsExactlyInAnyOrder(book1, book2, book3);
    }

    @Test
    @DisplayName("Должен возвращать список книг определенного жанра")
    void shouldReturnBooksByGenre() {
      List<Book> result = libraryManager.getBooksByGenre(Book.Genre.HISTORY);
      assertThat(result).containsExactly(book2);
    }

    @Test
    @DisplayName("Должен возвращать список книг определенного автора")
    void shouldReturnBooksByAuthor() {
      List<Book> books = libraryManager.getBooksByAuthor("Alice Johnson");
      assertThat(books).containsExactly(book1);
      assertThat(books).allSatisfy(b -> assertThat(b.getAuthors()).contains("Alice Johnson"));
    }

    @Test
    @DisplayName("Должен находить книги по части названия")
    void shouldFindBooksByTitlePart() {
      List<Book> found = libraryManager.searchBooksByTitle("Java");
      assertThat(found).containsExactly(book1);
    }

    @Test
    @DisplayName("Должен возвращать только доступные книги")
    void shouldReturnOnlyAvailableBooks() {
      libraryManager.borrowBook("ISBN003", "R001", 7); // делаем недоступной
      List<Book> availableBooks = libraryManager.getAvailableBooks();
      assertThat(availableBooks).doesNotContain(book3);
      assertThat(availableBooks).allSatisfy(Book::isAvailable);
    }
  }

  @Nested
  @DisplayName("Тесты сортировки книг")
  class BookSortingTests {

    @Test
    @DisplayName("Должен корректно сортировать книги по названию")
    void shouldSortBooksByTitle() {
      List<Book> books = List.of(book3, book1, book2);
      List<Book> sorted = libraryManager.sortBooksByTitle(books);
      assertThat(sorted).containsExactly(book3, book2, book1);
    }

    @Test
    @DisplayName("Должен корректно сортировать книги по году публикации")
    void shouldSortBooksByPublicationYear() {
      List<Book> books = List.of(book1, book2, book3);
      List<Book> sorted = libraryManager.sortBooksByPublicationYear(books);
      assertThat(sorted).containsExactly(book2, book1, book3);
    }

    @Test
    @DisplayName("Должен корректно сортировать книги по доступности")
    void shouldSortBooksByAvailability() {
      book1.setAvailable(false);
      List<Book> books = List.of(book1, book2, book3);
      List<Book> sorted = libraryManager.sortBooksByAvailability(books);
      assertThat(sorted.subList(0, 2)).allMatch(Book::isAvailable);
      assertThat(sorted.get(2).isAvailable()).isFalse();
    }
  }

  @Nested
  @DisplayName("Тесты CRUD операций с читателями")
  class ReaderCrudTests {

    @Test
    @DisplayName("Должен корректно добавлять читателя")
    void shouldAddReaderCorrectly() {
      Reader newReader = new Reader("R003", "Oleg Smirnov", "oleg@example.com",
          Reader.ReaderCategory.VIP);
      boolean added = libraryManager.addReader(newReader);
      assertThat(added).isTrue();
      assertThat(libraryManager.getReaderById("R003")).isEqualTo(newReader);
    }

    @Test
    @DisplayName("Не должен добавлять дубликат читателя")
    void shouldNotAddDuplicateReader() {
      Reader duplicate = new Reader("R001", "Duplicate", "dup@example.com",
          Reader.ReaderCategory.STUDENT);
      boolean added = libraryManager.addReader(duplicate);
      assertThat(added).isFalse();
    }

    @Test
    @DisplayName("Должен возвращать читателя по ID")
    void shouldReturnReaderById() {
      Reader result = libraryManager.getReaderById("R002");
      assertThat(result).isNotNull();
      assertThat(result.getName()).isEqualTo("Anna Petrova");
    }

    @Test
    @DisplayName("Должен возвращать null при поиске читателя по несуществующему ID")
    void shouldReturnNullForNonExistingReaderId() {
      Reader result = libraryManager.getReaderById("X999");
      assertThat(result).isNull();
    }

    @Test
    @DisplayName("Должен корректно удалять читателя")
    void shouldRemoveReaderCorrectly() {
      boolean removed = libraryManager.removeReader("R002");

      assertThat(removed).isTrue();
      assertThat(libraryManager.getReaderById("R002")).isNull();
    }
  }

  @Nested
  @DisplayName("Тесты операций с выдачей книг")
  class BorrowingOperationsTests {

    @Test
    @DisplayName("Должен корректно оформлять выдачу книги")
    void shouldBorrowBookCorrectly() {
      boolean result = libraryManager.borrowBook("ISBN001", "R001", 7);

      assertThat(result).isTrue();
      assertThat(book1.isAvailable()).isFalse();
      assertThat(libraryManager.getAllBorrowings()).anySatisfy(b -> {
        assertThat(b.getIsbn()).isEqualTo("ISBN001");
        assertThat(b.getReaderId()).isEqualTo("R001");
      });
    }

    @Test
    @DisplayName("Не должен выдавать недоступную книгу")
    void shouldNotBorrowUnavailableBook() {
      libraryManager.borrowBook("ISBN001", "R001", 7);
      boolean secondBorrow = libraryManager.borrowBook("ISBN001", "R002", 5);
      assertThat(secondBorrow).isFalse();
    }

    @Test
    @DisplayName("Должен корректно оформлять возврат книги")
    void shouldReturnBookCorrectly() {
      libraryManager.borrowBook("ISBN002", "R001", 7);
      boolean returned = libraryManager.returnBook("ISBN002", "R001");

      assertThat(returned).isTrue();
      assertThat(book2.isAvailable()).isTrue();

      Optional<Borrowing> borrowing = libraryManager.getAllBorrowings()
          .stream()
          .filter(b -> b.getIsbn().equals("ISBN002") && b.getReaderId().equals("R001"))
          .findFirst();

      assertThat(borrowing).isPresent();
      assertThat(borrowing.get().getReturnDate()).isNotNull();
    }

    @Test
    @DisplayName("Должен возвращать список просроченных выдач")
    void shouldReturnOverdueBorrowings() {
      Borrowing overdue = new Borrowing("ISBN001", "R001",
          LocalDate.now().minusDays(10), null);
      overdue.setDueDate(LocalDate.now().minusDays(1));

      Borrowing notOverdue = new Borrowing("ISBN002", "R002",
          LocalDate.now().minusDays(5), null);
      notOverdue.setDueDate(LocalDate.now().plusDays(3));

      libraryManager.addBorrowingForTest(overdue);
      libraryManager.addBorrowingForTest(notOverdue);

      List<Borrowing> result = libraryManager.getOverdueBorrowings();
      assertThat(result).contains(overdue).doesNotContain(notOverdue);
    }

    @Test
    @DisplayName("Должен корректно продлевать срок выдачи")
    void shouldExtendBorrowingPeriodCorrectly() {
      libraryManager.borrowBook("ISBN001", "R001", 5);

      Borrowing borrowing = libraryManager.getAllBorrowings().stream()
          .filter(b -> b.getIsbn().equals("ISBN001") && b.getReaderId().equals("R001"))
          .findFirst().orElseThrow();

      LocalDate originalDueDate = borrowing.getDueDate();

      boolean extended = libraryManager.extendBorrowingPeriod("ISBN001", "R001", 3);

      assertThat(extended).isTrue();
      assertThat(borrowing.getDueDate()).isEqualTo(originalDueDate.plusDays(3));
    }
  }

  @Nested
  @DisplayName("Тесты статистики и отчетов")
  class StatisticsAndReportsTests {

    @Test
    @DisplayName("Должен возвращать корректную статистику по жанрам")
    void shouldReturnCorrectGenreStatistics() {
      Map<Book.Genre, Integer> stats = libraryManager.getGenreStatistics();

      assertThat(stats.get(Book.Genre.SCIENCE)).isEqualTo(1);
      assertThat(stats.get(Book.Genre.HISTORY)).isEqualTo(1);
      assertThat(stats.get(Book.Genre.FANTASY)).isEqualTo(1);
    }

    @Test
    @DisplayName("Должен возвращать список самых популярных книг")
    void shouldReturnMostPopularBooks() {
      libraryManager.borrowBook("ISBN001", "R001", 3);
      libraryManager.returnBook("ISBN001", "R001");
      libraryManager.borrowBook("ISBN001", "R002", 3);

      libraryManager.borrowBook("ISBN002", "R001", 3);

      Map<Book, Integer> popular = libraryManager.getMostPopularBooks(2);
      List<Book> ordered = new ArrayList<>(popular.keySet());
      assertThat(ordered).containsExactly(book1, book2);
    }

    @Test
    @DisplayName("Должен возвращать список самых активных читателей")
    void shouldReturnMostActiveReaders() {
      libraryManager.borrowBook("ISBN001", "R001", 3);
      libraryManager.returnBook("ISBN001", "R001");
      libraryManager.borrowBook("ISBN002", "R001", 3);
      libraryManager.borrowBook("ISBN003", "R002", 3);

      Map<Reader, Integer> activeReaders = libraryManager.getMostActiveReaders(2);
      assertThat(activeReaders).containsEntry(reader1, 2);
      assertThat(activeReaders).containsEntry(reader2, 1);
    }

    @Test
    @DisplayName("Должен возвращать список читателей с просроченными книгами")
    void shouldReturnReadersWithOverdueBooks() {
      Borrowing overdue = new Borrowing("ISBN001", "R001",
          LocalDate.now().minusDays(10), null);
      overdue.setDueDate(LocalDate.now().minusDays(1));

      Borrowing notOverdue = new Borrowing("ISBN002", "R002",
          LocalDate.now().minusDays(2), null);
      notOverdue.setDueDate(LocalDate.now().plusDays(5));

      libraryManager.addBorrowingForTest(overdue);
      libraryManager.addBorrowingForTest(notOverdue);

      List<Reader> overdueReaders = libraryManager.getReadersWithOverdueBooks();

      assertThat(overdueReaders).containsExactly(reader1);
    }
  }

  @Nested
  @DisplayName("Тесты итераторов")
  class IteratorsTests {

    @Test
    @DisplayName("Должен корректно итерироваться по книгам определенного жанра и года")
    void shouldIterateOverBooksByGenreAndYear() {
      Iterator<Book> iterator = libraryManager.getBooksByGenreAndYearIterator(Book.Genre.FANTASY,
          2021);
      assertThat(iterator).toIterable().containsExactly(book3);
    }

    @Test
    @DisplayName("Должен корректно итерироваться по книгам с несколькими авторами")
    void shouldIterateOverBooksWithMultipleAuthors() {
      Iterator<Book> iterator = libraryManager.getBooksWithMultipleAuthorsIterator(2);
      assertThat(iterator).toIterable().containsExactly(book3);
    }

    @Test
    @DisplayName("Должен корректно итерироваться по просроченным выдачам")
    void shouldIterateOverOverdueBorrowings() {
      Borrowing overdue = new Borrowing("ISBN001", "R001",
          LocalDate.now().minusDays(10), null);
      overdue.setDueDate(LocalDate.now().minusDays(1));
      Borrowing notOverdue = new Borrowing("ISBN002", "R002",
          LocalDate.now().minusDays(1), null);
      notOverdue.setDueDate(LocalDate.now().plusDays(2));

      libraryManager.addBorrowingForTest(overdue);
      libraryManager.addBorrowingForTest(notOverdue);

      Iterator<Borrowing> iterator = libraryManager.getOverdueBorrowingsIterator();

      assertThat(iterator).toIterable().containsExactly(overdue);
    }

    @Test
    @DisplayName("Должен выбрасывать NoSuchElementException при отсутствии следующего элемента")
    void shouldThrowNoSuchElementExceptionWhenNoMoreElements() {
      Iterator<Book> iterator = libraryManager.getBooksByGenreAndYearIterator(Book.Genre.HISTORY,
          2000);
      assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }
  }
}