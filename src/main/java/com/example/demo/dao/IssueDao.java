package com.example.demo.dao;

import com.example.demo.model.Comment;
import com.example.demo.model.Issue;

import java.util.List;

public interface IssueDao {
    List<Issue> findIssuesByUsername(String username);
    List<Comment> findIssueCommentsById(Long id);
    List<Issue> findAllIssues();

    Issue findById(Long parameter);

    void save(Issue issue);
}
