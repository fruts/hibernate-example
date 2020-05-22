package com.dev.library.dao.impl;

import com.dev.library.dao.BookDao;
import com.dev.library.exception.DataProcessingException;
import com.dev.library.lib.Dao;
import com.dev.library.model.Author;
import com.dev.library.model.Book;
import com.dev.library.model.Genre;
import com.dev.library.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BookDaoImpl implements BookDao {
    @Override
    public Book add(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add book", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Book WHERE title=:title");
            query.setParameter("title", title);
            return (Book) query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("unable to get book by title", e);
        }
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Predicate predicate = criteriaBuilder.isMember(author, root.get("authors"));
            List<Book> books = session.createQuery(query.where(predicate)).getResultList();
            return books;
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Book WHERE genre=:genre");
            query.setParameter("genre", genre);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("unable to get books by author", e);
        }
    }
}
