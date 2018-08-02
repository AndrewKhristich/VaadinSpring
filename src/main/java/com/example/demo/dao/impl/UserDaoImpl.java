package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(IssueDaoImpl.class);

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
            throw new UserAlreadyExistsException("User already exists");
        }
        User entity = new User(username, password);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);
        UserRole role = new UserRole();
        role.setAuthority("USER");
        role.setUser(entity);
        entity.setAuthorities(new ArrayList<UserRole>(Arrays.asList(role)));
        em.persist(entity);
    }

}
