package com.example.demo.dao.impl;

import com.example.demo.dao.IssueDao;
import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class IssueDaoImpl implements IssueDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Issue> findIssuesByUsername(String username) {
        return em.createQuery("from Issue where author = ?1", Issue.class)
                .setParameter(1, username).getResultList();
    }

    @Override
    public List<Comment> findIssueCommentsById(Long id) {
        return em.createQuery("from Comment where issue_id = ?1", Comment.class)
                .setParameter(1, id).getResultList();
    }

    @Override
    public List<Issue> findAllIssues() {
        return em.createQuery("from Issue iss order by iss.publishedAt desc", Issue.class).getResultList();
    }

    @Override
    public Issue findById(Long parameter) {
        return em.createQuery("from Issue iss where iss.id = ?1", Issue.class)
                .setParameter(1, parameter).getSingleResult();
    }

    @Override
    public void save(Issue issue) {
        em.merge(issue);
    }
}
