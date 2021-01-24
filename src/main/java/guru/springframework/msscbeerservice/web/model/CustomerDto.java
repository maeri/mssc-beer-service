package guru.springframework.msscbeerservice.web.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private UUID id;
    private String name;

	public CustomerDto accept (CustomerDto customerDto) {
		return CustomerDto.builder().id(customerDto.getId())
				.name(customerDto.getName())
				.build();
	}
}
