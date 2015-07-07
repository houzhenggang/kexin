package com.lq.util;

import com.lq.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenManager {
    private Map<String, User> tokenMap = new ConcurrentHashMap<String, User>();

    public User checkToken(String token) {
        return tokenMap.get(token);
    }

    public String addToken(User user) {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        tokenMap.put(token, user);
        return token;
    }
}
