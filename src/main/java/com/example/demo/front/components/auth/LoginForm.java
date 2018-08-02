package com.example.demo.front.components.auth;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("login-form")
@HtmlImport("src/login-form.html")
public class LoginForm extends PolymerTemplate<TemplateModel> {

    @Id
    private TextField name;

    @Id
    private PasswordField password;

    @Id
    private Button submit;

    @Id
    private Button cancel;

    private Binder<User> binder;
    private UserService service;
    private User user;
    private Dialog dialog;

    public LoginForm(UserService service, Dialog dialog) {
        this.service = service;
        this.binder = new Binder<>(User.class);
        this.user = new User("","");
        this.dialog = dialog;

        password.addKeyPressListener(Key.ENTER, event -> submit.click());
        name.addKeyPressListener(Key.ENTER, event -> submit.click());
    }

    @EventHandler
    private void login() {
        String nameValue = name.getValue();
        String passwordValue = password.getValue();
        try {
            service.authenticate(nameValue, passwordValue);
            PageUtils.reloadPage();
        } catch (Exception e) {
            password.setValue("");
            Notification.show("Wrong name or password", 3000, Notification.Position.TOP_CENTER);
        }

    }

    @EventHandler
    private void cancel() {
        dialog.close();
        name.setValue("");
    }

}
