package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.mentee.power.io.model.Book;
import ru.mentee.power.io.model.Reader;
import ru.mentee.power.io.model.Reader.ReaderCategory;

@DisplayName("Тесты сохранения/загрузки состояния LibraryManager")
class LibraryManagerPersistenceTest {

  @TempDir
  Path tempDir;

  private Path stateFilePath;
  private LibraryManager managerToSave;

  @BeforeEach
  void setUp() {
    stateFilePath = tempDir.resolve("library_test_state.ser");
    managerToSave = new LibraryManager();

    Book book1 = new Book("111-AAA", "Effective Java", 2018, Book.Genre.SCIENCE);
    book1.addAuthor("Joshua Bloch");
    Book book2 = new Book("222-BBB", "Clean Code", 2008, Book.Genre.SCIENCE);
    book2.addAuthor("Robert");
    managerToSave.addBook(book1);
    managerToSave.addBook(book2);

    Reader reader1 = new Reader("R001", "Иван Иванов", "ivanIvanov@gmail.com",
        ReaderCategory.STUDENT);
    managerToSave.addReader(reader1);
  }

  @Test
  @DisplayName("Должен сохранять и загружать непустое состояние")
  void shouldSaveAndLoadNonEmptyState() {
    managerToSave.saveLibraryState(stateFilePath.toString());
    LibraryManager loadedManager = LibraryManager.loadLibraryState(stateFilePath.toString());

    assertThat(loadedManager).isNotNull();
    assertThat(loadedManager.getAllBooks()).hasSize(managerToSave.getAllBooks().size());
    assertThat(loadedManager.getAllReaders()).hasSize(managerToSave.getAllReaders().size());
    assertThat(loadedManager.getAllBorrowings()).hasSize(managerToSave.getAllBorrowings().size());

    assertThat(loadedManager.getAvailableBooks()).isNotNull();
  }

  @Test
  @DisplayName("Должен сохранять и загружать пустое состояние")
  void shouldSaveAndLoadEmptyState() {
    LibraryManager emptyManager = new LibraryManager();

    emptyManager.saveLibraryState(stateFilePath.toString());
    LibraryManager loadedManager = LibraryManager.loadLibraryState(stateFilePath.toString());

    assertThat(loadedManager).isNotNull();
    assertThat(loadedManager.getAllBooks()).isEmpty();
    assertThat(loadedManager.getAllReaders()).isEmpty();
    assertThat(loadedManager.getAllBorrowings()).isEmpty();
  }

  @Test
  @DisplayName("loadLibraryState должен возвращать null для несуществующего файла")
  void loadShouldReturnNullForNonExistentFile() {
    Path nonExistentFile = tempDir.resolve("lalalla.txt");
    LibraryManager loadedManager = LibraryManager.loadLibraryState(nonExistentFile.toString());
    assertThat(loadedManager).isNull();
  }

}