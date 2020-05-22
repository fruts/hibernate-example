package com.dev.library.dao.impl;

import com.dev.library.dao.AuthorDao;
import com.dev.library.exception.DataProcessingException;
import com.dev.library.lib.Dao;
import com.dev.library.model.Author;
import com.dev.library.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {

    @Override
    public Author add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add author", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Author> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Author.class);
            criteriaQuery.from(Author.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("UNABLE TO GET ALL AUTHORS", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
