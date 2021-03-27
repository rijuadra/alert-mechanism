package com.decathlon.alert.controller;

import com.decathlon.alert.bo.Team;
import com.decathlon.alert.service.AlertService;
import com.decathlon.alert.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {
    @Autowired
    AlertService alertService;
    @Autowired
    TeamService teamService;

    @PostMapping("/team")
    public String formTeam(@RequestBody Team team){
        return teamService.createTeam(team);

    }


    @PostMapping("/{team_id}/alert")
    public String alertTeam(@PathVariable("team_id") int teamId) {
        return alertService.doAlert(teamId);
    }

}
