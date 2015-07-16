package com.lq.kexin.util;

import com.lq.kexin.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component

//由spring保证单例
public class TokenManager {

    private static final int TOKEN_LIVE_HOURS = 3;
    private Map<String, Token> tokenMap = new ConcurrentHashMap<String, Token>();

    public TokenManager() {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                for (Map.Entry<String, Token> entry : tokenMap.entrySet()) {
                    if (entry.getValue().isExpire()) {
                        tokenMap.remove(entry.getKey());
                    }
                }
            }
        }, 600000, 600000);//每10分清除过期的token
    }


    public int checkToken(String s) {
        Token token = tokenMap.get(s);
        if (token == null) {
            return -1; //无此token
        }
        if (token.isExpire()) {//token过期
            tokenMap.remove(s);
            return -1;
        }
        token.update();//更新token过期时间
        return token.userId;
    }

    public String generateToken(User user) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        tokenMap.put(s, new Token(user.getUserId()));
        return s;
    }

    class Token {
        public int userId;
        public LocalDateTime expiredTime;

        public Token(int userId) {
            this.userId = userId;
            this.expiredTime = LocalDateTime.now().plusHours(TOKEN_LIVE_HOURS);
        }

        public void update() {
            expiredTime = LocalDateTime.now().plusHours(TOKEN_LIVE_HOURS);
        }

        public boolean isExpire() {
            return expiredTime.isBefore(LocalDateTime.now());
        }
    }
}
