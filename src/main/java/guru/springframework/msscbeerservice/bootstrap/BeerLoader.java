package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zawadma
 * @date 25/01/2021 08:43
 */
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

	public static final Long BEER_1_UPC = 631234200036L;
	public static final Long BEER_2_UPC = 631234300019L;
	public static final Long BEER_3_UPC = 683783375213L;

	private final BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		loadBeer();
	}

	private void loadBeer() {
		if (beerRepository.count() == 0) {
			beerRepository.save(
					Beer.builder().beerName("Mango Bobs")
							.beerStyle("IPA")
							.quantityToBrew(700)
							.minOnHand(12)
							.upc(BEER_1_UPC)
							.price(new BigDecimal("12.92"))
							.build());

			beerRepository.save(
					Beer.builder().beerName("Galaxy Cat")
							.beerStyle("IPA")
							.quantityToBrew(700)
							.minOnHand(12)
							.upc(BEER_2_UPC)
							.price(new BigDecimal("12.92"))
							.build());

			beerRepository.save(
					Beer.builder()
							.beerName("Pinball Porter")
							.minOnHand(12)
							.quantityToBrew(200)
							.price(new BigDecimal("12.95"))
							.upc(BEER_3_UPC)
							.build());
		}
	}
}
