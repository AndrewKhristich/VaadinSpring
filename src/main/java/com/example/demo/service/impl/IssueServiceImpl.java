package com.example.demo.service.impl;

import com.example.demo.dao.IssueDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import com.example.demo.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueDao issueDao;
    private UserDao userDao;

    @Autowired
    public IssueServiceImpl(IssueDao issueDao, UserDao userDao) {
        this.issueDao = issueDao;
        this.userDao = userDao;
    }

    @Override
    public List<Issue> findAll() {
        return issueDao.findAllIssues();
    }

    @Override
    public Issue findById(Long parameter) {
        return issueDao.findById(parameter);
    }

    @Override
    public void saveIssue(Issue issue, String username) {
        issue.setPublishedAt(new Date());
        issue.setUser(userDao.findUserByName(username));
        issue.setStatus("Created");
        issueDao.save(issue);
    }

    @Override
    public List<Issue> findAllOfCurrentUser(String currentUser) {
        return issueDao.findIssuesByUsername(currentUser);
    }

    @Override
    public List<Comment> findIssueComments(Long parameter) {
        return issueDao.findIssueCommentsById(parameter);
    }

    @Override
    public void saveComment(Comment comment, Long issueId) {
        comment.setAuthor(SecurityUtil.getAuthorizedUsername());
        Issue issue = new Issue();
        issue.setId(issueId);
        comment.setIssue(issue);
        issueDao.saveComment(comment);
    }
}
