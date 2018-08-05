package com.example.demo.front.components.menu;

import com.example.demo.front.views.IssueListView;
import com.example.demo.front.views.MyIssueList;
import com.example.demo.utils.Layouts;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;


public class SideMenu extends HorizontalLayout implements RouterLayout {

    private final Tab allIssuesTab;
    private final Tab myIssues;
    private final Tabs tabs;

    public SideMenu() {

        this.allIssuesTab = new NavigableTab("All Issues", IssueListView.class);
        this.myIssues = new NavigableTab("My Issues", MyIssueList.class);
        this.tabs = new Tabs();

        tabs.add(allIssuesTab);

        if (SecurityUtil.isAuthenticated()) {
            tabs.add(myIssues);
        }

        tabs.setHeight("40px");

        add(tabs);
    }

    public void setSelected(Layouts layouts) {
        switch (layouts) {
            case MY_ISSUE:
                tabs.setSelectedTab(myIssues);
                break;
            case All_ISSUES:
                tabs.setSelectedTab(allIssuesTab);
                break;
            default: tabs.setSelectedTab(allIssuesTab);
        }
    }
}
