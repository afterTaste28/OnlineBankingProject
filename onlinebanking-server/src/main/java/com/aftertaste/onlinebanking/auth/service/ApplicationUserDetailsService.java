package com.aftertaste.onlinebanking.auth.service;

import com.aftertaste.onlinebanking.auth.entity.User;
import com.aftertaste.onlinebanking.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user present by email:"+username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmailId(),
                user.getPassword(),
                List.of()
        );
    }
}
