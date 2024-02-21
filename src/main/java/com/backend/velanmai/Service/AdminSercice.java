package com.backend.velanmai.Service;

import com.backend.velanmai.Common.ApiResponse;
import com.backend.velanmai.DTO.ArticlesDto;
import com.backend.velanmai.DTO.CategoriesDto;
import com.backend.velanmai.Entity.Articles;
import com.backend.velanmai.Entity.Categories;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.ArticlesRepository;
import com.backend.velanmai.Repository.CategoriesRepository;
import com.backend.velanmai.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class AdminSercice {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UserRepository userRepository;
    public String addCategories(CategoriesDto categoriesDto, Model model) {

        Categories categories=new Categories();
        categories.setName(categoriesDto.getName());
        categories.setImages(categoriesDto.getImage());
        ApiResponse apiResponse=new ApiResponse();

        categoriesRepository.save(categories);
        model.addAttribute("message",categories.getName());

        return "success";
    }


    public String addAtricles(ArticlesDto articlesDto,Model model) {
        System.out.println(categoriesRepository.findByName(articlesDto.getName()));
        Articles articles=new Articles();

        Categories categories=categoriesRepository.findByName(articlesDto.getName());
        articles.setCategories(categories);
        articles.setTitle(articlesDto.getTitle());
        articles.setImage(articlesDto.getImage());
        articles.setDescription(articlesDto.getDescription());
        articles.setContent(articlesDto.getContent());
        articlesRepository.save(articles);
        System.out.println(articles);
        model.addAttribute("message1",articlesDto.getTitle());
        return "success";

    }

    public ResponseEntity getAdmin() {
        ApiResponse apiResponse=new ApiResponse();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) userRepository.findByEmail(auth.getName());
        apiResponse.setData(user);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse.getData());
    }
}
