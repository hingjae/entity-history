package com.example.entityhistory.security.dto;

import com.example.entityhistory.user.entity.User;
import com.example.entityhistory.user.entity.UserRole;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
public class CustomUserDetails implements UserDetails {
    private final String username;

    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public static UserDetails from(User user) {
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        user.getUserRoles().stream()
                                .map(UserRole::getRoleTypeName)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
