package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.CustomerDto;
import guru.springframework.msscbeerservice.web.service.customer.CustomerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
 * Created by jt on 2019-04-20.
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping({"/{customerId}"})
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@RequestBody CustomerDto customerDto) {
		CustomerDto savedDto = customerService.save(customerDto);
		HttpHeaders headers = new HttpHeaders();

		//TODO Add hostname (full URL)
		headers.add("Location", "api/v1/customer/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping({"/{customerId}"})
	public ResponseEntity<HttpStatus> handleUpdate(@PathVariable("customerId") UUID customerId,
			@RequestBody CustomerDto customerDto) {

		if (customerService.update(customerId, customerDto) != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@DeleteMapping({"/{customerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleDelete(@PathVariable("customerId") UUID customerId) {
		customerService.delete(customerId);
	}

}
