package com.backend.velanmai.Controller;

import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.Service.RegisterService;
import com.backend.velanmai.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@Controller

@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserServices userService;

    @GetMapping("/get")
    public String get()
    {
        return "redirect:/home";
    }


    @PostMapping("/userRegister")
    public ResponseEntity userRegister(@RequestBody RegisterDto registerDto)
    {
        return registerService.addUser(registerDto);
    }
    @PostMapping("/userLogin")
    public ResponseEntity userLogin(@RequestBody RegisterDto registerDto)
    {
        return registerService.userLogin(registerDto);
    }

}
