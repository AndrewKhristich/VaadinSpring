package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.util.List;

public interface IssueService {

    List<Issue> findAll();

    Issue findById(Long parameter);

    void saveIssue(@Valid Issue issue, String username);

    @PreAuthorize("isAuthenticated()")
    List<Issue> findAllOfCurrentUser(String currentUser);

    List<Comment> findIssueComments(Long parameter);

    @PreAuthorize("isAuthenticated()")
    void saveComment(Comment comment, Long issueId);
}
