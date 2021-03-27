package com.decathlon.alert.service;

import com.decathlon.alert.bo.Developer;
import com.decathlon.alert.bo.Team;
import com.decathlon.alert.exception.AlertException;
import com.decathlon.alert.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    AlertRepository alertRepository;

    public String createTeam(Team team){
        if (team == null) {
            throw new AlertException("Please provide valid team details");
        } else {
            if (team.getTeamName() == null||team.getTeamName().trim().equals(""))
                throw new AlertException("Please provide team name");
            List<Developer> developers = team.getDevelopers();
            if (developers==null||developers.size() == 0) {
                throw new AlertException("Team Can't be formed without developers");
            } else {
                for (Developer d : developers) {
                    d.setTeam(team);
                }
            }
        }
        Team savedTeam = alertRepository.save(team);
        return "Your Team id is: "+savedTeam.getTeamId();
    }

}
