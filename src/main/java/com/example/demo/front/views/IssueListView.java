package com.example.demo.front.views;

import com.example.demo.front.components.IssueDiv;
import com.example.demo.front.components.IssueList;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "issues")
public class IssueListView extends AbstractLayout{


    private final Span span;
    private List<Issue> issues;

    private UserService userService;
    private IssueService issueService;

    public IssueListView(UserService userService, IssueService issueService) {
        super(issueService, userService, Layouts.All_ISSUES);
        this.userService = userService;
        this.issueService = issueService;
        this.setHorizontalComponentAlignment(Alignment.END);
        this.span = new Span("asd");

        issues = issueService.findAll();
        issues.forEach(issue -> add(new IssueDiv(issue)));

    }

}
