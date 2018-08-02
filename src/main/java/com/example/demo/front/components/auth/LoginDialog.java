package com.example.demo.front.components.auth;

import com.example.demo.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Input;

public class LoginDialog extends Dialog {

    private Input login;

    private Input password;

    private Button submit;

    private Button cancel;

    private UserService service;

    public LoginDialog(UserService service) {
        this.service = service;

        LoginForm loginForm = new LoginForm(service, this);
        add(loginForm);

    }

}
