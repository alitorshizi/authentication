package ir.torshizi.authentication.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "user_is_not_active")
public class UserIsNotActiveException extends RuntimeException {
    public UserIsNotActiveException(String message) {
        super(message);
    }
}
