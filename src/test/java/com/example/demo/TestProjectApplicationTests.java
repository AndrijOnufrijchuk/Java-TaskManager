package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

@SpringBootTest
class TestProjectApplicationTests {

    @Test
    public void testConfigureGlobal() throws Exception {
        String auth = "dXNlcjpwYXNzd29yZA==";
        System.out.println(new String(Base64.getDecoder().decode(auth)));

    }
}
