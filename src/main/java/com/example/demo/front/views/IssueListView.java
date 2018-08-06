package com.example.demo.front.views;

import com.example.demo.front.components.IssueDiv;
import com.example.demo.front.components.IssueForm;
import com.example.demo.front.components.IssueList;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Layouts;
import com.example.demo.utils.SecurityUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "issues")
public class IssueListView extends AbstractLayout{


    private List<Issue> issues;

    private ListDataProvider<Issue> provider;
    private Div issueListDiv;

    @Autowired
    public IssueListView(UserService userService, IssueService issueService) {
        super(issueService, userService, Layouts.All_ISSUES);

//        this.setHorizontalComponentAlignment(Alignment.END);
        this.issueListDiv = new Div();

        Button addIss = new Button("Add Issue", new Icon(VaadinIcon.PLUS));

        addIss.addClickListener(event -> {
            Dialog dialog = new Dialog(new IssueForm(provider, issueService));
            dialog.setWidth("300px");
            dialog.setHeight("400px");
            dialog.open();
        });

        if (SecurityUtil.isAuthenticated()){
            addToTop(addIss);
        }

        issues = issueService.findAll();
        issues.forEach(issue -> issueListDiv.add(new IssueDiv(issue)));

        add(issueListDiv);

        this.provider = new ListDataProvider<>(issues);

        provider.addDataProviderListener(dataChangeEvent -> {
            issues.forEach(issue -> issueListDiv.add(new IssueDiv(issue)));
        });
    }

}
