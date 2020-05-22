package com.dev.library.service.impl;

import com.dev.library.dao.AuthorDao;
import com.dev.library.lib.Inject;
import com.dev.library.lib.Service;
import com.dev.library.model.Author;
import com.dev.library.service.AuthorService;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
