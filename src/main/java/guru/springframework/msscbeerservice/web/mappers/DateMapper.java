package guru.springframework.msscbeerservice.web.mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

/**
 * @author zawadma
 * @date 06/02/2021 07:44
 */
@Component
public class DateMapper {

	public OffsetDateTime asOffsetDateTime(Timestamp ts) {
		if (ts != null) {
			LocalDateTime localDateTime = ts.toLocalDateTime();
			return OffsetDateTime
					.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
							localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(),
							localDateTime.getNano(), ZoneOffset.UTC);
		} else {
			return null;
		}
	}

	public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
		if (offsetDateTime != null) {
			return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
		} else {
			return null;
		}
	}
}
