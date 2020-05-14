package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager issueManager = new IssueManager(repository);

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", Label.BUG, Arrays.asList("project1", "project2"), "5.7 M2", new Assignee(4, "Name4", "Surname4"), "26.01.2020", 3, 4);
    private Issue issue2 = new Issue(2, "name2", Status.CLOSED, "author2", Label.FEATURE_REQUEST, Collections.singletonList("project3"), "5.7 Backlog", new Assignee(3, "Name3", "Surname3"), "14.02.2020", 11, 1);
    private Issue issue3 = new Issue(3, "name3", Status.OPEN, "author3", Label.QUESTION, Arrays.asList("project1", "project2"), null, new Assignee(2, "name2", "Surname2"), "16.01.2019", 15, 0);
    private Issue issue4 = new Issue(4, "name4", Status.CLOSED, "author4", Label.BUG, Collections.emptyList(), null, null, "09.04.2019", 3, 2);
    private Issue issue5 = new Issue(5, "name5", Status.OPEN, "author5", Label.QUESTION, Collections.singletonList("project2"), "5.7 M2", null, "06.05.2020", 0, 0);
    private Issue issue6 = new Issue(6, "name6", Status.CLOSED, "author6", Label.FEATURE_REQUEST, Collections.emptyList(), null, new Assignee(1, "Name1", "Surname1"), "11.04.2020", 70, 7);

    @BeforeEach()
    void setUp() {
        issueManager.add(issue1);
        issueManager.add(issue2);
        issueManager.add(issue3);
        issueManager.add(issue4);
        issueManager.add(issue5);
        issueManager.add(issue6);
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> actual = issueManager.findAllOpen();
        List<Issue> expected = Arrays.asList(issue1, issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllClosed() {
        List<Issue> actual = issueManager.findAllClosed();
        List<Issue> expected = Arrays.asList(issue2, issue4, issue6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> actual = issueManager.filterByAuthor(issue -> issue.getAuthor().equals("author2"));
        List<Issue> expected = Arrays.asList(issue2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthor() {
        List<Issue> actual = issueManager.filterByAuthor(issue -> issue.getAuthor().equals("author7"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> actual = issueManager.filterByLabel(issue -> issue.getLabel().equals(Label.QUESTION));
        List<Issue> expected = Arrays.asList(issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabel() {
        List<Issue> actual = issueManager.filterByLabel(issue -> issue.getLabel().equals(Label.DOC));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> actual = issueManager.filterByAssignee(new Assignee(4, "Name4", "Surname4"));
        List<Issue> expected = Arrays.asList(issue1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssignee() {
        List<Issue> actual = issueManager.filterByAssignee(new Assignee(5, "Name5", "Surname5"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}