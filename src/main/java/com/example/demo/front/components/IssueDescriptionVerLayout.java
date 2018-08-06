package com.example.demo.front.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class IssueDescriptionVerLayout extends VerticalLayout {

    public IssueDescriptionVerLayout(Label author, Label descr) {
        Div authorDiv = new Div(author);
        authorDiv.getStyle().set("background-color", "#e8ebef");
        authorDiv.getStyle().set("width", "100%");
        authorDiv.getStyle().set("padding-left", "5px");
        Div descrDiv = new Div(descr);
        descrDiv.getStyle().set("border", "1px solid #e8ebef");
        descrDiv.getStyle().set("margin-top", "0");
        descrDiv.getStyle().set("padding-bottom", "10px");
        descrDiv.getStyle().set("padding-left", "5px");
        descrDiv.getStyle().set("width", "100%");
        this.add(authorDiv, descrDiv);
    }
}
