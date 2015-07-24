package com.lq.kexin.util;

import com.lq.kexin_ca.entity.User;
import com.lq.kexin_ca.util.TokenManager;
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