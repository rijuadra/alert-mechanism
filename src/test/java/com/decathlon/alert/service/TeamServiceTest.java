package com.decathlon.alert.service;

import com.decathlon.alert.bo.Team;
import com.decathlon.alert.exception.AlertException;
import com.decathlon.alert.repository.AlertRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TeamServiceTest {
    @Mock
    AlertRepository alertRepository;
    @InjectMocks
    TeamService teamService;

    @Test
    public void createTeamValidationTest() {
        Exception exception = assertThrows(AlertException.class, () -> {
            teamService.createTeam(null);
        });
        Assert.assertEquals("Please provide valid team details",exception.getMessage());

    }
    @Test
    public void createTeamNameValidationTest() {
        Exception exception = assertThrows(AlertException.class, () -> {
            teamService.createTeam(new Team());
        });
        Assert.assertEquals("Please provide team name",exception.getMessage());

    }
    @Test
    public void createTeamWithoutDeveloperValidationTest() {
        Exception exception = assertThrows(AlertException.class, () -> {
            teamService.createTeam(new Team(1,"test"));
        });
        Assert.assertEquals("Team Can't be formed without developers",exception.getMessage());

    }
}
