package ru.mentee.power.tdd;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class LibraryService {

    private final List<Book> books = new ArrayList<>();
    private final String csvFilePath; // Путь к файлу CSV
    private static final String CSV_DELIMITER = ";"; // Разделитель

    // Конструктор по умолчанию (может быть не нужен, если всегда работаем с файлом)
    public LibraryService() {
        this.csvFilePath = null; // Или "default_library.csv"
    }

    // Конструктор для работы с конкретным файлом
    public LibraryService(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        // TODO: Возможно, здесь стоит вызвать loadFromCsv()?
    }

    public void addBook(Book book) {
        if (book != null) {
            this.books.add(book);
        }
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(this.books);
    }

    // Минимальная реализация сохранения в CSV
    public void saveToCsv() throws IOException {
        if (csvFilePath == null) {
            throw new IllegalStateException("Путь к файлу CSV не задан");
        }
        Path path = Paths.get(csvFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Book book : books) {
                writer.write(book.toCsvString(CSV_DELIMITER));
                writer.newLine();
            }
        }
        // Ошибки IOException пробрасываются выше
    }

    public void saveToCsv(String filePath) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым");
        }
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Book book : books) {
                writer.write(book.toCsvString(CSV_DELIMITER));
                writer.newLine();
            }
        }
    }

    // TODO: Реализовать метод loadFromCsv()
    // TODO: Реализовать метод loadFromCsv(String filePath)
    // public void loadFromCsv(String filePath) throws IOException { ... }
}