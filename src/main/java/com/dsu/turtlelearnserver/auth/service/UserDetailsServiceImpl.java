package com.dsu.turtlelearnserver.auth.service;

import com.dsu.turtlelearnserver.auth.util.UserDetailsImpl;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다 : " + username));
    }
}
