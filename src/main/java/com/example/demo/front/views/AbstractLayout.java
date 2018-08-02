package com.example.demo.front.views;

import com.example.demo.front.components.auth.*;
import com.example.demo.front.components.menu.SideMenu;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

import java.awt.*;


public abstract class AbstractLayout extends VerticalLayout implements RouterLayout {

    private final Button logout;
    private final RegistrationForm registrationForm;
    private final Button login;
    private final LoginDialog loginDialog;

    public AbstractLayout(UserService service) {
        this.registrationForm = new RegistrationForm(service);
        this.loginDialog = new LoginDialog(service);
        this.login = new Button("Sign in", event -> loginDialog.open());
        this.logout = new Button("Logout");

        HorizontalLayout authTop = new HorizontalLayout();
        SideMenu sideMenu = new SideMenu();

        if (SecurityUtil.isAuthenticated()) {
            Button userLabel = new Button(SecurityUtil.getAuthorizedUsername());
            authTop.add(userLabel, sideMenu, logout);
        } else {
            authTop.add(login, sideMenu, registrationForm);
        }

        add(authTop);
        logout.addClickListener(event -> {
            SecurityUtil.unAuthenticate();
            PageUtils.reloadPage();
        });

        getStyle().set("width", "fit-content");
        getStyle().set("margin-left", "auto");
        getStyle().set("margin-right", "auto");
    }
}
