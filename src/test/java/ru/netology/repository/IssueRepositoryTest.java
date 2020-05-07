package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository issueRepository = new IssueRepository();

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", Label.BUG, Arrays.asList("project1", "project2"), "5.7 M2", new Assignee(4, "Name4", "Surname4"), "26.01.2020", 3, 4);
    private Issue issue2 = new Issue(2, "name2", Status.CLOSED, "author2", Label.FEATURE_REQUEST, Collections.singletonList("project3"), "5.7 Backlog", new Assignee(3, "Name3", "Surname3"), "14.02.2020", 11, 1);
    private Issue issue3 = new Issue(3, "name3", Status.OPEN, "author3", Label.QUESTION, Arrays.asList("project1", "project2"), null, new Assignee(2, "name2", "Surname2"), "16.01.2019", 15, 0);
    private Issue issue4 = new Issue(4, "name4", Status.CLOSED, "author4", Label.BUG, Collections.emptyList(), null, null, "09.04.2019", 3, 2);
    private Issue issue5 = new Issue(5, "name5", Status.OPEN, "author5", Label.QUESTION, Collections.singletonList("project2"), "5.7 M2", null, "06.05.2020", 0, 0);
    private Issue issue6 = new Issue(6, "name6", Status.CLOSED, "author6", Label.FEATURE_REQUEST, Collections.emptyList(), null, new Assignee(1, "Name1", "Surname1"), "11.04.2020", 70, 7);

    @BeforeEach()
    void setUp() {
        issueRepository.save(issue1);
        issueRepository.save(issue2);
        issueRepository.save(issue3);
        issueRepository.save(issue4);
        issueRepository.save(issue5);
        issueRepository.save(issue6);
    }

    @Test
    void shouldFindAll() {
        ArrayList<Issue> actual = issueRepository.findAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4, issue5, issue6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = issueRepository.findById(3);
        assertEquals(issue3, actual);
    }

    @Test
    void shouldOpenById() {
        issueRepository.openById(1);
        Issue byId = issueRepository.findById(1);
        assertSame(byId.getStatus(), Status.OPEN);
    }

    @Test
    void shouldCloseById() {
        issueRepository.closeById(2);
        Issue byId = issueRepository.findById(2);
        assertSame(byId.getStatus(), Status.CLOSED);
    }
}