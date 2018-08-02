package com.example.demo.front.components.menu;

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
        RouterLink routerLink = new RouterLink(name, clazz, parameter);
        add(routerLink);
    }
}
