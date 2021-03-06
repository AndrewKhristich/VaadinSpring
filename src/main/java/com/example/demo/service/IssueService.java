package com.example.demo.service;

import com.example.demo.model.Issue;

import javax.validation.Valid;
import java.util.List;

public interface IssueService {

    List<Issue> findAll();

    Issue findById(Long parameter);

    void saveIssue(@Valid Issue issue, String username);

    List<Issue> findAllOfCurrentUser(String currentUser);
}
