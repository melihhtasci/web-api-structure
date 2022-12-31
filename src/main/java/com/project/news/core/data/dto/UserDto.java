package com.project.news.core.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    public String name;
    public String email;
    public String password;
    private String passwordSalt;
}
