package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		return null;
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handleCreate(@RequestBody BeerDto beerDto) {
		//TODO impl
		return null;
	}

	@PutMapping
	public ResponseEntity<HttpHeaders> handleUpdate(@RequestBody BeerDto beerDto) {
		//TODO impl
		return null;
	}

	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("beerId") UUID beerId) {
		//TODO impl
	}

}
