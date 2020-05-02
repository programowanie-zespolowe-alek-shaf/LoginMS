package pl.agh.login.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.agh.login.application.entity.AppUser;
import pl.agh.login.application.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AppUser getUser(String username) {

        AppUser user = userRepository.getUser(username);

        if (user.getUsername() == null) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found", username));
        }
        return user;
    }
}
