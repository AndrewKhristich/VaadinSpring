package com.example.demo.front.components.menu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.RouterLink;

import java.util.function.Consumer;

public class NavigableTab extends Tab {

    public NavigableTab(String name, Class clazz) {
        RouterLink routerLink = new RouterLink(name, clazz);
        add(routerLink);
    }

    public NavigableTab(String name, Class clazz, Object parameter) {
//        RouterLink routerLink = new RouterLink(name, clazz, parameter);
//        add(routerLink);
        setValue(name);
//        addListener(ClickEvent.class, clickEvent -> getUI().ifPresent(ui -> ui.navigate(clazz, parameter)));
        addAttachListener(attachEvent -> getUI().ifPresent(ui -> ui.navigate(clazz, parameter)));

    }
}
