package com.dev.library.service.impl;

import com.dev.library.dao.GenreDao;
import com.dev.library.lib.Inject;
import com.dev.library.lib.Service;
import com.dev.library.model.Genre;
import com.dev.library.service.GenreService;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
