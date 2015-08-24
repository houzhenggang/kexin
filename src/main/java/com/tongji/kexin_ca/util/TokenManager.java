package com.tongji.kexin_ca.util;

import com.tongji.kexin_ca.entity.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {

    private TokenManager() {
    }

    private static final Map<String, Token> tokenMap = new ConcurrentHashMap<String, Token>();

    static {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("目前所有的token：");
                System.out.println(tokenMap);
                for (Map.Entry<String, Token> entry : tokenMap.entrySet()) {
                    if (entry.getValue().isExpire()) {
                        tokenMap.remove(entry.getKey());
                    }
                }
            }
        }, 600000, 600000);//每10分清除过期的token
    }

    /**
     * @param s token
     * @return userid,-1表示无此token或已过期
     */

    public static int checkToken(String s) {
        Token token = tokenMap.get(s);
        if (token == null) {
            return -1; //无此token
        }
        if (token.isExpire()) {//token过期
            tokenMap.remove(s);
            return -1;
        }
        token.update();//更新token过期时间
        return token.getUserId();
    }

    public static String generateToken(User user) {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        tokenMap.put(s, new Token(user.getUserId()));
        return s;
    }
}

class Token {
    private static final int TOKEN_LIVE_HOURS = 3;

    private final int userId;
    private LocalDateTime expiredTime;

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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId=" + userId +
                ", expiredTime=" + expiredTime +
                '}';
    }
}