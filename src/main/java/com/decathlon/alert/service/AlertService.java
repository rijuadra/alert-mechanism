package com.decathlon.alert.service;

import com.decathlon.alert.bo.Developer;
import com.decathlon.alert.bo.Team;
import com.decathlon.alert.exception.AlertException;
import com.decathlon.alert.repository.AlertRepository;
import com.decathlon.alert.request.AlertRequest;
import com.decathlon.alert.request.AlertResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AlertService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AlertRepository alertRepository;

    private final String alertUrl = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f/4/";

    public String doAlert(int teamId) {
        AlertResponse alertResponse = new AlertResponse();
        Optional<Team> team = alertRepository.findById(teamId);
        AlertRequest alertRequest = new AlertRequest();
        if (team.isPresent()) {
            Team team1 = team.get();
            List<Developer> developerList = team1.getDevelopers();
            if(developerList==null||developerList.size()==0)
                throw new AlertException("Please create a team first with developers");
            Random random = new Random();
            Developer developer = developerList.get(random.nextInt(developerList.size()));
            alertRequest.setPhoneNumer(developer.getPhoneNumber());
            HttpEntity<AlertRequest> httpEntity = new HttpEntity<>(alertRequest);
            ResponseEntity<String> exchange = restTemplate.exchange(alertUrl, HttpMethod.POST, httpEntity, String.class);
            alertResponse.setStatus(exchange.getBody());
            alertResponse.setAlertedPhoneNumber(alertRequest.getPhoneNumer());

        }else {
            throw new AlertException("Please create a team first");
        }
        return alertResponse.toString();
    }
}
