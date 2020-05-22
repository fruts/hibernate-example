package com.dev.library.service;

import com.dev.library.model.Author;
import java.util.List;

public interface AuthorService {

    Author add(Author author);

    List<Author> getAll();
}
