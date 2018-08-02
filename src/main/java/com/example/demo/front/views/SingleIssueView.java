package com.example.demo.front.views;

import com.example.demo.service.UserService;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("issue")
public class SingleIssueView extends AbstractLayout implements HasUrlParameter<Long> {

    public SingleIssueView(UserService service) {
        super(service);
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {

    }
}
