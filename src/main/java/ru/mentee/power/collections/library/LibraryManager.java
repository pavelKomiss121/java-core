package ru.mentee.power.collections.library;

import java.time.LocalDate;
import java.util.*;

public class LibraryManager {
    // TODO: Объявить коллекцию для хранения книг (Map<String, Book> с ключом ISBN)
    private final Map<String, Book> books = new HashMap<>();

    // TODO: Объявить коллекцию для хранения читателей (Map<String, Reader> с ключом ID)
    private final Map<String, Reader> readers = new HashMap<>();

    // TODO: Объявить коллекцию для хранения истории выдач (List<Borrowing>)
    private final List<Borrowing> borrowingHistory = new ArrayList<>();

    // TODO: Объявить коллекцию для группировки книг по жанрам (Map<Book.Genre, Set<Book>>)
    private final Map<Book.Genre, Set<Book>> booksByGenre = new EnumMap<>(Book.Genre.class);

    // TODO: Объявить коллекцию для хранения авторов и их книг (Map<String, List<Book>>)
    private final Map<String, List<Book>> booksByAuthor = new HashMap<>();

    // TODO: Реализовать конструктор, который инициализирует все коллекции
    public LibraryManager() {
        for (Book.Genre genre : Book.Genre.values()) {
            booksByGenre.put(genre, new HashSet<>());
        }
    }

    // ============ Методы для работы с книгами ============

    /**
     * Добавляет книгу в библиотеку
     * @param book книга для добавления
     * @return true если книга добавлена, false если книга с таким ISBN уже существует
     */
    public boolean addBook(Book book) {
        if (!books.containsKey(book.getIsbn())){
            books.put(book.getIsbn(), book);
            booksByGenre.get(book.getGenre()).add(book);
            for (String author : book.getAuthors()) {
                booksByAuthor.computeIfAbsent(author, k -> new ArrayList<>()).add(book);
            }
            return true;
        }
        return false;
    }

    /**
     * Получает книгу по ISBN
     * @param isbn ISBN книги
     * @return книга или null, если книга не найдена
     */
    public Book getBookByIsbn(String isbn) {
        if (isbn == null) return null;
        return books.get(isbn);
    }

    /**
     * Удаляет книгу из библиотеки
     * @param isbn ISBN книги для удаления
     * @return true если книга удалена, false если книга не найдена
     */
    public boolean removeBook(String isbn) {

        if (isbn == null || !books.containsKey(isbn)) return false;
        Book book = books.remove(isbn);

        for (String author: book.getAuthors()){
            List<Book> authorBooks = booksByAuthor.get(author);
            if (authorBooks != null) {
                authorBooks.remove(book);
                if (authorBooks.isEmpty()) booksByAuthor.remove(author);
            }
        }
        return true;
    }

    /**
     * Возвращает список всех книг
     * @return список книг
     */
    public List<Book> getAllBooks() {
        return List.copyOf(books.values());
    }

    /**
     * Возвращает список книг определенного жанра
     * @param genre жанр
     * @return список книг
     */
    public List<Book> getBooksByGenre(Book.Genre genre) {
        if (genre == null) return Collections.emptyList();

        Set<Book> genreBooks = booksByGenre.get(genre);
        if (genreBooks == null) return Collections.emptyList();

        return new ArrayList<>(genreBooks); // преобразуем Set в List
    }

    /**
     * Возвращает список книг определенного автора
     * @param author автор
     * @return список книг
     */
    public List<Book> getBooksByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        List<Book> authorBooks = booksByAuthor.get(author);
        if (authorBooks == null) return Collections.emptyList();

