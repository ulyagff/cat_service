package Dao;

import Entity.Cat;
import Entity.Owner;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;


import java.util.List;

public class OwnerDaoHibernate implements OwnerDao {
    @Override
    public List<Owner> getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Owner> cr = cb.createQuery(Owner.class);
        Root<Owner> root = cr.from(Owner.class);
        cr.select(root);
        Query<Owner> query = session.createQuery(cr);
        List<Owner> result = query.getResultList();
        transaction.commit();
        return result;
    }

    @Override
    public Owner getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(Owner owner) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Owner> cr = cb.createQuery(Owner.class);
        Root<Owner> root = cr.from(Owner.class);
        cr.select(root);
        Query<Owner> query = session.createQuery(cr);
        List<Owner> allOwners = query.getResultList();
        for (Owner owner : allOwners) {
            session.delete(owner);
        }

        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        Owner owner = session.get(Owner.class, id);
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Cat> getAllCatsByOwnerId(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cat> cr = cb.createQuery(Cat.class);
        Root<Cat> root = cr.from(Cat.class);

        cr.select(root).where(cb.equal(root.get("owner"), id));
        Query<Cat> query = session.createQuery(cr);
        return query.getResultList();
    }
}
