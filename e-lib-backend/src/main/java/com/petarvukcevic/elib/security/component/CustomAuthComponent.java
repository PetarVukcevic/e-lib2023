package com.petarvukcevic.elib.security.component;

import org.springframework.stereotype.Component;

@Component("customAuth")
public class CustomAuthComponent {


    public boolean hasPermission()
    {
        return false;
    }
}
