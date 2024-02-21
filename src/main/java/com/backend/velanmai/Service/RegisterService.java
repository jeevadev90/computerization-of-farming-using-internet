package com.backend.velanmai.Service;

import com.backend.velanmai.Common.ApiResponse;
import com.backend.velanmai.Common.BadRequestException;
import com.backend.velanmai.Common.Errors;
import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.Entity.Role;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.UserRepository;
import com.backend.velanmai.Validater.LoginValidater;
import com.backend.velanmai.Validater.RegisterValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginValidater loginValidater;
    @Autowired
    private RegisterValidater registerValidater;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<ApiResponse> addUser(RegisterDto registerDto) {
        ApiResponse apiResponse=new ApiResponse();

        List<Errors> errors=registerValidater.validateRegister(registerDto);
        if (!errors.isEmpty())
        {
            throw new BadRequestException("bad request",errors);
        }

        User user =new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);

        String token=jwtService.tokenGenerater(registerDto);

        userRepository.save(user);
        apiResponse.setData(token);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity<ApiResponse> userLogin(RegisterDto registerDto) {

        List<Errors>errors=loginValidater.validateLogin(registerDto);
        if (!errors.isEmpty())
        {
            throw new BadRequestException("bad request",errors);
        }
        ApiResponse apiResponse=new ApiResponse();
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerDto.getEmail(),registerDto.getPassword()));
        if (authentication.isAuthenticated())
        {
            apiResponse.setData(jwtService.tokenGenerater(registerDto));
        }
        else
        {
            throw new UsernameNotFoundException("invalid user details");
        }
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
