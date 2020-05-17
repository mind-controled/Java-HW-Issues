package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssueComparator;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;
import ru.netology.repository.IssueRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager issueManager = new IssueManager(repository);
    private IssueComparator comparator = new IssueComparator();

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", EnumSet.of(Label.BUG, Label.FEATURE_REQUEST), Arrays.asList("project1", "project2"), "5.7 M2", new Assignee(4, "Name4", "Surname4"), LocalDate.of(2020, 3, 1), 3, 4);
    private Issue issue2 = new Issue(2, "name2", Status.CLOSED, "author2", EnumSet.of(Label.FEATURE_REQUEST), Collections.singletonList("project3"), "5.7 Backlog", new Assignee(3, "Name3", "Surname3"), LocalDate.of(2020, 2, 14), 11, 1);
    private Issue issue3 = new Issue(3, "name3", Status.OPEN, "author3", EnumSet.of(Label.QUESTION), Arrays.asList("project1", "project2"), null, new Assignee(2, "name2", "Surname2"), LocalDate.of(2019, 1, 16), 15, 0);
    private Issue issue4 = new Issue(4, "name4", Status.CLOSED, "author4", EnumSet.of(Label.BUG), Collections.emptyList(), null, new Assignee(5, "name5", "Surname5"), LocalDate.of(2019, 4, 9), 3, 2);
    private Issue issue5 = new Issue(5, "name5", Status.OPEN, "author5", EnumSet.of(Label.BUG), Collections.singletonList("project2"), "5.7 M2", new Assignee(6, "name6", "Surname6"), LocalDate.of(2020, 5, 6), 0, 0);
    private Issue issue6 = new Issue(6, "name6", Status.CLOSED, "author6", EnumSet.of(Label.FEATURE_REQUEST), Collections.emptyList(), null, new Assignee(1, "Name1", "Surname1"), LocalDate.of(2020, 4, 11), 70, 7);

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
        List<Issue> actual = issueManager.filterByAuthor("author2", comparator);
        List<Issue> expected = Arrays.asList(issue2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAuthor() {
        List<Issue> actual = issueManager.filterByAuthor("author7", comparator);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> actual = issueManager.filterByLabel(EnumSet.of(Label.QUESTION), comparator);
        List<Issue> expected = Arrays.asList(issue3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabels() {
        List<Issue> actual = issueManager.filterByLabel(EnumSet.of(Label.BUG,Label.FEATURE_REQUEST), comparator);
        List<Issue> expected = Arrays.asList(issue1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByLabel() {
        List<Issue> actual = issueManager.filterByLabel(EnumSet.of(Label.DOC), comparator);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldNotFilterByLabels() {
        List<Issue> actual = issueManager.filterByLabel(EnumSet.of(Label.BUG, Label.QUESTION, Label.FEATURE_REQUEST), comparator);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> actual = issueManager.filterByAssignee(new Assignee(4, "Name4", "Surname4"), comparator);
        List<Issue> expected = Arrays.asList(issue1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFilterByAssignee() {
        List<Issue> actual = issueManager.filterByAssignee(new Assignee(7, "Name7", "Surname7"), comparator);
        assertEquals(0, actual.size());
    }
}