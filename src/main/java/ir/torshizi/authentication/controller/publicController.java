package ir.torshizi.authentication.controller;

import ir.torshizi.authentication.security.model.dto.RoleDto;
import ir.torshizi.authentication.security.model.dto.UserDto;
import ir.torshizi.authentication.security.service.RoleService;
import ir.torshizi.authentication.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/api")
@AllArgsConstructor
public class publicController {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello Public World.";
    }

    @GetMapping("/add")
    public String addRolesAndUsers() {
        addRoles();
        addUsers();
        return "Roles {ADMIN, WRITER, USER} Added. \n Users {admin, writer, user} by password 123456 Added.";
    }

    public void addRoles() {
        RoleDto adminRole = new RoleDto();
        RoleDto writerRole = new RoleDto();
        RoleDto userRole = new RoleDto();
        adminRole.setRole("ADMIN");
        writerRole.setRole("WRITER");
        userRole.setRole("USER");
        roleService.save(adminRole);
        roleService.save(writerRole);
        roleService.save(userRole);
    }

    public void addUsers(){
        RoleDto adminRole = roleService.findByRole("ADMIN");
        RoleDto writerRole = roleService.findByRole("WRITER");
        RoleDto userRole = roleService.findByRole("USER");
        List<RoleDto> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        UserDto adminUser = new UserDto(null,"admin", "ali", "torshizi", "torshizi.a@gmail.com", "091", "123", "123456", "123456",true, adminRoles);
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        userService.save(adminUser);

        List<RoleDto> writerRoles = new ArrayList<>();
        writerRoles.add(writerRole);
        writerRoles.add(userRole);
        UserDto writerUser = new UserDto(null, "writer", "ali", "torshizi", "torshizi.a@gmail.com", "092", "123", "123456","123456", true, writerRoles);
        writerUser.setPassword(passwordEncoder.encode(writerUser.getPassword()));
        userService.save(writerUser);

        List<RoleDto> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        UserDto user = new UserDto(null, "user", "ali", "torshizi", "torshizi.a@gmail.com", "093", "123", "123456","123456", true, userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
