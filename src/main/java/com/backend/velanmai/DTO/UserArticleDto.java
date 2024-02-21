package com.backend.velanmai.DTO;

import com.backend.velanmai.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserArticleDto {

    private String article;
    private User user;
    private Date created_At;
}
