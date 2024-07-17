package ru.otus.testing.exercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"java:S2699", "java:S125"})
class Solution1_MessageBuilderImplTest {

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
        // throw new RuntimeException("Ooops in setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    void buildMessageTest() {
        System.out.println("buildMessageTest");
        // throw new RuntimeException("Ooops in buildMessageTest");
    }
}
