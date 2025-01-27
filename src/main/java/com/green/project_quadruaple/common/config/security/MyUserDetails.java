package com.green.project_quadruaple.common.config.security;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.project_quadruaple.common.config.jwt.JwtUser;
import com.green.project_quadruaple.common.config.jwt.UserRole;
=======
import com.green.project_quadruaple.common.config.jwt.JwtUser;
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class MyUserDetails implements UserDetails {

    private JwtUser jwtUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(jwtUser.getRoles().size());
<<<<<<< HEAD
        for (UserRole role : jwtUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
=======
        for (String role : jwtUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
<<<<<<< HEAD

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
=======
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
}
