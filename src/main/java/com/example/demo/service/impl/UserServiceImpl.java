package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private PasswordEncoder encoder;
    private AuthenticationManager authManager;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder, AuthenticationManager authManager) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.authManager = authManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User userByName = userDao.findUserByName(username);
            Hibernate.initialize(userByName.getAuthorities());
            return userByName;
        } catch (UsernameNotFoundException e){
            LOG.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void saveUser(String username, String password) {
        User userByName = userDao.findUserByName(username);
        if (userByName != null) {
            throw new UserAlreadyExistsException("User with name '" + username + "' already exists");
        }
        String encodedPass = encoder.encode(password);
        userDao.saveUser(username, encodedPass);
    }
}
