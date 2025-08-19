package ru.mentee.power.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты для LibraryService")
class LibraryServiceTest {

  private LibraryService libraryService; // Сервиса еще нет!

  @BeforeEach
  void setUp() {
    // Перед каждым тестом создаем новый экземпляр сервиса
    // Мы не будем здесь указывать файл, так как первый тест не требует IO
    libraryService = new LibraryService(); // Предполагаем конструктор по умолчанию
  }

  @Test
  @DisplayName("Добавление новой книги в библиотеку")
  void shouldAddNewBook() {
    // Arrange
    Book newBook = new Book("Гордость и предубеждение", "Джейн Остин", 1813);

    // Act
    libraryService.addBook(newBook); // Метода addBook еще нет!

    // Assert
    List<Book> allBooks = libraryService.getAllBooks(); // Метода getAllBooks тоже нет!
    assertThat(allBooks)
        .isNotNull()
        .hasSize(1)
        .containsExactly(newBook); // Проверяем, что список содержит только нашу книгу
  }

  // TODO: Добавить тесты на поиск книги, удаление, получение всех книг и т.д.
  @Test
  @DisplayName("Сохранение книг в CSV файл")
  void shouldSaveBooksToCsvFile(@TempDir Path tempDir) throws IOException {
    // Arrange
    Path csvFile = tempDir.resolve("library.csv"); // Создаем путь к файлу во временной директории
    LibraryService serviceForSave = new LibraryService(
        csvFile.toString()); // Нужен конструктор с путем к файлу

    Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
    Book book2 = new Book("1984", "Джордж Оруэлл", 1949);
    serviceForSave.addBook(book1);
    serviceForSave.addBook(book2);

    // Act
    serviceForSave.saveToCsv(); // Метода saveToCsv еще нет!

    // Assert
    assertThat(csvFile).exists(); // Проверяем, что файл создан
    List<String> lines = Files.readAllLines(csvFile); // Читаем все строки из файла
    assertThat(lines)
        .hasSize(2) // Ожидаем две строки
        .containsExactly(
            book1.toCsvString(";"), // Предполагаем разделитель ';'
            book2.toCsvString(";")
        );
  }
}