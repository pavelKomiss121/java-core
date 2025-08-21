
package ru.mentee.power.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ru.mentee.power.io.model.Book;
import ru.mentee.power.io.model.Borrowing;
import ru.mentee.power.io.model.Reader;


public class LibraryManager implements Serializable {

  @Serial
  private static final long serialVersionUID = 100L;

  private Map<String, Book> booksMap;
  private Map<String, Reader> readersMap;
  private List<Borrowing> borrowingsList;


  private transient Map<Book.Genre, Set<Book>> booksByGenre;
  private transient Map<String, List<Book>> booksByAuthor;

  public LibraryManager() {
    this.booksMap = new HashMap<>();
    this.readersMap = new HashMap<>();
    this.borrowingsList = new LinkedList<>();
    rebuildIndexes();
  }


  public void saveLibraryState(String filename) {
    try (FileOutputStream fos = new FileOutputStream(
        filename); BufferedOutputStream bos = new BufferedOutputStream(
        fos); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
      oos.writeObject(this);
      System.out.println("Состояние библиотеки сохранено в " + filename);
    } catch (IOException ex) {
      System.err.println("Ошибка при сохранении состояния: " + ex.getMessage());
    }
  }

  public static LibraryManager loadLibraryState(String filename) {
    File file = new File(filename);
    if (!file.exists()) {
      System.err.println("Файл состояния не найден: " + filename);
      return null;
    }

    try (FileInputStream fis = new FileInputStream(
        filename); BufferedInputStream bis = new BufferedInputStream(
        fis); ObjectInputStream ois = new ObjectInputStream(bis)) {
      Object o = ois.readObject();
      if (o instanceof LibraryManager lm) {
        lm.rebuildIndexes();
        return lm;
      }

    } catch (FileNotFoundException ex) {
      System.err.println("Файл состояния не найден: " + filename);
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      System.err.println("Ошибка при загрузке состояния: " + e.getMessage());
    }
    return null;
  }

  private void rebuildIndexes() {
    booksByGenre = new HashMap<>();
    booksByAuthor = new HashMap<>();

    if (booksMap != null) {
      for (Book book : booksMap.values()) {
        booksByGenre.computeIfAbsent(book.getGenre(), k -> new HashSet<>()).add(book);
        for (String author : book.getAuthors()) {
          booksByAuthor.computeIfAbsent(author, k -> new ArrayList<>()).add(book);
        }
      }
    }
    System.out.println("(Пере)индексация transient полей выполнена (или не требуется).");
  }

  public static final String BOOK_CSV_HEADER = "ISBN;Title;Authors;Genre;Year;Pages;Available";

