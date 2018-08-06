package com.example.demo.front.components;

import com.example.demo.model.Comment;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CommentDiv extends Div {

    private Icon icon;

    public CommentDiv(Comment comment) {
        setWidth("100%");
        Div authorDiv = new Div(new Label(comment.getAuthor()));
        authorDiv.getStyle().set("background-color", "#e8ebef");
        authorDiv.getStyle().set("border", "1px solid #e8ebef");
        authorDiv.getStyle().set("width", "100%");
        authorDiv.getStyle().set("padding-left", "5px");

        Div descrDiv = new Div(new Label(comment.getDescription()));
        descrDiv.getStyle().set("border", "1px solid #e8ebef");
        descrDiv.getStyle().set("margin-top", "0");
        descrDiv.getStyle().set("padding-bottom", "10px");
        descrDiv.getStyle().set("padding-left", "5px");
        descrDiv.getStyle().set("width", "100%");

        VerticalLayout verticalLayout = new VerticalLayout(authorDiv, descrDiv);

        icon = new Icon(VaadinIcon.CHECK);

        if (comment.getStatus()!=null && comment.getStatus().equals("Resolved")) {
            icon.setColor("green");
        } else {
            icon.setColor("white");
        }

        icon.setSize("17px");
        HorizontalLayout layout = new HorizontalLayout(icon, verticalLayout);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(layout);
    }
}
