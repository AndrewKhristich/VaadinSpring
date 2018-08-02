package com.example.demo.front.views;

import com.example.demo.service.UserService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "issue/my")
public class MyIssueList extends AbstractLayout {

    @Autowired
    public MyIssueList(UserService service) {
        super(service);


    }

}
