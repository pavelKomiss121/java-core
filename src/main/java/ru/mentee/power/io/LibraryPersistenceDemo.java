package ru.mentee.power.io;

import ru.mentee.power.io.model.Book;
import ru.mentee.power.io.model.Reader;
import ru.mentee.power.io.model.Reader.ReaderCategory;

public class LibraryPersistenceDemo {

  private static final String STATE_FILE = "library_state.ser";
  private static final String BOOKS_CSV_FILE = "books_export.csv";
  private static final String BOOKS_CSV_IMPORT_FILE = "books_import.csv";
  private static final String CSV_DELIMITER = ";";

  public static void main(String[] args) {
    LibraryManager libraryManager;

    System.out.println("Попытка загрузки состояния из " + STATE_FILE + "...");

    libraryManager = LibraryManager.loadLibraryState(STATE_FILE);

    if (libraryManager == null) {
      libraryManager = new LibraryManager();
      System.out.println("Создана новая пустая библиотека. Добавляем тестовые данные...");

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

    System.out.println("\n--- Текущее состояние библиотеки --- ");
    displayLibraryStats(libraryManager);

    System.out.println("\n--- Демонстрация операций --- ");
    boolean borrowed = libraryManager.borrowBook("111-AAA", "R001", 14);
    System.out.println("Выдача книги (111-AAA): " + (borrowed ? "успешно" : "ошибка"));

    System.out.println("\n--- Экспорт книг в CSV --- ");
    libraryManager.exportBooksToCsv(BOOKS_CSV_FILE, CSV_DELIMITER);
    System.out.println("Экспорт завершен в " + BOOKS_CSV_FILE);

    System.out.println("\n--- Импорт книг из CSV --- ");
    int importedCount = libraryManager.importBooksFromCsv(BOOKS_CSV_IMPORT_FILE, CSV_DELIMITER,
        true);
    System.out.println("Импортировано книг: " + importedCount);

    System.out.println("\n--- Состояние библиотеки после импорта --- ");
    displayLibraryStats(libraryManager);

    System.out.println("\n--- Сохранение финального состояния --- ");
    libraryManager.saveLibraryState(STATE_FILE);

    System.out.println("\nРабота программы завершена.");

  }

  private static void displayLibraryStats(LibraryManager manager) {
    System.out.println("Всего книг: " + manager.getAllBooks().size());
    System.out.println("Всего читателей: " + manager.getAllReaders().size());
    System.out.println("Всего записей о выдаче: " + manager.getAllBorrowings().size());
    System.out.println("Доступных книг: " + manager.getAvailableBooks().size());
    System.out.println("Просроченных выдач: " + manager.getOverdueBorrowings().size());
  }
}