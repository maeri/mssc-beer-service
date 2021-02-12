package guru.springframework.msscbeerservice.web.controller;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;

import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.util.StringUtils;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;


/**
 * @author zawadma
 * @date 24/01/2021 09:54
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getById() throws Exception {
		//given
		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
				.param("iscold", "yes")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("v1/beer-get",
						pathParameters(
								parameterWithName("beerId").description("UUID of desired beer to get.")
						),
						requestParameters(
								parameterWithName("iscold").description("Is Cold query parameter")
						),
						responseFields(
								fieldWithPath("id").description("Id of Beer"),
								fieldWithPath("version").description("Version of Beer"),
								fieldWithPath("createdDate").description("Creation Date of Beer object"),
								fieldWithPath("lastModifiedDate").description("Last Modified Date of Beer object"),
								fieldWithPath("beerName").description("Name of Beer object"),
								fieldWithPath("beerStyle").description("Enum of Beer style"),
								fieldWithPath("upc").description("UPC of Beer"),
								fieldWithPath("price").description("Price of Beer single unit"),
								fieldWithPath("quantityOnHand").description("Quantity on hand")
						)
				));
	}

	@Test
	void handleCreate() throws Exception {
		//given
		BeerDto beerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.version(123123)
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.beerName("ALE")
				.beerStyle(BeerStyleEnum.ALE)
				.upc(111011L)
				.price(new BigDecimal("10.12"))
				.quantityOnHand(110)
				.build();

		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

		//when
		ResultActions perform = mockMvc.perform(
				post("/api/v1/beer/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson)
		);

		//then
		perform.andExpect(status().isCreated())
				.andDo(document("v1/beer-new",
						requestFields(
								fields.withPath("id").description("UUID"),
								fields.withPath("version").description("version"),
								fields.withPath("createdDate").description("Created date"),
								fields.withPath("lastModifiedDate").description("Last modified date"),
								fields.withPath("beerName").description("Name of the beer"),
								fields.withPath("beerStyle").description("Beer style"),
								fields.withPath("upc").description("UPC code"),
								fields.withPath("price").description("Price per unit"),
								fields.withPath("quantityOnHand").description("Quantity at stock")
						)));
	}

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions
							.descriptionsForProperty(path), ". ")));
		}
	}

	@Test
	void handleUpdate() throws Exception {
		//given
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		//when
		ResultActions perform = mockMvc.perform(
				put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson));

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
				delete("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON));

		//then
		perform.andExpect(status().isNoContent());
	}

}
