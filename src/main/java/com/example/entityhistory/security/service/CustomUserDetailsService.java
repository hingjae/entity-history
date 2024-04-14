package com.example.entityhistory.security.service;

import com.example.entityhistory.security.dto.CustomUserDetails;
import com.example.entityhistory.user.entity.User;
import com.example.entityhistory.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserWithRolesByUsername(username)
                .map(CustomUserDetails::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
