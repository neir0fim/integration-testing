package com.kuzin.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class FullContextFirstExampleTest {

	@Value("${server.port}")
	private String applicationPort;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Test
	void contextLoads() {
		assertEquals("/web-api", contextPath);
		assertEquals("8079", applicationPort);
	}
}
