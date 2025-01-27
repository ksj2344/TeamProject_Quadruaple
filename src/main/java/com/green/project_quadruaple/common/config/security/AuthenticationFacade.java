package com.green.project_quadruaple.common.config.security;

import com.green.project_quadruaple.common.config.jwt.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public JwtUser getSignedUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
<<<<<<< HEAD
                .getAuthentication()
                .getPrincipal();
        return myUserDetails.getJwtUser();
    }

    public long getSignedUserId() {
        return getSignedUser().getSignedUserId();
    }
}
=======
                                                                           .getAuthentication()
                                                                           .getPrincipal();
        return myUserDetails.getJwtUser();
    }

    public long getSignedUserId() {
        return getSignedUser().getSignedUserId();
    }
}
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
