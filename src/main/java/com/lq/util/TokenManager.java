package com.lq.util;

import com.lq.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenManager {
    private Map<String, Integer> tokenMap = new ConcurrentHashMap<String, Integer>();

    public Integer checkToken(String token) {
        return tokenMap.get(token);
    }

    public String generateToken(User user) {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        tokenMap.put(token, user.getUserId());
        return token;
    }
}
