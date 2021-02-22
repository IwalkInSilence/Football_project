package project.cinema.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.cinema.model.User;
import project.cinema.service.UserService;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).get();
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            String[] userRoles = user.getRoles()
                    .stream()
                    .map(r -> r.getRole().toString())
                    .toArray(String[]::new);
            builder.roles(userRoles);
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
