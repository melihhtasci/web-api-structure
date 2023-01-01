package com.project.webapi.core.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public String name;
    public String email;
    public String password;
    private String passwordSalt;
}
