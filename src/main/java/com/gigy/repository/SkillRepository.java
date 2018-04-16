package com.gigy.repository;

import com.gigy.model.Person;
import com.gigy.model.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SkillRepository  extends CrudRepository<Skill, Long>{

    Collection<Skill> findAll();
    Skill findByName(String skillName);

}
