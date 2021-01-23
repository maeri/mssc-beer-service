package guru.springframework.msscbeerservice.web.model;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @author zawadma
 * @date 24/01/2021 00:05
 */
public class BeerPagedList  extends PageImpl<BeerDto>{

	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public BeerPagedList(List<BeerDto> content) {
		super(content);
	}
}
