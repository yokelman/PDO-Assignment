package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No book with id " + id));
    }

    public void issueBook(int id) {
        Book book = findById(id);
        if (!book.isAvailable()) throw new IllegalStateException("Book already issued: " + id);
        book.setAvailable(false);
    }

    public void returnBook(int id) {
        Book book = findById(id);
        if (book.isAvailable()) throw new IllegalStateException("Book not issued: " + id);
        book.setAvailable(true);
    }

    public List<Book> listBooks() {
        return books;
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(1, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(2, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(3, "The Pragmatic Programmer", "Hunt & Thomas"));

        System.out.println("--- Library Catalogue ---");
        library.listBooks().forEach(System.out::println);

        library.issueBook(2);
        System.out.println("\nIssued book 2");
        library.listBooks().forEach(System.out::println);

        library.returnBook(2);
        System.out.println("\nReturned book 2");
        library.listBooks().forEach(System.out::println);
    }
}
