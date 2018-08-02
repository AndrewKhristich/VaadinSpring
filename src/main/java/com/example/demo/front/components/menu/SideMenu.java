package com.example.demo.front.components.menu;

import com.example.demo.front.views.IssueListView;
import com.example.demo.front.views.MyIssueList;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.stream.Stream;


public class SideMenu extends HorizontalLayout implements RouterLayout {

    public SideMenu() {
//        addClassNames("container");

        Tab allIssuesTab = new NavigableTab("All Issues",IssueListView.class);
        Tab myIssues = new NavigableTab("My Issues", MyIssueList.class);

        Tabs tabs = new Tabs();
        tabs.add(allIssuesTab);

        if (SecurityUtil.isAuthenticated()) {
            tabs.add(myIssues);
        }

        tabs.setHeight("40px");

        add(tabs);
    }
}
