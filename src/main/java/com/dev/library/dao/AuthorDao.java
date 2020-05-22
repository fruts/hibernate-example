package com.dev.library.dao;

import com.dev.library.model.Author;
import java.util.List;

public interface AuthorDao {

    Author add(Author author);

    List<Author> getAll();
}
