package com.green.project_quadruaple.config.security;

import com.green.project_quadruaple.config.jwt.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacade {
    public static long getSignedUserId(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSignedUserId();
    }
}

