package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author zawadma
 * @date 23/01/2021 23:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	@NotNull
	private UUID id;

	@NotNull
	private Integer version;

	@NotNull
	private OffsetDateTime createdDate;

	@NotNull
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyle;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	@NotNull
	private BigDecimal price;

	private Integer quantityOnHand;

}
