package com.petarvukcevic.elib.security.otp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class OtpGenerator {

    private static final Integer EXPIRE_IN_MINUTES = 5; // TTL time to live
    private final LoadingCache<String, Integer> otpCache;

    public OtpGenerator() {
        this.otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_IN_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }

    public Integer generateOTP(String key)
    {
        Random random = new Random();
        Integer otpCode = 100000 + random.nextInt(900000);

        otpCache.put(key, otpCode);

        return otpCode;
    }

    public Integer getOtpCodeByKey(String key)
    {
        return otpCache.getIfPresent(key); // null if key doesn't exist
    }
}
