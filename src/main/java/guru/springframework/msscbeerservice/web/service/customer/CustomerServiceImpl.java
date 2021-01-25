package guru.springframework.msscbeerservice.web.service.customer;

import guru.springframework.msscbeerservice.web.model.CustomerDto;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zawadma
 * @date 23/01/2021 10:44
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID id) {
		return CustomerDto.builder().id(UUID.randomUUID()).name("customer1").build();
	}

	@Override
	public CustomerDto save(CustomerDto customerDto) {
		return customerDto;
	}

	@Override
	public CustomerDto update(UUID customerId, CustomerDto customerDto) {
		return customerDto.accept(customerDto);
	}

	@Override
	public void delete(UUID customerDto) {
		log.info("Deleting a customer...");
	}
}
