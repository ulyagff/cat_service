package Dao;

import Entity.Cat;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;
import java.util.List;

public class CatDaoHibernate implements CatDao{
    @Override
    public List<Cat> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cat> cr = cb.createQuery(Cat.class);
        Root<Cat> root = cr.from(Cat.class);
        cr.select(root);

        Query<Cat> query = session.createQuery(cr);
        List<Cat> result = query.getResultList();
        transaction.commit();
        return result;
    }

    @Override
    public Cat getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;    }

    @Override
    public void save(Cat cat) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(Cat cat) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cat> cr = cb.createQuery(Cat.class);
        Root<Cat> root = cr.from(Cat.class);
        cr.select(root);
        Query<Cat> query = session.createQuery(cr);
        List<Cat> allCats = query.getResultList();
        for (Cat cat : allCats) {
            session.delete(cat);
        }

        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        Cat cat = session.get(Cat.class, id);
        session.delete(cat);
        tx1.commit();
        session.close();
    }
}