  public void exportBooksToCsv(String filename, String delimiter) {
    try (FileWriter writer = new FileWriter(
        filename); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
      bufferedWriter.write(BOOK_CSV_HEADER);
      bufferedWriter.newLine();
      for (Book book : booksMap.values()) {
        String line = String.join(delimiter, book.getIsbn(), book.getTitle(),
            String.join(",", book.getAuthors()), book.getGenre().name(),
            String.valueOf(book.getPublicationYear()), String.valueOf(book.getPageCount()),
            String.valueOf(book.isAvailable()));
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
      System.out.println("Книги экспортированы в " + filename);
    } catch (IOException ex) {
      System.err.println("Ошибка экспорта CSV: " + ex.getMessage());
    }
  }

  public int importBooksFromCsv(String filename, String delimiter, boolean append) {
    int importedCount = 0;
    File file = new File(filename);
    if (!file.exists()) {
      System.err.println("CSV файл не найден: " + filename);
      return 0;
    }
    if (!append) {
      booksMap.clear();
      rebuildIndexes();
    }

    try (FileReader reader = new FileReader(
        filename); BufferedReader bufferedReader = new BufferedReader(reader)) {
      String line;
      boolean firstLine = true;
      while ((line = bufferedReader.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue;
        }
        String[] parts = line.split(delimiter);
        if (parts.length != 7) {
          System.err.println("Неверное количество полей: " + line);
          continue;
        }
        String isbn = parts[0].trim();
        String title = parts[1].trim();
        List<String> authors = Arrays.stream(parts[2].split(",")).map(String::trim).toList();
        Book.Genre genre = Book.Genre.valueOf(parts[3].trim());
        int year = Integer.parseInt(parts[4].trim());
        int pages = Integer.parseInt(parts[5].trim());
        boolean available = Boolean.parseBoolean(parts[6].trim());

        Book book = new Book(isbn, title, year, genre);
        for (String author : authors) {
          book.addAuthor(author);
        }
        book.setAvailable(available);
        book.setPageCount(pages);
        if (addBook(book)) {
          importedCount++;
        }
      }
    } catch (Exception ex) {
      System.err.println("Ошибка импорта CSV: " + ex.getMessage());
    }
    return importedCount;
  }

  public boolean addBook(Book book) {
    if (book == null || booksMap.containsKey(book.getIsbn())) {
      return false;
    }
    booksMap.put(book.getIsbn(), book);
    booksByGenre.computeIfAbsent(book.getGenre(), g -> new HashSet<>()).add(book);
    for (String author : book.getAuthors()) {
      booksByAuthor.computeIfAbsent(author, a -> new ArrayList<>()).add(book);
    }
    return true;
  }

  public boolean removeBook(String isbn) {
    Book removed = booksMap.remove(isbn);
    if (removed == null) {
      return false;
    }
    booksByGenre.getOrDefault(removed.getGenre(), Set.of()).remove(removed);
    for (String author : removed.getAuthors()) {
      booksByAuthor.getOrDefault(author, List.of()).remove(removed);
    }
    return true;
  }

  public Book getBookByIsbn(String isbn) {
    return booksMap.get(isbn);
  }

  public List<Book> getAllBooks() {
    return new ArrayList<>(booksMap.values());
  }

  public boolean addReader(Reader reader) {
    if (reader == null || readersMap.containsKey(reader.getId())) {
      return false;
    }
    readersMap.put(reader.getId(), reader);
    return true;
  }

  public boolean removeReader(String id) {
    return readersMap.remove(id) != null;
  }

  public Reader getReaderById(String id) {
    return readersMap.get(id);
  }

  public List<Reader> getAllReaders() {
    return new ArrayList<>(readersMap.values());
  }

  public List<Borrowing> getAllBorrowings() {
    return new ArrayList<>(borrowingsList);
  }

  public List<Book> getAvailableBooks() {
    return booksMap.values().stream().filter(Book::isAvailable).toList();
  }

  public List<Borrowing> getOverdueBorrowings() {
    LocalDate today = LocalDate.now();
    return borrowingsList.stream().filter(b -> b.getDueDate().isBefore(today) && !b.isReturned())
        .toList();
  }

  public boolean borrowBook(String isbn, String readerId, int days) {
    Book book = booksMap.get(isbn);
    Reader reader = readersMap.get(readerId);
    if (book == null || reader == null || !book.isAvailable()) {
      return false;
    }
    book.setAvailable(false);
    LocalDate borrowDate = LocalDate.now();
    LocalDate dueDate = borrowDate.plusDays(days);
    Borrowing borrowing = new Borrowing(isbn, readerId, borrowDate, null);
    borrowing.setDueDate(dueDate);
    borrowingsList.add(borrowing);
    return true;
  }

  public boolean returnBook(String isbn, String readerId) {
    for (Borrowing b : borrowingsList) {
      if (b.getIsbn().equals(isbn) && b.getReaderId().equals(readerId) && !b.isReturned()) {
        b.returnBook(LocalDate.now());
        Book book = booksMap.get(isbn);
        if (book != null) {
          book.setAvailable(true);
        }
        return true;
      }
    }
    return false;
  }
}