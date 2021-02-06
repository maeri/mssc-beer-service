package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * @author zawadma
 * @date 06/02/2021 07:42
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	BeerDto BeerToBeerDto(Beer beer);
	Beer BeerDtoToBeer(BeerDto beerDto);
}
