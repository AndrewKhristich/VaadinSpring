package com.example.demo.front.components.auth;

import com.example.demo.service.UserService;
import com.vaadin.flow.component.dialog.Dialog;

public class LoginDialog extends Dialog {

    private UserService service;

    public LoginDialog(UserService service) {
        this.service = service;

        LoginForm loginForm = new LoginForm(service);
        add(loginForm);

    }

}
