package org.springboot.blog.api.service.auth;

import org.springboot.blog.api.dto.auth.LoginDto;
import org.springboot.blog.api.dto.auth.RegisterDto;

public interface AuthService {
    String login(LoginDto dto);
    String register(RegisterDto dto);
    

}
