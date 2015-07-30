package com.tongji.kexin_ca.util;

import com.tongji.kexin_ca.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TokenManagerTest {

    @Test
    public void testCheckToken() throws Exception {


    }

    @Test
    public void testGenerateToken() throws Exception {
        String token = TokenManager.generateToken(new User(3, "d", "f", "", ""));
        assertTrue(TokenManager.checkToken(token) == 3);

    }
}