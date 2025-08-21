package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.mentee.power.io.model.Book;
import ru.mentee.power.io.model.Reader;
import ru.mentee.power.io.model.Reader.ReaderCategory;

@DisplayName("Тесты CSV экспорта/импорта LibraryManager")
class LibraryManagerCsvTest {

  @TempDir
  Path tempDir;

  private LibraryManager libraryManager;
  private Path csvFilePath;
  private final String delimiter = ";";

  @BeforeEach
  void setUp() {
    libraryManager = new LibraryManager();
    csvFilePath = tempDir.resolve("books_test.csv");

    Book book1 = new Book("111-AAA", "Effective Java", 2018, Book.Genre.SCIENCE);
    book1.addAuthor("Joshua Bloch");
    Book book2 = new Book("222-BBB", "Clean Code", 2008, Book.Genre.SCIENCE);
    book2.addAuthor("Robert");
    libraryManager.addBook(book1);
    libraryManager.addBook(book2);

    Reader reader1 = new Reader("R001", "Иван Иванов", "ivanIvanov@gmail.com",
        ReaderCategory.STUDENT);
    libraryManager.addReader(reader1);
  }

  @Test
  @DisplayName("exportBooksToCsv должен создавать корректный CSV файл")
  void exportShouldCreateCorrectCsv() throws IOException {
    libraryManager.exportBooksToCsv(csvFilePath.toString(), delimiter);

    assertThat(csvFilePath).exists();

    List<String> lines = Files.readAllLines(csvFilePath, StandardCharsets.UTF_8);
    assertThat(lines).isNotEmpty().hasSize(libraryManager.getAllBooks().size() + 1);
    assertThat(lines.getFirst()).isEqualTo(LibraryManager.BOOK_CSV_HEADER);

    assertThat(lines).anyMatch(line -> line.contains("111-AAA"));
    assertThat(lines).anyMatch(line -> line.contains("222-BBB"));
  }

  @Test
  @DisplayName("importBooksFromCsv должен добавлять книги (append=true)")
  void importShouldAddBooksWhenAppendIsTrue() throws IOException {
    // Given
    int initialBookCount = libraryManager.getAllBooks().size();
    String csvContent = LibraryManager.BOOK_CSV_HEADER + "\n"
        + "ISBN-TEST-1;Новая Книга 1;Автор Тест;FICTION;2024;100;true\n"
        + "ISBN-TEST-2;Новая Книга 2;Автор Тест;SCIENCE;2023;200;false";
    Files.writeString(csvFilePath, csvContent, StandardCharsets.UTF_8);

    int importedCount = libraryManager.importBooksFromCsv(csvFilePath.toString(), delimiter, true);

    assertThat(importedCount).isEqualTo(2);
    assertThat(libraryManager.getAllBooks()).hasSize(initialBookCount + 2);
    assertThat(libraryManager.getAllBooks()).anyMatch(b -> b.getIsbn().equals("ISBN-TEST-1"))
        .anyMatch(b -> b.getIsbn().equals("ISBN-TEST-2"));
  }

  @Test
  @DisplayName("importBooksFromCsv должен заменять книги (append=false)")
  void importShouldReplaceBooksWhenAppendIsFalse() throws IOException {

    String csvContent = LibraryManager.BOOK_CSV_HEADER + "\n"
        + "ISBN-REPLACE-1;Заменяющая Книга;Автор Иной;HISTORY;2022;300;true";
    Files.writeString(csvFilePath, csvContent, StandardCharsets.UTF_8);
    int importBookCount = 1;

    int importedCount = libraryManager.importBooksFromCsv(csvFilePath.toString(), delimiter, false);

    assertThat(importedCount).isEqualTo(importBookCount);
    assertThat(libraryManager.getAllBooks()).hasSize(importBookCount);
    assertThat(libraryManager.getAllBooks()).anyMatch(b -> b.getIsbn().equals("ISBN-REPLACE-1"));
    assertThat(libraryManager.getAllBooks()).noneMatch(b -> b.getIsbn().equals("ISBN-1"));
  }

  @Test
  @DisplayName("importBooksFromCsv должен пропускать некорректные строки")
  void importShouldSkipMalformedLines() throws IOException {
    int initialBookCount = libraryManager.getAllBooks().size();
    String csvContent = LibraryManager.BOOK_CSV_HEADER + "\n"
        + "ISBN-GOOD-1;Хорошая Книга;Автор;FANTASY;2020;150;true\n" + "bad-line-format\n"
        + "ISBN-BAD-YEAR;Плохой Год;Автор;SCIENCE;не_число;200;true";
    Files.writeString(csvFilePath, csvContent, StandardCharsets.UTF_8);

    int importedCount = libraryManager.importBooksFromCsv(csvFilePath.toString(), delimiter, true);

    assertThat(importedCount).isEqualTo(1);
    assertThat(libraryManager.getAllBooks()).hasSize(initialBookCount + 1);
    assertThat(libraryManager.getAllBooks()).anyMatch(b -> b.getIsbn().equals("ISBN-GOOD-1"));
  }
}