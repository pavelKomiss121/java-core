package ru.mentee.power.collections.library;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Borrowing {
    private String isbn;
    private String readerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // TODO: Реализовать конструктор, который принимает ISBN книги, ID читателя, дату выдачи и дату возврата
    public Borrowing(String isbn, String readerId, LocalDate borrowDate, LocalDate returnDate) {
        this.isbn = isbn;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // TODO: Реализовать геттеры и сеттеры для всех полей
    public String getIsbn() {
        return isbn;
    }

    public String getReaderId() {
        return readerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // TODO: Реализовать метод isOverdue(), который возвращает true, если срок возврата истек (текущая дата > dueDate),
    // а книга еще не возвращена (returnDate == null)
    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    // TODO: Реализовать метод isReturned(), который возвращает true, если книга была возвращена (returnDate != null)
    public boolean isReturned() {
        return returnDate != null;
    }

    // TODO: Реализовать метод returnBook(LocalDate returnDate), который устанавливает дату возврата книги
    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // TODO: Переопределить equals и hashCode, чтобы записи считались равными, если у них одинаковые isbn, readerId и borrowDate
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(isbn, borrowing.isbn) && Objects.equals(readerId, borrowing.readerId) && Objects.equals(borrowDate, borrowing.borrowDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, readerId, borrowDate);
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "isbn='" + isbn + '\'' +
                ", readerId='" + readerId + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}