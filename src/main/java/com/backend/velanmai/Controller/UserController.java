package com.backend.velanmai.Controller;

import com.backend.velanmai.DTO.ArticlesDto;
import com.backend.velanmai.DTO.CategoriesDto;
import com.backend.velanmai.DTO.UserArticleDto;
import com.backend.velanmai.Entity.Categories;
import com.backend.velanmai.Repository.ArticlesRepository;
import com.backend.velanmai.Repository.CategoriesRepository;
import com.backend.velanmai.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@CrossOrigin(origins = "https://letsfarming.netlify.app")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("/getuser")
    public String get()
    {
        return "it is users auth";
    }

    @GetMapping("/userProfile")
    public ResponseEntity userProfile()
    {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        return userService.getUserDetails(email);
    }

    @GetMapping("/getCategories")
    public ResponseEntity getCategories()
    {

        return userService.getCategories();
    }

    @GetMapping("/particularCategories/{id}")
    public ResponseEntity getParticularCategories(@PathVariable Long id)
    {
        return userService.getParticularCategories(id);
    }

    @GetMapping("getArticle/{id}")
    public ResponseEntity getArticles(@PathVariable Long id)
    {
        return userService.getArticles(id);
    }

    @PostMapping("/addUserArticle")
    public ResponseEntity addUserArticle(@RequestBody UserArticleDto userArticleDto)
    {
        String article=userArticleDto.getArticle();
        return userService.addUserArticle(article);
    }

    @GetMapping("/getUserArticles")
    public ResponseEntity getUserArticles()
    {
        return userService.getUsersArticles();
    }

    @GetMapping("/getAlluserarticle")
    public ResponseEntity getAlluserArticle(Pageable pageable)
    {
        return userService.getAlluserArticle(pageable);
    }

    @GetMapping("/getParticularUserArticle/{id}")
    public ResponseEntity getParticularUserArticle(@PathVariable Long id)
    {
        return userService.getParticularUserArticle(id);
    }

    @PutMapping("/upadateimage")
    public ResponseEntity updateimage(@RequestParam("image")MultipartFile file) throws IOException {
        return userService.updateImage(file);

    }
    @DeleteMapping("/deleteArticle/{id}")
    public ResponseEntity deleteArticle(@PathVariable Long id)
    {
        return userService.deleteArticle(id);
    }

}
