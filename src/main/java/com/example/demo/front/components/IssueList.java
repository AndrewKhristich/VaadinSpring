package com.example.demo.front.components;

import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class IssueList extends VerticalLayout {


    public IssueList(IssueService service) {

        List<Issue> issues = service.findAll();
        issues.forEach(issue -> add(new IssueDiv(issue)));

    }
}
