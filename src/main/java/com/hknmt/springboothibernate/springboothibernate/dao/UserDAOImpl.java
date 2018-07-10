package com.hknmt.springboothibernate.springboothibernate.dao;

import com.hknmt.springboothibernate.springboothibernate.Utils.HibernateUtil;
import com.hknmt.springboothibernate.springboothibernate.entities.User;
import org.hibernate.*;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
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
            user = (User) session.createQuery("UserRes" + user_id).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean checkLogin(String userName, String passWord) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userRes = null;
        try {
            transaction = session.beginTransaction();
            userRes = session.createQuery("'from User where userName ='" + userName + "'and password='" + passWord).list();
            transaction.commit();
            if (userRes.size() >= 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkUserName(String userName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userRes = null;
        try {
            transaction = session.beginTransaction();
            userRes = session.createQuery("'from User where userName ='" + userName ).list();
            transaction.commit();
            if (userRes.size() >= 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
