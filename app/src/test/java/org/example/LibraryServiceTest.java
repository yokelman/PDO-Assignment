package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    @Test
    void borrowBookMarksItUnavailable() {
        Library library = new Library();
        library.addBook(new Book(1, "Clean Code", "Robert C. Martin"));

        library.issueBook(1);

        assertFalse(library.findById(1).isAvailable());
    }
}
