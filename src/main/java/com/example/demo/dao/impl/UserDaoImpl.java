package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

//    private static final Logger LOG = LoggerFactory.getLogger(IssueDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByName(String username) {
        User user = em.find(User.class, username);
        return user;
    }

    @Override
    public void saveUser(String username, String password) {
        User user = em.find(User.class, username);
        if (user!=null){
            throw new UserAlreadyExists("User already exists");
        }
        em.persist(new User(username, password));
    }

}
