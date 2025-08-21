package ru.mentee.power.io.model;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Borrowing implements Serializable {

  @Serial
  private static final long serialVersionUID = 3L;

  private final String isbn;
  private final String readerId;
  private final LocalDate borrowDate;
  private LocalDate dueDate;
  private LocalDate returnDate;

  public Borrowing(String isbn, String readerId, LocalDate borrowDate, LocalDate returnDate) {
    this.isbn = isbn;
    this.readerId = readerId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
  }

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

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }


  public boolean isOverdue() {
    return returnDate == null && LocalDate.now().isAfter(dueDate);
  }

  public boolean isReturned() {
    return returnDate != null;
  }

  public void returnBook(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Borrowing borrowing = (Borrowing) o;
    return Objects.equals(isbn, borrowing.isbn) && Objects.equals(readerId, borrowing.readerId)
        && Objects.equals(borrowDate, borrowing.borrowDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn, readerId, borrowDate);
  }

  @Override
  public String toString() {
    return "Borrowing{" + "isbn='" + isbn + '\'' + ", readerId='" + readerId + '\''
        + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate
        + '}';
  }
}