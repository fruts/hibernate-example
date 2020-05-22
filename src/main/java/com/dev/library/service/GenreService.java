package com.dev.library.service;

import com.dev.library.model.Genre;
import java.util.List;

public interface GenreService {

    Genre add(Genre genre);

    List<Genre> getAll();
}
