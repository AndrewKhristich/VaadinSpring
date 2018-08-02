package com.example.demo.front;

import com.example.demo.front.views.SingleIssueView;
import com.example.demo.model.Issue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.swing.*;

public class IssueDiv extends Div {

    private final Issue issue;

    public IssueDiv(Issue issue) {
        this.issue = issue;
        Icon alarm = new Icon(VaadinIcon.ALARM);
        Icon access = new Icon(VaadinIcon.ACCESSIBILITY);

        Label name = new Label(issue.getIssueName());
        Label date = new Label(issue.getPublishedAt().toString());

        VerticalLayout verLayout = new VerticalLayout(name, date);
        HorizontalLayout horLayout = new HorizontalLayout(issue.getStatus().equals("Resolved") ? access : alarm, verLayout);
        horLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(horLayout);

        addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(SingleIssueView.class, issue.getId())));

    }
}
