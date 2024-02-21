package com.backend.velanmai.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private int acrticleCount;
    private byte[] image;

}
