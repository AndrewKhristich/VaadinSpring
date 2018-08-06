package com.example.demo.front.views;

import com.example.demo.front.components.IssueForm;
import com.example.demo.front.components.auth.LoginForm;
import com.example.demo.front.components.auth.RegistrationForm;
import com.example.demo.front.components.menu.SideMenu;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.RouterLayout;

public abstract class AbstractLayout extends VerticalLayout implements RouterLayout {

    private final Button logout;
    private final RegistrationForm registrationForm;
    private final Button login;
    private final LoginForm loginDialog;
    private final SideMenu sideMenu;
    private final HorizontalLayout authTop;


    public AbstractLayout(IssueService issueService, UserService service, Layouts layout) {
        this.registrationForm = new RegistrationForm(service);
        this.loginDialog = new LoginForm(service);
        this.login = new Button("Sign in", event -> new Dialog(loginDialog).open());
        this.logout = new Button("Logout");
        this.sideMenu = new SideMenu();
        sideMenu.setSelected(layout);

        this.authTop = new HorizontalLayout();

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

        authTop.getStyle().set("margin-left", "auto");
        authTop.getStyle().set("margin-right", "auto");
        getStyle().set("width", "fit-content");
        getStyle().set("min-width", "50%");
        getStyle().set("margin-left", "auto");
        getStyle().set("margin-right", "auto");
    }

    public void addToTop(Component component) {
        authTop.add(component);
    }

    public void triggerLogin() { login.click(); }
}
