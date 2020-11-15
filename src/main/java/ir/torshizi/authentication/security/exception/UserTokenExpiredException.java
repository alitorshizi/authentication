package ir.torshizi.authentication.security.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="User_Token_Expired")
public class UserTokenExpiredException extends RuntimeException {
    public UserTokenExpiredException(String message) {
        super(message);
    }
}
