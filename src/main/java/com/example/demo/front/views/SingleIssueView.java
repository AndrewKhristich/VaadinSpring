package com.example.demo.front.views;

import com.example.demo.front.components.CommentDiv;
import com.example.demo.front.components.CommentForm;
import com.example.demo.front.components.IssueComponent;
import com.example.demo.front.components.IssueDescriptionVerLayout;
import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

@Route("issues")
public class SingleIssueView extends AbstractLayout implements HasUrlParameter<Long> {

    private IssueService issueService;
    private IssueComponent issueForm;
    private Issue issue;
    private List<Comment> commentList;
    private ListDataProvider<Comment> provider;

    private Label name;
    private Label descr;
    private Label author;
    private Label status;
    private Label date;
    private Span authorNameSpan;
    private VerticalLayout comments;
    private Div commentFormDiv;

    @Autowired
    public SingleIssueView(IssueService issueService, UserService service) {
        super(issueService, service, Layouts.NONE);
        this.issueService = issueService;
        this.name = new Label();
        this.descr = new Label();
        this.authorNameSpan = new Span();
        this.author = new Label();
        this.status = new Label();
        this.date = new Label();
        this.comments = new VerticalLayout();
        this.commentList = new ArrayList<>();
        this.commentFormDiv = new Div();


        HorizontalLayout horLayout = new HorizontalLayout(status, authorNameSpan, date);
        VerticalLayout verLayout = new VerticalLayout(name, horLayout);

        VerticalLayout issueDescrLayout = new IssueDescriptionVerLayout(author, descr);

        add(verLayout, issueDescrLayout);

        provider = new ListDataProvider<>(commentList);
        provider.addDataProviderListener(dataChangeEvent ->
                    comments.add(new CommentDiv(commentList.get(commentList.size() - 1)))
        );

        add(comments, commentFormDiv);

        name.getStyle().set("font-weight", "bold");
        name.getStyle().set("font-size", "150%");

        status.getStyle().set("color", "white");
        status.getStyle().set("border-radius", "5px");
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        Issue issue = issueService.findById(parameter);
        issueForm = new IssueComponent(issue);
        add(issueForm);
        authorNameSpan.setText(issue.getUser().getUsername());
        authorNameSpan.getStyle().set("font-weight", "bold");
        author.add(new Span("was created by: "), authorNameSpan);

        name.setText(issue.getIssueName());
        descr.setText(issue.getDescription());
        status.setText(issue.getStatus());
        date.setText(issue.getPublishedAt().toString());

        if (issue.getStatus().equals("Resolved")) {
            status.getStyle().set("background-color", "green");
        } else {
            status.getStyle().set("background-color", "red");
        }

        List<Comment> issueComments = issueService.findIssueComments(parameter);
        issueComments.forEach(comment -> comments.add(new CommentDiv(comment)));

        if (SecurityUtil.isAuthenticated()) {
            CommentForm commentForm = new CommentForm(parameter, provider, issueService);
            commentFormDiv.add(commentForm);
        } else {
            Button signIn = new Button("Sign In");
            commentFormDiv.add(new Span("Need to "), signIn, new Span("to comment this issue"));
            signIn.getStyle().set("background-color", "#e8ebef");
            commentFormDiv.getStyle().set("background-color", "#e8ebef");
            signIn.addClickListener(event1 -> triggerLogin());
        }


        commentFormDiv.getStyle().set("margin-left", "auto");
        commentFormDiv.getStyle().set("margin-right", "auto");
    }
}