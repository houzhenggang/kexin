package com.lq.kexin.util;

import com.lq.kexin.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenManager {

    private static final int TOKEN_LIVE_HOURS = 3;
    private Map<String, Token> tokenMap = new ConcurrentHashMap<String, Token>();

    public int checkToken(String s) {
        Token token = tokenMap.get(s);
        if (token == null) {
            return -1; //无此token
        }
        if (token.expiredTime.isBefore(LocalDateTime.now())) {//token过期
            tokenMap.remove(s);
            return -1;
        }
        token.expiredTime = LocalDateTime.now().plusHours(TOKEN_LIVE_HOURS);//更新token过期时间

        return token.userId;
    }

    public String generateToken(User user) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        tokenMap.put(s, new Token(user.getUserId(), LocalDateTime.now().plusHours(TOKEN_LIVE_HOURS)));
        return s;
    }

    class Token {
        public int userId;
        public LocalDateTime expiredTime;

        public Token(int userId, LocalDateTime expiredTime) {
            this.userId = userId;
            this.expiredTime = expiredTime;
        }
    }
}
