package com.project.webapi.core.data.dto;

import com.project.webapi.core.data.BaseDto;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    public String name;
    public String email;
    public String password;
    private String passwordSalt;
}
