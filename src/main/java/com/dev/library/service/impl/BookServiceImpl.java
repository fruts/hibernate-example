package com.dev.library.service.impl;

import com.dev.library.dao.BookDao;
import com.dev.library.lib.Inject;
import com.dev.library.lib.Service;
import com.dev.library.model.Author;
import com.dev.library.model.Book;
import com.dev.library.model.Genre;
import com.dev.library.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.getAllBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.getAllBooksByGenre(genre);
    }
}
