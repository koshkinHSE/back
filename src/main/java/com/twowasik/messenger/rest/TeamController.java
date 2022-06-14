package com.twowasik.messenger.rest;

import com.twowasik.messenger.dto.AddPersonDto;
import com.twowasik.messenger.dto.CreateTeamDto;
import com.twowasik.messenger.dto.ShowDto;
import com.twowasik.messenger.dto.IdDto;
import com.twowasik.messenger.exceptions.InvalidTokenExceptions;
import com.twowasik.messenger.jwt.JWTProvider;
import com.twowasik.messenger.model.Team;
import com.twowasik.messenger.model.User;
import com.twowasik.messenger.service.TeamService;
import com.twowasik.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/teams/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private IdDto idDto;

    @Autowired
    private ShowDto showDto;

    @Autowired
    private User admin;

    @PostMapping("create")
    public ResponseEntity createTeam(HttpServletRequest request, @RequestBody CreateTeamDto createTeamDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        admin = userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());
        String participantsId = userService.getUsersId(createTeamDto.getTeam_participants(), Integer.toString(admin.getId()));
        System.out.println(participantsId);
        if (participantsId.equals("")) {
            System.out.println(participantsId);
            return ResponseEntity.notFound().build();
        }

        idDto.setId(teamService.saveTeam(new Team(createTeamDto.getName(), participantsId, Integer.toString(admin.getId()), createTeamDto.getAvatar())));

        userService.addTeam(Integer.toString(idDto.getId()), participantsId);

        return ResponseEntity.ok(idDto);
    }

    @GetMapping("showTeams")
    public ResponseEntity showTeams(HttpServletRequest request) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        showDto.setId(userService.getTeams((int) jwtProvider.getAccessClaims(request.getHeader("Authorization")).get("id")));
        showDto.setName(teamService.showTeams(showDto.getId()));

        return ResponseEntity.ok(showDto);
    }

    @PatchMapping("addPerson")
    public ResponseEntity addPerson(HttpServletRequest request, @RequestBody AddPersonDto addPersonDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }



        admin = userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());
        String participantsId = userService.getUsersId(addPersonDto.getParticipants(), admin.getId());

        if (participantsId.equals("")) {
            System.out.println(participantsId);
            return ResponseEntity.notFound().build();
        }

        System.out.println(participantsId);
        userService.addTeam(Integer.toString(addPersonDto.getTeam_id()), participantsId);
        teamService.addPerson(addPersonDto.getTeam_id(), participantsId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("getTeam")
    public ResponseEntity getTeam(HttpServletRequest request, @RequestBody IdDto idDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        return ResponseEntity.ok(teamService.getTeam(idDto.getId()));
    }
}