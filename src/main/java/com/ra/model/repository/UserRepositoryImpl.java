package com.ra.model.repository;

import com.ra.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    // tiem SessionFactory
    private SessionFactory sessionFactory;
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        // khoi tao doi tuong sesssion
        Session session = sessionFactory.openSession();
        try {
            list = session.createQuery("from User").list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        User user = new User() ;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User WHERE id = :id", User.class) ;
            query.setParameter("id", id) ;
            user = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e ) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public User saveOrUpdate(User user) {
       Session session = sessionFactory.openSession()  ;
       try {
           session.beginTransaction();
           // Kiểm tra xem người dùng với cùng ID đã tồn tại trong cơ sở dữ liệu hay chưa
           User existingUser = session.get(User.class, user.getId());
           if (existingUser != null) {
               session.merge(user) ;
           } else {
               session.save(user) ;
           }
           session.getTransaction().commit();
       } catch (Exception e) {
           if(session.getTransaction() != null) {
               session.getTransaction().rollback();
           }
           e.printStackTrace();
       } finally {
           session.close();
       }
       return user ;
    }


    @Override
    public Boolean delete(Integer id) {
        Session session = sessionFactory.openSession();
        Boolean isDelete = false ;
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("delete from User where id = :id", User.class) ;
            query.setParameter("id",id) ;
            if ( query.executeUpdate() > 0 ) {
                isDelete = true ;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isDelete;
    }
}
