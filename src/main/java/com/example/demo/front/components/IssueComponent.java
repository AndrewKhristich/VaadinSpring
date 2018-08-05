package com.example.demo.front.components;

import com.example.demo.model.Issue;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class IssueComponent extends VerticalLayout {

    private Label name;
    private Label descr;
    private Label date;
    private Label author;
    private Label status;

    public IssueComponent(Issue issue) {
        this.name = new Label(issue.getIssueName());
        this.descr = new Label(issue.getDescription());
        this.date = new Label(issue.getPublishedAt().toString());
        this.author = new Label(issue.getUser().getUsername());
        this.status = new Label(issue.getStatus());



    }
}
