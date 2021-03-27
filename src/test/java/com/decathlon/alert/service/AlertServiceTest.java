package com.decathlon.alert.service;

import com.decathlon.alert.bo.Developer;
import com.decathlon.alert.bo.Team;
import com.decathlon.alert.exception.AlertException;
import com.decathlon.alert.repository.AlertRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AlertServiceTest {
    @InjectMocks
    AlertService alertService;
    @Mock
    AlertRepository alertRepository;
    @Mock
    RestTemplate restTemplate;


    @Test
    public void doAlertTest() {
        when(alertRepository.findById(1)).thenReturn(Optional.of(createTeam()));
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("{\n" +
                "\"success\":\"alert sent\"\n" +
                "}", HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                Matchers.anyString(),
                Matchers.any(HttpMethod.class),
                Matchers.<HttpEntity<?>> any(),
                Matchers.<Class<String>> any()
                )
        ).thenReturn(responseEntity);
        String actual = alertService.doAlert(1);
        String expected="{status='{\n" +
                "\"success\":\"alert sent\"\n" +
                "}', alertedPhoneNumber='1234'}";
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void doAlertValidationTest() {
        when(alertRepository.findById(1)).thenReturn(Optional.of(new Team()));
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("{\n" +
                "\"success\":\"alert sent\"\n" +
                "}", HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                Matchers.anyString(),
                Matchers.any(HttpMethod.class),
                Matchers.<HttpEntity<?>> any(),
                Matchers.<Class<String>> any()
                )
        ).thenReturn(responseEntity);
        Exception exception = assertThrows(AlertException.class, () -> {
            alertService.doAlert(1);
        });
        Assert.assertEquals("Please create a team first with developers",exception.getMessage());

    }
    @Test
    public void doAlertNullValidationTest() {
        when(alertRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("{\n" +
                "\"success\":\"alert sent\"\n" +
                "}", HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                Matchers.anyString(),
                Matchers.any(HttpMethod.class),
                Matchers.<HttpEntity<?>> any(),
                Matchers.<Class<String>> any()
                )
        ).thenReturn(responseEntity);
        Exception exception = assertThrows(AlertException.class, () -> {
            alertService.doAlert(1);
        });
        Assert.assertEquals("Please create a team first",exception.getMessage());

    }
    private Team createTeam() {
        Team team1 = new Team(1,"test team");
        //Developer developer1 = new Developer(1, "Saikat", "5465", team1);
        Developer developer2 = new Developer(2, "Koushik", "1234", team1);
        team1.setDevelopers(Arrays.asList(developer2));
        return team1;
    }
}
