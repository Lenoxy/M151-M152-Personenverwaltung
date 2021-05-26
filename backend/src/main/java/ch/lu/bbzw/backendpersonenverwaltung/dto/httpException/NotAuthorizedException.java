package ch.lu.bbzw.backendpersonenverwaltung.dto.httpException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Not authorized")
public class NotAuthorizedException extends Exception{
}
