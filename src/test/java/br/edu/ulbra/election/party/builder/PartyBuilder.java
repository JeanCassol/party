package br.edu.ulbra.election.party.builder;

import java.util.Collections;
import java.util.List;

import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import br.edu.ulbra.election.party.output.v1.PartyOutput;

public class PartyBuilder {
	public static PartyOutput getPartyOutput() {
		PartyOutput partyOutput = new PartyOutput();
		partyOutput.setId(1L);
		partyOutput.setCode("99");
		partyOutput.setName("Party Name");
		partyOutput.setNumber(99);
		return partyOutput;
	}

	public static List<PartyOutput> getElectionOutputList() {
		return Collections.singletonList(getPartyOutput());
	}

	public static PartyInput getPartyInput() {
		PartyOutput partyOutput = getPartyOutput();
		PartyInput partyInput = new PartyInput();
		partyInput.setCode(partyOutput.getCode());
		partyInput.setName(partyOutput.getName());
		partyInput.setNumber(partyOutput.getNumber());
		return partyInput;
	}

	public static Party getParty() {
		Party party = new Party();
		party.setId(1L);
		party.setCode("99");
		party.setName("Party name");
		party.setNumber(99);
		return party;
	}

	public static List<Party> getPartyList() {
		return Collections.singletonList(getParty());
	}
}
