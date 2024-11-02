package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AppTest {

    @Test
    void contextLoads() {
        // This test will simply verify that the application context loads successfully
    }

    @Test
    void testApp() {
        // A basic test case to verify true assertion
        assertThat(true).isTrue();
    }
}

