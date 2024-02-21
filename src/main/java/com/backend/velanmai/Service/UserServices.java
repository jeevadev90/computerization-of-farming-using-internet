package com.backend.velanmai.Service;

import com.backend.velanmai.Common.ApiResponse;
import com.backend.velanmai.Common.BadRequestException;
import com.backend.velanmai.Common.Errors;
import com.backend.velanmai.DTO.ArticlesDto;
import com.backend.velanmai.DTO.ProfileDto;
import com.backend.velanmai.DTO.RegisterDto;
import com.backend.velanmai.DTO.UserArticleDto;
import com.backend.velanmai.Entity.*;
import com.backend.velanmai.Repository.*;
import com.backend.velanmai.Validater.UserArticleValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAtriclesRepository userAtriclesRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UserArticleValidater userArticleValidater;
    public ResponseEntity<ApiResponse> getUserDetails(String email) {

        User user = (User) userRepository.findByEmail(email);
        List<UserArticle>userArticleList=userAtriclesRepository.findByUser(user);
        ProfileDto profileDto=new ProfileDto();
        profileDto.setAcrticleCount(userArticleList.size());
        profileDto.setFirstName(user.getFirstName());
        profileDto.setLastName(user.getLastName());
        profileDto.setName(user.getName());
        profileDto.setEmail(user.getEmail());
        profileDto.setImage(user.getImage());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(profileDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }


    public ResponseEntity getCategories() {

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData( categoriesRepository.findAll());
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getParticularCategories(Long id) {

        Categories categories =categoriesRepository.findCategoriesById(id);
        if (categories==null)
        {
            throw new BadRequestException("bad", (List<Errors>) categories);
        }
        List<Articles> articlesList=articlesRepository.findByCategories(categories);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(articlesList);

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getArticles(Long id) {
        ApiResponse apiResponse =new ApiResponse();
        Optional<Articles> articles=articlesRepository.findById(id);
        apiResponse.setData(articles);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity addUserArticle(String article) {
        ApiResponse apiResponse=new ApiResponse();
        List<Errors> errors=userArticleValidater.validater(article);
        if (!errors.isEmpty())
        {
            throw new BadRequestException("empty",errors);
        }
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name= authentication.getName();
        
        User user1= (User) userRepository.findByEmail(name);
        UserArticle userArticle=new UserArticle();
        userArticle.setArticle(article);
        userArticle.setUser(user1);
        userArticle.setCreated_At(new Date(System.currentTimeMillis()));
        userAtriclesRepository.save(userArticle);
        apiResponse.setData(userArticle);

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getUsersArticles() {
        ApiResponse apiResponse=new ApiResponse();
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        User user = (User) userRepository.findByEmail(email);
        List<UserArticle> userArticleList=userAtriclesRepository.findByUser(user);
        apiResponse.setData(userArticleList);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getAlluserArticle(Pageable pageable) {
        ApiResponse apiResponse=new ApiResponse();
        Page<UserArticle>articlePage=userAtriclesRepository.findAll(pageable);
        apiResponse.setData(articlePage);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getParticularUserArticle(Long id) {
        ApiResponse apiResponse=new ApiResponse();
        Optional<UserArticle> userArticle=userAtriclesRepository.findById(id);
        List<Comments>comments=commentsRepository.findByUserArticleId(id);
        apiResponse.setData1(comments);
        apiResponse.setData(userArticle);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity updateImage(MultipartFile file) throws IOException {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByEmail(auth.getName());
        RegisterDto registerDto=new RegisterDto();
        registerDto.setImage(file.getBytes());
        user.setImage(registerDto.getImage());
        userRepository.save(user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData("success");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse.getData());
    }

    public ResponseEntity deleteArticle(long id) {

        userAtriclesRepository.deleteById(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData("deleted");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
