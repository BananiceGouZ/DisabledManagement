package cn.bananice.basic.dto;

import lombok.Data;

@Data
public class EmailRegisterDto {

    private String email;
    private String password;
    private String passwordRepeat;

}