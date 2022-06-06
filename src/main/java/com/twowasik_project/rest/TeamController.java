package com.twowasik_project.rest;

import com.twowasik_project.dto.CreateTeamDto;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.Team;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import com.twowasik_project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/teams/")
public class TeamController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("create")
    public ResponseEntity createTeam(HttpServletRequest request, @RequestBody CreateTeamDto CreateTeamDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        User admin = userRepository.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());
        String members = admin.getEmail() + " " + CreateTeamDto.getTeam_participants();

        teamService.saveTeam(new Team(CreateTeamDto.getName(), members, new ArrayList<>(), admin));
        return ResponseEntity.ok(true);
    }
}