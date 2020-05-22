package com.dev.library.dao.impl;

import com.dev.library.dao.GenreDao;
import com.dev.library.exception.DataProcessingException;
import com.dev.library.lib.Dao;
import com.dev.library.model.Genre;
import com.dev.library.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre add(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add genre", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Genre> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Genre> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Genre.class);
            criteriaQuery.from(Genre.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("UNABLE TO GET ALL GENRES", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
