package com.aftertaste.onlinebanking.auth.service;

import com.aftertaste.onlinebanking.account.service.AccountService;
import com.aftertaste.onlinebanking.auth.dto.LoginRequest;
import com.aftertaste.onlinebanking.auth.dto.RegisterRequest;
import com.aftertaste.onlinebanking.auth.dto.UserInfoDTO;
import com.aftertaste.onlinebanking.auth.entity.Role;
import com.aftertaste.onlinebanking.auth.entity.User;
import com.aftertaste.onlinebanking.auth.repository.UserRepository;
import com.aftertaste.onlinebanking.common.exception.ApiException;
import com.aftertaste.onlinebanking.common.exception.ErrorCode;
import com.aftertaste.onlinebanking.config.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository,AccountService accountService,PasswordEncoder passwordEncoder,JwtService jwtService, ApplicationUserDetailsService applicationUserDetailsService){
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }
    @Override
    public void registerUser(RegisterRequest registerRequestDTO){
        User user = dtoToUserBean(registerRequestDTO);
        try{
            userRepository.save(user);
            logger.info("User {} registered.", user.getEmailId());

            accountService.createAccount(user);
            logger.info("Created account for user: {}", user.getEmailId());
        } catch(DataIntegrityViolationException e){
            throw new ApiException(ErrorCode.EMAIL_ALREADY_EXISTS,"Email-ID already exists:"+user.getEmailId());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new ApiException(ErrorCode.SYSTEM_EXCEPTION,"Failed while registering User!");
        }
    }

    @Override
    public String loginUser(LoginRequest loginRequestDTO) {
        User user = userRepository.findByEmailId(loginRequestDTO.getEmailId())
                .orElseThrow(() -> new UsernameNotFoundException("No user present by email:"+loginRequestDTO.getEmailId()));
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(user.getEmailId());

        return jwtService.generateToken(userDetails);
    }

    private User dtoToUserBean(RegisterRequest registerRequestDTO){
        String clientHashedPassword = registerRequestDTO.getPassword();
        String passwordToStore = passwordEncoder.encode(clientHashedPassword); // BCrypt

        User user = new User(registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(),
                registerRequestDTO.getEmailId(),
                passwordToStore
                );
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public UserInfoDTO getUserInfo(String emailId){
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new UsernameNotFoundException("No user present by email:"+emailId));
        return new UserInfoDTO(user);
    }

}
