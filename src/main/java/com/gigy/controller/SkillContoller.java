package com.gigy.controller;


import com.gigy.model.Party;
import com.gigy.model.Person;
import com.gigy.model.Skill;
import com.gigy.repository.PersonRepository;
import com.gigy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableAuthorizationServer
@RestController
@RequestMapping("/skill")
public class SkillContoller{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    PersonRepository persons ;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Skill>> getSkill() {
        return new ResponseEntity<>(skillRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Skill> getSkill(@PathVariable long id) {
        Skill skill = skillRepo.findOne(id);

        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(/*null,*/ HttpStatus.NOT_FOUND);

        }
    }
    @RequestMapping(value = "/{skillName}", method = RequestMethod.GET)
    public ResponseEntity<Skill> getSkill(@PathVariable String skillName) {
        Skill skill = skillRepo.findByName(skillName);

        if (skill != null) {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(/*null,*/ HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSkill(@RequestBody Skill skill) {
        return new ResponseEntity<>(skillRepo.save(skill), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSkill(@PathVariable long id) {
        Skill skill = skillRepo.findOne(id);

        if (skill != null) {
            skillRepo.delete(skill);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "/{skillName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSkill(@PathVariable String skillName) {
        Skill skill = skillRepo.findByName(skillName);

        if (skill != null) {
            skillRepo.delete(skill);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{id}/people", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>>  getPerson(@PathVariable long id) {
        Skill skill = skillRepo.findOne(id);

        Collection<Person> firstList = persons.findAll();
        Set<Person> finishSet = firstList.stream().filter((s) -> s.getSkills().contains(skill)).collect(Collectors.toSet());

        return new ResponseEntity<>(finishSet, HttpStatus.OK);
    }

    @RequestMapping(value = "/{skillName}/people", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>>  getPerson(@PathVariable String skillName) {
        Skill skill = skillRepo.findByName(skillName);

        Collection<Person> firstList = persons.findAll();
        Set<Person> finishSet = firstList.stream().filter((s) -> s.getSkills().contains(skill)).collect(Collectors.toSet());

        return new ResponseEntity<>(finishSet, HttpStatus.OK);
    }

}
