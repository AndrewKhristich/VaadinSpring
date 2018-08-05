package com.example.demo.front.views;

import com.example.demo.front.components.IssueComponent;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("issues")
public class SingleIssueView extends AbstractLayout implements HasUrlParameter<Long> {

    private IssueService issueService;
    private IssueComponent issueForm;
    private Issue issue;

    @Autowired
    public SingleIssueView(IssueService issueService, UserService service) {
        super(issueService, service, Layouts.NONE);
        this.issueService = issueService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        Issue issue = issueService.findById(parameter);
        this.issueForm = new IssueComponent(issue);
        add(issueForm);
    }
}
