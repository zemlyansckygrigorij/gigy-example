package com.gigy.repository;

import java.util.Collection;

import com.gigy.model.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {

	Collection<Party> findAll();

}
