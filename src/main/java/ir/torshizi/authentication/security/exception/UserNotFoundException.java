package ir.torshizi.authentication.security.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User_Not_found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { super(message); }
}
