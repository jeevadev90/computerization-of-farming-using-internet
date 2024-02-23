package com.backend.velanmai.Controller;

import com.backend.velanmai.Common.BadRequestException;
import com.backend.velanmai.Common.Errors;
import com.backend.velanmai.DTO.ArticlesDto;
import com.backend.velanmai.DTO.CategoriesDto;
import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.Entity.Categories;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.ArticlesRepository;
import com.backend.velanmai.Repository.CategoriesRepository;
import com.backend.velanmai.Repository.UserRepository;
import com.backend.velanmai.Service.AdminSercice;
import com.backend.velanmai.Service.JwtService;
import com.backend.velanmai.Validater.LoginValidater;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://letsfarming.netlify.app")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginValidater loginValidater;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdminSercice adminSercice;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticlesRepository articlesRepository;

    @PostMapping("/addCategories")
    public String addCategories(@RequestParam("image") MultipartFile file, @RequestParam("name") String name ,Model model) throws IOException {
        CategoriesDto categoriesDto=new CategoriesDto();
        categoriesDto.setImage(file.getBytes());
        categoriesDto.setName(name);

        return adminSercice.addCategories(categoriesDto,model);
    }
    @PostMapping("/addArticles")
    public String AddArticles(@RequestParam("images") MultipartFile imge ,@RequestParam String title,@RequestParam String description,
                              @RequestParam String content,@RequestParam String name,Model model) throws IOException {

        ArticlesDto articlesDto=new ArticlesDto();
        articlesDto.setTitle(title);
       articlesDto.setImage(imge.getBytes());
        articlesDto.setDescription(description);
        articlesDto.setContent(content);
        articlesDto.setName(name);
        return adminSercice.addAtricles(articlesDto,model);
    }

//    @GetMapping("/login")
//    public String login(Model m)
//    {
//
//
//
//        return "login";
//    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home")
    public ResponseEntity getAdmin()
    {
        return adminSercice.getAdmin();
    }
}
