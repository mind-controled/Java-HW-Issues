package ru.netology.manager;

import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Status;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findAllOpen() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.OPEN)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    public List<Issue> findAllClosed() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.CLOSED)) {
                temp.add(issue);

            }
        }
        return temp;
    }

    public List<Issue> filterByAuthor(Predicate<Issue> predicate) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> filterByLabel(Predicate<Issue> predicate) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> filterByAssignee(Assignee assignee) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getSetAssignee().contains(assignee)) {
                temp.add(issue);
            }
        }
        return temp;
    }
}