package com.shopreviews.dto;

import lombok.Data;

/**
 * @author zyh
 */
@Data
public class LoginFormDTO {
    private String phone;
    private String code;
    private String password;
}
