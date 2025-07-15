package ru.mentee.power.collections.library;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LibraryDemo {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();

        Book book1 = new Book("111", "Java Basics", 2020, Book.Genre.SCIENCE);
        book1.setPageCount(320);
        book1.setAuthors(Set.of("Alice Smith"));
        book1.setAvailable(true);

        Book book2 = new Book("222", "The History of Time", 2018, Book.Genre.HISTORY);
        book2.setPageCount(250);
        book2.setAuthors(Set.of("Bob Johnson"));
        book2.setAvailable(true);

        Book book3 = new Book("333", "Fantasy Tales", 2022, Book.Genre.FANTASY);
        book3.setPageCount(500);
        book3.setAuthors(Set.of("Clara Grey", "John Doe"));
        book3.setAvailable(true);
        manager.addBook(book1);
        manager.addBook(book2);
        manager.addBook(book3);

        Reader reader1 = new Reader("R001", "Ivan Petrov", "ivan@example.com", Reader.ReaderCategory.STUDENT);
        Reader reader2 = new Reader("R002", "Anna Ivanova", "anna@example.com", Reader.ReaderCategory.TEACHER);
        manager.addReader(reader1);
        manager.addReader(reader2);

        manager.borrowBook("111", "R001", 7);
        manager.borrowBook("222", "R002", 10);

        manager.returnBook("111", "R001");

        System.out.println("\nПоиск по названию 'Java':");
        manager.searchBooksByTitle("java").forEach(System.out::println);

        System.out.println("\nСортировка по году:");
        manager.sortBooksByPublicationYear(manager.getAllBooks()).forEach(System.out::println);
        System.out.println("\nСортировка по доступности:");
        manager.sortBooksByAvailability(manager.getAllBooks()).forEach(System.out::println);


        System.out.println("\nКниги жанра SCIENCE и 2020 года:");
        Iterator<Book> sciBooks2020 = manager.getBooksByGenreAndYearIterator(Book.Genre.SCIENCE, 2020);
        sciBooks2020.forEachRemaining(System.out::println);

        System.out.println("\nКниги с несколькими авторами:");
        Iterator<Book> multiAuthorBooks = manager.getBooksWithMultipleAuthorsIterator(2);
        multiAuthorBooks.forEachRemaining(System.out::println);

        System.out.println("\nПросроченные выдачи:");
        Iterator<Borrowing> overdue = manager.getOverdueBorrowingsIterator();
        overdue.forEachRemaining(System.out::println);

        System.out.println("\nСтатистика по жанрам:");
        for (Map.Entry<Book.Genre, Integer> entry : manager.getGenreStatistics().entrySet()) {
            System.out.printf("%s: %d книг%n", entry.getKey(), entry.getValue());
        }

        // --- Статистика: популярные книги ---
        System.out.println("\nПопулярные книги:");
        for (Map.Entry<Book, Integer> entry : manager.getMostPopularBooks(5).entrySet()) {
            System.out.printf("%s — %d выдач%n", entry.getKey().getTitle(), entry.getValue());
        }

        // --- Статистика: активные читатели ---
        System.out.println("\nАктивные читатели:");
        for (Map.Entry<Reader, Integer> entry : manager.getMostActiveReaders(5).entrySet()) {
            System.out.printf("%s — %d выдач%n", entry.getKey().getName(), entry.getValue());
        }
    }
}