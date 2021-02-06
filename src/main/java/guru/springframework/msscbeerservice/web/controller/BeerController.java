package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zawadma
 * @date 24/01/2021 09:27
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/beer")
public class BeerController {

	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDto> getById(@PathVariable("beerId") UUID beerId) {
		//TODO impl
		return new ResponseEntity<>(
				BeerDto.builder().beerName("ALE").beerStyle(BeerStyleEnum.ALE).build(),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handleCreate(@RequestBody @Validated BeerDto beerDto) {
		//TODO impl
		log.info("Request for save : {}", beerDto);
		HttpHeaders headers = new HttpHeaders();

		//TODO Add hostname (full URL)
		headers.add("Location", "api/v1/beer/" + UUID.randomUUID().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping({"/{beerId}"})
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("beerId") UUID beerId,
			@RequestBody BeerDto beerDto) {
		log.info("Request for update : {} , {}", beerId, beerDto);
		if (beerDto != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("beerId") UUID beerId) {
		//TODO impl
		log.info("Request for delete : {}", beerId);
	}

}
