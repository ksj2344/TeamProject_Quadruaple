package com.green.project_quadruaple.common.config.security;

import com.green.project_quadruaple.common.config.jwt.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public JwtUser getSignedUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return myUserDetails.getJwtUser();
    }

    public long getSignedUserId() {
        return getSignedUser().getSignedUserId();
    }
}