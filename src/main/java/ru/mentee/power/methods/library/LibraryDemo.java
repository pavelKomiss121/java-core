package ru.mentee.power.methods.library;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library(5);

        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book("Преступление и наказание", "Федор Достоевский", 1866);
        Book book3 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("== Доступные книги ==");
        printBooks(library.listAvailableBooks());

        // Выдаём книги
        library.checkoutBook("Война и мир");
        library.checkoutBook("Мастер и Маргарита");

        System.out.println("===============================================================");
        System.out.println("\n== После выдачи ==");
        System.out.println("-- Доступные --");
        printBooks(library.listAvailableBooks());
        System.out.println("-- Выданные --");
        printBooks(library.listCheckedOutBooks());

        // Возвращаем одну книгу
        library.returnBook("Война и мир");

        System.out.println("===============================================================");
        System.out.println("\n== После возврата ==");
        System.out.println("-- Доступные --");
        printBooks(library.listAvailableBooks());
        System.out.println("-- Выданные --");
        printBooks(library.listCheckedOutBooks());
    }

    private static void printBooks(Book[] books) {
        if (books.length == 0) {
            System.out.println("Нет книг");
        } else {
            for (Book book : books) {
                System.out.println(book);
                System.out.println();
            }
        }
    }
}