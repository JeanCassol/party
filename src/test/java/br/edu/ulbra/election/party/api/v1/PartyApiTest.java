package br.edu.ulbra.election.party.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.edu.ulbra.election.party.TestConfig;
import br.edu.ulbra.election.party.builder.PartyBuilder;
import br.edu.ulbra.election.party.output.v1.GenericOutput;
import br.edu.ulbra.election.party.service.PartyService;

@RunWith(SpringRunner.class)
@WebMvcTest(PartyApi.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class PartyApiTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PartyService partyService;

	private final Gson gson = new Gson();
	private String URL_BASE = "/v1/party/";

	@Test
	public void getAll() throws Exception {
		given(partyService.getAll()).willReturn(PartyBuilder.getElectionOutputList());

		mockMvc.perform(get(URL_BASE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", equalTo(1)))
				.andExpect(jsonPath("$[0].name", equalTo("Party Name")))
				.andExpect(jsonPath("$[0].number", equalTo(99)));
	}

	@Test
	public void getOne() throws Exception {
		given(partyService.getById(anyLong())).willReturn(PartyBuilder.getPartyOutput());
		mockMvc.perform(get(URL_BASE + "1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.name", equalTo("Party Name")))
				.andExpect(jsonPath("$.number", equalTo(99)));
	}

	@Test
	public void create() throws Exception {
		given(partyService.create(any())).willReturn(PartyBuilder.getPartyOutput());

		mockMvc.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(PartyBuilder.getPartyInput()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.name", equalTo("Party Name")))
				.andExpect(jsonPath("$.number", equalTo(99)));
	}

	@Test
	public void update() throws Exception {
		given(partyService.update(anyLong(), any())).willReturn(PartyBuilder.getPartyOutput());

		mockMvc.perform(put(URL_BASE + "1").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(PartyBuilder.getPartyInput()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.name", equalTo("Party Name")))
				.andExpect(jsonPath("$.number", equalTo(99)));
	}

	@Test
	public void deleteVoter() throws Exception {
		given(partyService.delete(anyLong())).willReturn(new GenericOutput("OK"));

		mockMvc.perform(delete(URL_BASE + "1").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(PartyBuilder.getPartyInput())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", equalTo("OK")));
	}
}
