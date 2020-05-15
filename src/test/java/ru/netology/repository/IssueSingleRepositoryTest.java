package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;
import ru.netology.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssueSingleRepositoryTest {
    private IssueRepository issueRepository = new IssueRepository();
    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", Label.BUG, Arrays.asList("project1", "project2"), "5.7 M2", new Assignee(4, "Name4", "Surname4"), LocalDate.of(2020, 3, 1), 3, 4);

    @BeforeEach()
    void setup() {
        issueRepository.save(issue1);
    }

    @Test
    void shouldFindAll() {
        ArrayList<Issue> actual = issueRepository.findAll();
        List<Issue> expected = Collections.singletonList(issue1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = issueRepository.findById(1);

        assertEquals(issue1, actual);
    }

    @Test
    void shouldThrowException() {
        assertThrows(NotFoundException.class, () -> issueRepository.findById(2));
    }
}