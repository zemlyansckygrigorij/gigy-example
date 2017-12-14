package com.gigy.controller;

import java.security.Principal;
import java.util.Collection;

import com.gigy.model.Level;
import com.gigy.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.*;

import com.gigy.model.Party;
import com.gigy.model.Person;
import com.gigy.repository.PersonRepository;



@Configuration
@EnableAuthorizationServer
@RestController
@RequestMapping("/user")
public class UserContoller{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonRepository personRepo;

    @RequestMapping(  method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(Principal principal) {

        Person person = personRepo.findByUsername(principal.getName());

        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(/*null,*/ HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePerson(Principal principal) {
        Person person = personRepo.findByUsername(principal.getName());

        if ( principal != null ) {
            personRepo.delete(person);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/parties", method = RequestMethod.GET)
    public ResponseEntity<Collection<Party>> getPersonParties(Principal principal) {
        Person person = personRepo.findByUsername(principal.getName());

        if (person != null) {
            return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public ResponseEntity<Collection<Skill>> getSkills(Principal principal) {
        Person person = personRepo.findByUsername(principal.getName());

        if (person != null) {
            return new ResponseEntity<>(person.getSkills(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSkill(@RequestBody Skill skill,Principal principal) {

        Person person = personRepo.findByUsername(principal.getName());
        person.getSkills().add(skill);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

}

/*
*    {
        "id": 4,
        "name": "Singing",
        "level": "GOOD"
    }*/



/* http://localhost:8090/gigy/people?access_token=c86fac6f-dc0a-4832-9fb8-e1f35c916b27



    http://localhost:8090/gigy/oauth/token?grant_type=password&username=peter@example.com&password=password
*   http://localhost:8090/gigy/oauth/token?grant_type=password&username=peter@example.com&password=password
*   http://localhost:8090/gigy/oauth/token?grant_type=password&username=john@example.com&password=password
*   http://localhost:8090/gigy/oauth/token?grant_type=password&username=katie@example.com&password=password
*   http://localhost:8090/gigy/oauth/token?grant_type=password&username=tom@example.com&password=password
*
*
*   http://localhost:8090/gigy/user?access_token=7fd6aa4f-07c7-42f1-9105-881f5948646c
*
*
*   http://localhost:8090/gigy/user/Juggling/GODLIKE?access_token=f5240a64-c47e-4d90-8018-954e2f49d5a7
*   */



/*{
    "access_token": "c86fac6f-dc0a-4832-9fb8-e1f35c916b27",
    "token_type": "bearer",
    "refresh_token": "a909296c-9319-48e2-9fa4-f5161b5210a3",
    "expires_in": 3577,
    "scope": "read write"
}{
    "access_token": "c86fac6f-dc0a-4832-9fb8-e1f35c916b27",
    "token_type": "bearer",
    "refresh_token": "a909296c-9319-48e2-9fa4-f5161b5210a3",
    "expires_in": 3577,
    "scope": "read write"
}

    29d290f2-b1d6-417a-a243-46a8bef0ca1b
*/
/*
*
 * @RequestMapping(value = "/{skill}/{level}",method = RequestMethod.POST)
    public ResponseEntity<?> addSkill(@PathVariable String skill ,String level,Principal principal) {
        Person person = personRepo.findByUsername(principal.getName());

        Level level_;

        switch(level){
            case("GOOD"):{
                level_ = Level.GOOD;
            }
            case("AWESOME"):{
                level_ = Level.AWESOME;
            }
            case("GODLIKE"):{
                level_ = Level.GODLIKE;
            }
            default:{
                level_ = Level.GOOD;
            }
        }
        Skill skill_ = new Skill();
        skill_.setName(skill);
        skill_.setLevel(level_);

        person.getSkills().add(skill_);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
*
*
* */