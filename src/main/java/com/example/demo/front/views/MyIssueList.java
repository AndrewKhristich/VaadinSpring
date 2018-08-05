package com.example.demo.front.views;

import com.example.demo.front.components.IssueDiv;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "issues/my")
public class MyIssueList extends AbstractLayout {

    @Autowired
    public MyIssueList(IssueService issueService, UserService service) {
        super(issueService, service, Layouts.MY_ISSUE);

        List<Issue> issues = issueService.findAllOfCurrentUser(SecurityUtil.getAuthorizedUsername());

        issues.forEach(issue -> add(new IssueDiv(issue)));

    }

}
