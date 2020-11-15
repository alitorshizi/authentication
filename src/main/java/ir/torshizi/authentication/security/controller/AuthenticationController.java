package ir.torshizi.authentication.security.controller;

import ir.torshizi.authentication.security.exception.UserIsNotActiveException;
import ir.torshizi.authentication.security.exception.UserNotFoundException;
import ir.torshizi.authentication.security.model.dto.AuthenticationRequestDto;
import ir.torshizi.authentication.security.model.dto.AuthenticationResponseDto;
import ir.torshizi.authentication.security.model.dto.UserDto;
import ir.torshizi.authentication.security.repository.UserRepository;
import ir.torshizi.authentication.security.service.JwtUtilService;
import ir.torshizi.authentication.security.service.MyUserDetailsService;
import ir.torshizi.authentication.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final JwtUtilService jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> createLoginToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        UserDto userDto = userService.findByUsername(authenticationRequestDto.getUsername());
        if (userDto == null)
            throw new UserNotFoundException("کاربر یافت نشد");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(),
                            authenticationRequestDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("نام کاربری یا رمز نامعتبر است.", e);
        }

        if (!userDto.isEnabled()) {
            throw new UserIsNotActiveException("کابر فعال نیست لطفا رمز اولیه را تغییر کنید.");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
