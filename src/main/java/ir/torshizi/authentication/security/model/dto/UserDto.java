package ir.torshizi.authentication.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String nationalCode;
    private String password;
    private String confirmPassword;
    private boolean enabled;
    private List<RoleDto> roles;

}
