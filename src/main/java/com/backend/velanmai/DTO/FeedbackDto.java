package com.backend.velanmai.DTO;

import com.backend.velanmai.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private String content;
    private String email;

}
