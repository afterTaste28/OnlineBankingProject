package com.aftertaste.onlinebanking.auth.service;

import com.aftertaste.onlinebanking.auth.dto.RegisterRequest;
import com.aftertaste.onlinebanking.auth.entity.Role;
import com.aftertaste.onlinebanking.auth.entity.User;
import com.aftertaste.onlinebanking.auth.repository.UserRepository;
import com.aftertaste.onlinebanking.common.exception.ApiException;
import com.aftertaste.onlinebanking.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void registerUser(RegisterRequest registerRequestDTO){
        User user = dtoToUserBean(registerRequestDTO);
        try{
            userRepository.save(user);
            log.info("User "+user.getEmailId()+" registered.");
        } catch(DataIntegrityViolationException e){
            throw new ApiException(ErrorCode.EMAIL_ALREADY_EXISTS,"Email-ID already exists:"+user.getEmailId());
        } catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException(ErrorCode.SYSTEM_EXCEPTION,"Failed while registering User!");
        }
    }

    private User dtoToUserBean(RegisterRequest registerRequestDTO){
        User user = new User(registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(),
                registerRequestDTO.getEmailId(),
                registerRequestDTO.getPassword());
        user.setRole(Role.USER);
        return user;
    }
}
