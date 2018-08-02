package com.example.demo.front.views;

import com.example.demo.front.components.IssueList;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "issues")
public class IssueListView extends AbstractLayout{


    private final Span span;
    private UserService userService;
    private IssueService issueService;

    @Autowired
    public IssueListView(UserService userService, IssueService issueService) {
        super(userService);
        this.userService = userService;
        this.issueService = issueService;
        this.setHorizontalComponentAlignment(Alignment.END);
        this.span = new Span("asd");
        add(new IssueList(issueService));

    }
}
