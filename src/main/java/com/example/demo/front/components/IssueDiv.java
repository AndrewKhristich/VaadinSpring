package com.example.demo.front.components;

import com.example.demo.front.views.SingleIssueView;
import com.example.demo.model.Issue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@HtmlImport("styles/shared-styles.html")
public class IssueDiv extends Div {

    private final Issue issue;

    public IssueDiv(Issue issue) {

        addClassName("issue-div");

        this.issue = issue;
        Icon alarm = new Icon(VaadinIcon.CROSS_CUTLERY);
        Icon access = new Icon(VaadinIcon.CHECK);

        Label name = new Label(issue.getIssueName());
        Date publishedAt = issue.getPublishedAt();

        Label date = new Label(publishedAt.toString());

        name.getStyle().set("font-weight", "bold");
        name.getStyle().set("font-size", "140%");
        name.getStyle().set("border-bottom", "1px solid black");

        date.getStyle().set("color", "grey");
        date.getStyle().set("font-size", "70%");

        VerticalLayout verLayout = new VerticalLayout(name, date);
        HorizontalLayout horLayout = new HorizontalLayout(issue.getStatus().equals("Resolved") ? access : alarm, verLayout);
        horLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(horLayout);

        addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(SingleIssueView.class, issue.getId())));

    }
}
