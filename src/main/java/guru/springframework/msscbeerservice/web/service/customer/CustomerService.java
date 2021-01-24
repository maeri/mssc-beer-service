package guru.springframework.msscbeerservice.web.service.customer;

import guru.springframework.msscbeerservice.web.model.CustomerDto;
import java.util.UUID;

/**
 * @author zawadma
 * @date 23/01/2021 10:43
 */
public interface CustomerService {

	CustomerDto getCustomerById(UUID id);

	CustomerDto save(CustomerDto customerDto);

	CustomerDto update(UUID customerId, CustomerDto customerDto);

	void delete(UUID customerId);
}
