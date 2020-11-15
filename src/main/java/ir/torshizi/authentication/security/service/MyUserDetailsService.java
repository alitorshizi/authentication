package ir.torshizi.authentication.security.service;

import ir.torshizi.authentication.security.model.entity.RoleEntity;
import ir.torshizi.authentication.security.model.entity.UserEntity;
import ir.torshizi.authentication.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("user " + userName + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
        String[] userRoles = user.getRoles().stream().map(RoleEntity::getRole).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

}
