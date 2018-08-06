package com.example.demo.front.components;

import com.example.demo.model.Comment;
import com.example.demo.service.IssueService;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.dom.DisabledUpdateMode;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("comment-form")
@HtmlImport("src/comment-form.html")
public class CommentForm extends PolymerTemplate<TemplateModel> {

    @Id
    private Div form;

    @Id
    private TextArea descr;

    @Id
    private Checkbox resolve;

    private Comment comment;
    private ListDataProvider provider;
    private IssueService service;
    private BeanValidationBinder<Comment> binder;
    private Long issueId;

    public CommentForm(Long issueId, ListDataProvider provider, IssueService service) {
        this.comment = new Comment();
        this.provider = provider;
        this.service = service;
        this.binder = new BeanValidationBinder<>(Comment.class);
        this.issueId = issueId;

        binder.setBean(comment);
        binder.bind(descr, "description");

        form.setWidth("100%");
        form.getStyle().set("padding", "5px");

        descr.setWidth("95%");
        descr.getStyle().set("margin-left", "auto");
        descr.getStyle().set("margin-right", "auto");
    }

    @EventHandler
    private void comment() {
        try {
            if (binder.isValid()) {
                comment.setStatus(resolve.getValue() ? "Resolved" : null);
                comment.setDescription(descr.getValue());
                service.saveComment(comment, issueId);
                provider.getItems().add(comment);
                provider.refreshAll();
                descr.setValue("");
            }
        } catch (Exception e) {
            Notification.show(e.getMessage(), 3000, Notification.Position.TOP_CENTER);
        }
    }
}
