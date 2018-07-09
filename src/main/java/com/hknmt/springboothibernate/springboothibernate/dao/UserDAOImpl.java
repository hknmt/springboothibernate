package com.hknmt.springboothibernate.springboothibernate.dao;

import com.hknmt.springboothibernate.springboothibernate.Utils.HibernateUtil;
import com.hknmt.springboothibernate.springboothibernate.entities.User;
import org.hibernate.*;

public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public User getUser(int user_id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = session.beginTransaction();
            user = (User) session.createQuery("from User where user_id='" + "user_id" + "'").uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }}
