package org.springboot.blog.api.service.auth;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springboot.blog.api.config.jwt.JwtTokenProvider;
import org.springboot.blog.api.dto.auth.LoginDto;
import org.springboot.blog.api.dto.auth.RegisterDto;
import org.springboot.blog.api.exception.UnHandledException;
import org.springboot.blog.api.model.Role;
import org.springboot.blog.api.model.User;
import org.springboot.blog.api.repository.RoleRepository;
import org.springboot.blog.api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper mapper;


    @Override
    public String login(LoginDto dto) {
        Authentication  authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(),dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto dto) {
        //check for username
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UnHandledException("username already exists");
        }
        //chek for email
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UnHandledException("email already exists");
        }
        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "registration successfully completed";

    }
}
