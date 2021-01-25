package guru.springframework.msscbeerservice.repositories;

import guru.springframework.msscbeerservice.domain.Beer;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zawadma
 * @date 25/01/2021 08:28
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
