package pl.agh.login.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.agh.login.application.entity.AppUser;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userService.getUser(username);

        Set<SimpleGrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toSet());

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}