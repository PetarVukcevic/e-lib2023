package com.petarvukcevic.elib.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpDTO {
    private String username;
    private Integer otpCode;
    private boolean isRememberMe;
}
