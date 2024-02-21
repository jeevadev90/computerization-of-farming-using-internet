package com.backend.velanmai.DTO;

import com.backend.velanmai.Entity.UserArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String comment;
    private Long id;
}
