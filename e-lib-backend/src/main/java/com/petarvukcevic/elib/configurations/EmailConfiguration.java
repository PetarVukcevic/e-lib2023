package com.petarvukcevic.elib.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public JavaMailSender javaMailSender(ProviderConfiguration providerConfiguration)
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(providerConfiguration.getHost());
        mailSender.setPort(providerConfiguration.getPort());
        mailSender.setUsername(providerConfiguration.getUsername());
        mailSender.setPassword(providerConfiguration.getPassword());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transfer.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        return mailSender;
    }
}
