package com.dev.library;

import com.dev.library.lib.Injector;
import com.dev.library.model.Author;
import com.dev.library.model.Book;
import com.dev.library.model.Genre;
import com.dev.library.service.AuthorService;
import com.dev.library.service.BookService;
import com.dev.library.service.GenreService;
import java.util.List;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.library");
    private static BookService bookService = (BookService) INJECTOR.getInstance(BookService.class);
    private static AuthorService authorService
            = (AuthorService) INJECTOR.getInstance(AuthorService.class);
    private static GenreService genreService
            = (GenreService) INJECTOR.getInstance(GenreService.class);

    public static void main(String[] args) {
        Genre fantastic = new Genre();
        fantastic.setName("fantastic");
        Author husenko = new Author();
        husenko.setName("Sanya");
        Book hello = new Book();
        hello.setAuthors(List.of(husenko));
        hello.setTitle("coolest book");
        hello.setGenre(fantastic);
        genreService.add(fantastic);
        authorService.add(husenko);
        bookService.add(hello);
        authorService.getAll().forEach(System.out::println);
        genreService.getAll().forEach(System.out::println);
        System.out.println("BY TITLE");
        System.out.println(bookService.getByTitle("coolest book"));
        System.out.println("BY AUTHOR");
        bookService.getAllBooksByAuthor(husenko).forEach(System.out::println);
        System.out.println("BY GENRE");
        bookService.getAllBooksByGenre(fantastic).forEach(System.out::println);

    }
}
