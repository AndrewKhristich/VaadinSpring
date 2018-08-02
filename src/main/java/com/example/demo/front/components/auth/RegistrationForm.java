package com.example.demo.front.components.auth;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.dom.PropertyChangeEvent;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("registration-form")
@HtmlImport("src/registration-form.html")
public class RegistrationForm extends PolymerTemplate<TemplateModel>
        implements HasEnabled {

    @Id
    private TextField name;

    @Id
    private PasswordField password;

    @Id
    private Button submit;

    private Binder<User> binder;

    private UserService userService;

    private User user;

    public RegistrationForm(UserService userService) {
        this.userService = userService;
        this.binder = new Binder<>(User.class);
        this.user = new User("", "");

        submit.setEnabled(false);

        BeanValidationBinder<User> bind = new BeanValidationBinder<>(User.class);

        bind.bind(name, "username");
        bind.bind(password, "password");

        bind.addStatusChangeListener(event -> {
            boolean isValid = event.getBinder().isValid();
            boolean hasChanges = event.getBinder().hasChanges();

            submit.setEnabled(hasChanges && isValid);
        });
    }

    private void handleEnabled(PropertyChangeEvent event) {
        setEnabled((Boolean) event.getValue());
    }

    @EventHandler
    private void register() {
        String userName = name.getValue();
        String password = this.password.getValue();
        try {
            binder.writeBean(new User(userName, password));
            userService.saveUser(userName, password);
            userService.authenticate(userName, password);
            PageUtils.reloadPage();
        } catch (ValidationException e) {
            Notification.show(e.getMessage(),3000,Notification.Position.TOP_CENTER);
        }
    }
}