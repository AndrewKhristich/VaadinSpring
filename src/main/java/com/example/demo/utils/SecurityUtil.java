package com.example.demo.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    static public String getAuthorizedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    static public boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    static public void unAuthenticate() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}

