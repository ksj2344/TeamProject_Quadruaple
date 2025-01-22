package com.green.project_quadruaple.common.config.security;

import com.green.project_quadruaple.common.config.jwt.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacade {
    public static long getSignedUserId(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSignedUserId();
    }
}

