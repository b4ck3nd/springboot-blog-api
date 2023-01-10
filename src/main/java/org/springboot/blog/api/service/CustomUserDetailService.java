package org.springboot.blog.api.service;


import lombok.RequiredArgsConstructor;
import org.springboot.blog.api.model.User;
import org.springboot.blog.api.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        if (userRepository.existsByEmail(value) || userRepository.existsByUsername(value)) {
            User user = userRepository.findByUsernameOrEmail(value, value);
            Set<GrantedAuthority> authorities=user.getRoles().stream().map( role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
        }
        else {
            throw new UsernameNotFoundException("username not found with this username or email: " + value);
        }

    }
}
