package com.backend.velanmai.Validater;

import com.backend.velanmai.Common.Errors;
import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RegisterValidater {
    @Autowired
    private UserRepository userRepository;
    public List<Errors> validateRegister(RegisterDto registerDto) {

        User Existuser=userRepository.findByName(registerDto.getName());
        User Existemail= (User) userRepository.findByEmail(registerDto.getEmail());

        List<Errors> errors=new ArrayList<>();


        if (Existuser!=null)
        {
            errors.add(new Errors("user:","UserName Already exist"));
        }

        if (Existemail!=null)
        {
            errors.add(new Errors("email:","Email Already exist"));
        }
        if (Objects.equals(registerDto.getFirstName(),""))
        {
            errors.add(new Errors("FirstName","is Empty value"));
        }
        if (Objects.equals(registerDto.getLastName(),""))
        {
            errors.add(new Errors("LastName","is Empty value"));
        }
        if (Objects.equals(registerDto.getName(),""))
        {
            errors.add(new Errors("UserNmae","is Empty value"));
        }
        if (Objects.equals(registerDto.getEmail(),""))
        {
            errors.add(new Errors("Email","is Empty value"));
        }
        if (Objects.equals(registerDto.getPassword(),""))
        {
            errors.add(new Errors("Password","is Empty value"));
        }
        if (registerDto.getPassword().length()<8)
        {
            errors.add(new Errors("Password","Password minimum length 8"));
        }

        return errors;
    }
}
