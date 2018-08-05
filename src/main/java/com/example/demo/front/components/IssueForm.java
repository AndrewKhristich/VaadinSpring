package com.example.demo.front.components;

import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("issue-form")
@HtmlImport("src/issue-form.html")
public class IssueForm extends PolymerTemplate<TemplateModel> {

    @Id
    private Label author;

    @Id
    private TextField name;

    @Id
    private TextArea description;

    @Id
    private Button submit;

    private final Issue issue;
    private final BeanValidationBinder<Issue> binder;
    private final IssueService service;
    private final String username;

    public IssueForm(IssueService service) {
        this.binder = new BeanValidationBinder<>(Issue.class);
        this.service = service;
        this.username = SecurityUtil.getAuthorizedUsername();

        issue = new Issue();
        author.setText(username);


        binder.setBean(issue);
        binder.bind(name, "issueName");
        binder.bind(description, "description");

    }

    @EventHandler
    private void save() {
        try {
            if (binder.isValid()) {
                issue.setIssueName(name.getValue());
                issue.setDescription(description.getValue());
                service.saveIssue(issue, username);
                getParent().ifPresent(component -> {
                    if (component instanceof Dialog) {
                        Dialog component1 = (Dialog) component;
                        component1.close();
                    }
                });
                PageUtils.reloadPage();
            }
        } catch (Exception e) {
            Notification.show("Some Error", 3000, Notification.Position.TOP_CENTER);
        }
    }
}
