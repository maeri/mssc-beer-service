package guru.springframework.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * @author zawadma
 * @date 24/01/2021 09:54
 */
@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getById() throws Exception {
		//given
		mockMvc.perform(
				get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void handleCreate() throws Exception {
		//given
		BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("name").build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		//when
		ResultActions perform = mockMvc.perform(
				post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson));

		//then
		perform.andExpect(status().isCreated());
	}

	@Test
	void handleUpdate() throws Exception {
		//given
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		//when
		ResultActions perform = mockMvc.perform(
				put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson));

		//then
		perform.andExpect(status().isNoContent());
	}

	@Test
	void handleDelete() throws Exception {
		//given
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		//when
		ResultActions perform = mockMvc.perform(
				delete("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON));

		//then
		perform.andExpect(status().isNoContent());
	}

}
