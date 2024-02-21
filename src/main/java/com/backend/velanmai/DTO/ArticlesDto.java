package com.backend.velanmai.DTO;

import com.backend.velanmai.Entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesDto {
    private String title;
    private byte[] image;
    private String description;
    private String content;
    private String name;
    private Categories categories;
}
