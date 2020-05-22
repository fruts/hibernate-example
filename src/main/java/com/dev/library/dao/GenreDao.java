package com.dev.library.dao;

import com.dev.library.model.Genre;
import java.util.List;

public interface GenreDao {

    Genre add(Genre genre);

    List<Genre> getAll();
}
