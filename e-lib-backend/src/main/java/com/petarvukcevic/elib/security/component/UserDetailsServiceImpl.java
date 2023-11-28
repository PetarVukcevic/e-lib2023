package com.petarvukcevic.elib.security.component;

import com.petarvukcevic.elib.entities.User;
import com.petarvukcevic.elib.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userOptional = userService.findByUsernameWithRoles(username);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();

            List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                    return grantedAuthority;
                }).toList();

            return new org.springframework.security.core.userdetails.User(
              user.getUsername(), user.getPassword(), authorities
            );

            // authentication -> username, authorities (token)
        }
        else {
            throw new UsernameNotFoundException("User with username " + username + " not found!");
        }
    }

    // authenticationManager.authenticate(auth); -> new UsernameAndPasswordAuthenticationFilter(login.getUsername())
}