package com.dev.library.dao;

import com.dev.library.model.Author;
import com.dev.library.model.Book;
import com.dev.library.model.Genre;
import java.util.List;

public interface BookDao {

    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getAllBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);
}
