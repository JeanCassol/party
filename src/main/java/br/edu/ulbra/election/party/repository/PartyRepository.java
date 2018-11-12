package br.edu.ulbra.election.party.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.election.party.model.Party;

public interface PartyRepository extends CrudRepository<Party, Long> {
	List<Party> findByCode(String code);
}
