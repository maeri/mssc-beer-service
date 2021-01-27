package guru.springframework.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.CustomerDto;
import guru.springframework.msscbeerservice.web.service.customer.CustomerService;
import guru.springframework.msscbeerservice.web.service.customer.CustomerServiceImpl;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * @author zawadma
 * @date 24/01/2021 09:54
 */
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}

	@Autowired
	private CustomerService customerService;

	@Test
	void getCustomer() throws Exception {
		//given
		mockMvc.perform(
				get("/api/v1/customer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void handleCreate() throws Exception {
		//given
		CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).build();
		String CustomerDtoJson = objectMapper.writeValueAsString(customerDto);

		//when
		ResultActions perform = mockMvc.perform(
				post("/api/v1/customer/").contentType(MediaType.APPLICATION_JSON).content(CustomerDtoJson));

		//then
		perform.andExpect(status().isCreated());
	}

	@Test
	void handleUpdate() throws Exception {
		//given
		CustomerDto customerDto = CustomerDto.builder().build();
		String CustomerDtoJson = objectMapper.writeValueAsString(customerDto);

		//when
		ResultActions perform = mockMvc.perform(
				put("/api/v1/customer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
						.content(CustomerDtoJson));

		//then
		perform.andExpect(status().isNoContent());
	}

	@Test
	void handleDelete() throws Exception {
		//given
		CustomerDto customerDto = CustomerDto.builder().build();
		String CustomerDtoJson = objectMapper.writeValueAsString(customerDto);

		//when
		ResultActions perform = mockMvc.perform(
				delete("/api/v1/customer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON));

		//then
		perform.andExpect(status().isNoContent());
	}

}
