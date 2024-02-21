package com.backend.velanmai.Validater;

import com.backend.velanmai.Common.Errors;
import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LoginValidater {
    @Autowired
    private UserRepository userRepository;
    public List<Errors> validateLogin(RegisterDto registerDto) {
        List<Errors> errors=new ArrayList<>();
        User noEmail= (User) userRepository.findByEmail(registerDto.getEmail());


        String encodedpws= noEmail.getPassword();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();


        if (!encoder.matches(registerDto.getPassword(), encodedpws))
        {
            errors.add(new Errors("email and password","email or password is incorrect"));
        }

        if (Objects.equals(registerDto.getEmail(),""))
        {
            errors.add(new Errors("email","is empty"));
        }
        if (Objects.equals(registerDto.getPassword(),""))
        {
            errors.add(new Errors("Password","is empty"));
        }

        return errors;


    }
}
