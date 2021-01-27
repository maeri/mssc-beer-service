package guru.springframework.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author zawadma
 * @date 27/01/2021 08:45
 */
public class MvcExceptionHandler {

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {

		List<String> errorsList = new ArrayList<>(e.getConstraintViolations().size());
		e.getConstraintViolations().stream().map(Object::toString).forEach(errorsList::add);

		return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
	}

}
