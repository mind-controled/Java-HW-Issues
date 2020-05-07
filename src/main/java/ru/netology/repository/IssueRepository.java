package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.domain.Status;

import java.util.ArrayList;

import static ru.netology.domain.Status.CLOSED;

public class IssueRepository {
    ArrayList<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public ArrayList<Issue> findAll() {
        return issues;
    }

    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setStatus(Status.OPEN);
            }
        }
    }

    public void closeById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setStatus(CLOSED);
            }
        }
    }
}