        return authorBooks;
    }

    /**
     * Поиск книг по названию (частичное совпадение)
     * @param titlePart часть названия
     * @return список книг
     */
    public List<Book> searchBooksByTitle(String titlePart) {
        if (titlePart == null) return Collections.emptyList();
        List<Book> result = new ArrayList<>();
        String titleLower = titlePart.toLowerCase().trim();

        for (Book book : books.values()){
            if (book.getTitle().toLowerCase().contains(titleLower))
                result.add(book);
        }
        return result;
    }

    /**
     * Возвращает список доступных книг
     * @return список доступных книг
     */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();

        for(Book book: books.values()){
            if (book.isAvailable())
                result.add(book);
        }
        return result;
    }

    /**
     * Сортирует список книг по названию
     * @param books список книг для сортировки
     * @return отсортированный список
     */
    public List<Book> sortBooksByTitle(List<Book> books) {
        if (books == null) return Collections.emptyList();

        List<Book> sortBooks = new ArrayList<>(books);
        sortBooks.sort(Comparator.comparing(Book::getTitle));
        return sortBooks;
    }

    /**
     * Сортирует список книг по году публикации (от новых к старым)
     * @param books список книг для сортировки
     * @return отсортированный список
     */
    public List<Book> sortBooksByPublicationYear(List<Book> books) {

        if (books == null) return Collections.emptyList();

        List<Book> sortBooks = new ArrayList<>(books);
        sortBooks.sort(Comparator.comparing(Book::getPublicationYear));
        return sortBooks;
    }

    /**
     * Сортирует список книг по доступности (сначала доступные)
     * @param books список книг для сортировки
     * @return отсортированный список
     */
    public List<Book> sortBooksByAvailability(List<Book> books) {

        if (books == null) return Collections.emptyList();

        List<Book> sortBooks = new ArrayList<>(books);
        sortBooks.sort(Comparator.comparing(Book::isAvailable).reversed());
        return sortBooks;

    }

    // ============ Методы для работы с читателями ============

    /**
     * Добавляет читателя
     * @param reader читатель
     * @return true если читатель добавлен, false если читатель с таким ID уже существует
     */
    public boolean addReader(Reader reader) {
        if (reader==null || readers.containsKey(reader.getId())) return false;
        readers.put(reader.getId(), reader);
        return true;
    }

    /**
     * Получает читателя по ID
     * @param readerId ID читателя
     * @return читатель или null, если читатель не найден
     */
    public Reader getReaderById(String readerId) {
        if (readerId == null ) return null;
        return readers.getOrDefault(readerId, null);
    }

    /**
     * Удаляет читателя
     * @param readerId ID читателя
     * @return true если читатель удален, false если читатель не найден
     */
    public boolean removeReader(String readerId) {
        if (readerId == null && !readers.containsKey(readerId)) return false;
        readers.remove(readerId);
        return true;
    }

    /**
     * Возвращает список всех читателей
     * @return список читателей
     */
    public List<Reader> getAllReaders() {
        return new ArrayList<>(readers.values());
    }

    // ============ Методы для выдачи и возврата книг ============

    /**
     * Выдает книгу читателю
     * @param isbn ISBN книги
     * @param readerId ID читателя
     * @param borrowDays количество дней, на которое выдается книга
     * @return true если книга выдана, false если книга недоступна или не найдена
     */
    public boolean borrowBook(String isbn, String readerId, int borrowDays) {
        if (isbn == null || readerId == null || borrowDays <= 0) return false;

        Book book = books.get(isbn);
        Reader reader = readers.get(readerId);

        if (book == null || reader == null || !book.isAvailable()) return false;

        LocalDate borrowDate = LocalDate.now();

        Borrowing borrowing = new Borrowing(isbn, readerId, borrowDate, null);
        borrowing.setDueDate(borrowDate.plusDays(borrowDays));

        borrowingHistory.add(borrowing);
        book.setAvailable(false);
        return true;
    }

    /**
     * Возвращает книгу в библиотеку
     * @param isbn ISBN книги
     * @param readerId ID читателя
     * @return true если книга возвращена, false если запись о выдаче не найдена
     */
    public boolean returnBook(String isbn, String readerId) {
        if (isbn == null || readerId == null) return false;

        for (Borrowing borrowing : borrowingHistory) {
            if (isbn.equals(borrowing.getIsbn())
                            && readerId.equals(borrowing.getReaderId())
            ) {
                borrowing.returnBook(LocalDate.now());
                Book book = books.get(isbn);
                if (book != null) book.setAvailable(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Получает список всех выданных книг
     * @return список выдач
     */
    public List<Borrowing> getAllBorrowings() {
        return new ArrayList<>(borrowingHistory);
    }

    /**
     * Получает список просроченных выдач
     * @return список просроченных выдач
     */
    public List<Borrowing> getOverdueBorrowings() {
        List<Borrowing> overdue = new ArrayList<>();
        for (Borrowing borrowing : borrowingHistory)
            if (borrowing.isOverdue())
                overdue.add(borrowing);
        return overdue;
    }

    public void addBorrowingForTest(Borrowing borrowing) {
        borrowingHistory.add(borrowing);
    }

    /**
     * Получает историю выдач для конкретного читателя
     * @param readerId ID читателя
     * @return список выдач
     */
    public List<Borrowing> getBorrowingsByReader(String readerId) {
        if (readerId == null) return Collections.emptyList();

        List<Borrowing> borrow = new ArrayList<>();
        for (Borrowing borrowing: borrowingHistory)
            if (borrowing.getReaderId().equals(readerId))
                borrow.add(borrowing);
        return borrow;
    }

    /**
     * Получает историю выдач для конкретной книги
     * @param isbn ISBN книги
     * @return список выдач
     */
    public List<Borrowing> getBorrowingsByBook(String isbn) {
        if (isbn == null) return Collections.emptyList();

        List<Borrowing> borrowings = new ArrayList<>();
        for (Borrowing borrowing: borrowingHistory)
            if (borrowing.getIsbn().equals(isbn))
                borrowings.add(borrowing);
        return borrowings;
    }

    /**
     * Продлевает срок выдачи книги
     * @param isbn ISBN книги
     * @param readerId ID читателя
     * @param additionalDays дополнительные дни
     * @return true если срок продлен, false если запись о выдаче не найдена
     */
    public boolean extendBorrowingPeriod(String isbn, String readerId, int additionalDays) {
        if (isbn == null || readerId == null || additionalDays <= 0)
            return false;

        Book book = books.get(isbn);
        Reader reader = readers.get(readerId);

        if (book == null || reader == null ) return false;

        for (Borrowing borrowing: borrowingHistory)
            if (borrowing.getIsbn().equals(isbn) && borrowing.getReaderId().equals(readerId)){
                borrowing.setDueDate(borrowing.getDueDate().plusDays(additionalDays));
               return true;
            }
        return false;
    }

    // ============ Методы для статистики и отчетов ============

    /**
     * Возвращает статистику по жанрам: количество книг в каждом жанре
     * @return карта "жанр -> количество книг"
     */
    public Map<Book.Genre, Integer> getGenreStatistics() {
        Map<Book.Genre, Integer> result = new EnumMap<>(Book.Genre.class);
        for (Book book : books.values())
            result.put(
                    book.getGenre(),
                    result.getOrDefault(book.getGenre(), 0) + 1
            );
        return result;
    }

    /**
     * Возвращает наиболее популярные книги (по количеству выдач)
     * @param limit максимальное количество книг в результате
     * @return список пар "книга -> количество выдач"
     */
    public Map<Book, Integer> getMostPopularBooks(int limit) {
        if (limit <= 0) return Collections.emptyMap();

        Map<String, Integer> popularBooks = new HashMap<>();
        for (Borrowing borrowing: borrowingHistory)
            popularBooks.put(
                    borrowing.getIsbn(),
                    popularBooks.getOrDefault(borrowing.getIsbn(), 0) + 1
            );

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(popularBooks.entrySet());
        sorted.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        Map<Book, Integer> result = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry: sorted){
            Book book = books.get(entry.getKey());
            if (book != null){
                result.put(book, entry.getValue());
                count+=1;
                if (count>=limit) break;
            }
        }
        return result;
    }

    /**
     * Возвращает наиболее активных читателей (по количеству выдач)
     * @param limit максимальное количество читателей в результате
     * @return список пар "читатель -> количество выдач"
     */
    public Map<Reader, Integer> getMostActiveReaders(int limit) {
        if (limit <= 0) return Collections.emptyMap();

        Map<String, Integer> readerCounts = new HashMap<>();
        for (Borrowing borrowing: borrowingHistory)
            readerCounts.put(
                    borrowing.getReaderId(),
                    readerCounts.getOrDefault(borrowing.getReaderId(), 0) + 1
            );

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(readerCounts.entrySet());
        sorted.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        Map<Reader, Integer> result = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry: sorted){
            Reader reader = readers.get(entry.getKey());
            if (reader != null){
                result.put(reader, entry.getValue());
                count+=1;
                if (count>=limit) break;
            }
        }
        return result;
    }

    /**
     * Находит читателей, которые не возвращают книги вовремя
     * @return список читателей с просроченными книгами
     */
    public List<Reader> getReadersWithOverdueBooks() {
        List<Borrowing> overdueBooks = getOverdueBorrowings();
        Set<Reader> resultSet = new HashSet<>();
        for (Borrowing borrowing : overdueBooks)
            resultSet.add(readers.get(borrowing.getReaderId()));
        return new ArrayList<>(resultSet);
    }

    // ============ Методы для работы с итераторами ============

    /**
     * Создает итератор для просмотра книг определенного жанра и года издания
     * @param genre жанр книг
     * @param year год издания
     * @return итератор
     */
    public Iterator<Book> getBooksByGenreAndYearIterator(Book.Genre genre, int year) {
        if (genre == null) return Collections.emptyIterator();

        Set<Book> genreBooks = booksByGenre.getOrDefault(genre, Collections.emptySet());
        List<Book> filtered = new ArrayList<>();
        for (Book book : genreBooks)
            if (book.getPublicationYear() == year)
                filtered.add(book);
        return filtered.iterator();
    }

    /**
     * Создает итератор для просмотра книг с несколькими авторами
     * @param minAuthorsCount минимальное количество авторов
     * @return итератор
     */
    public Iterator<Book> getBooksWithMultipleAuthorsIterator(int minAuthorsCount) {
        if (minAuthorsCount <= 0) return Collections.emptyIterator();

        List<Book> result = new ArrayList<>();
        for (Book book : books.values())
            if (book.getAuthors().size() >= minAuthorsCount)
                result.add(book);
        return result.iterator();
    }

    /**
     * Создает итератор для просмотра просроченных выдач
     * @return итератор
     */
    public Iterator<Borrowing> getOverdueBorrowingsIterator() {
        LocalDate today = LocalDate.now();

        List<Borrowing> result = new ArrayList<>();
        for (Borrowing b : borrowingHistory)
            if (b.getDueDate().isBefore(today))
                result.add(b);
        return result.iterator();
    }
}