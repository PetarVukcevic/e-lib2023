package com.petarvukcevic.elib.security.otp;

import com.petarvukcevic.elib.dto.EmailDTO;
import com.petarvukcevic.elib.services.EmailService;
import com.petarvukcevic.elib.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpGenerator otpGenerator;
    private final EmailService emailService;
    private final UserService userService;

    public void generateOtpAndSendEmail(String username)
    {
        Integer otpCode = otpGenerator.generateOTP(username);
        String emailAddress = userService.findEmailByUsername(username);

        EmailDTO emailDTO = new EmailDTO(
                emailAddress, "OTP Code value: " + otpCode);
    }
}
