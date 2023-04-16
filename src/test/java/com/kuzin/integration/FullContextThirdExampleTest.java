package com.kuzin.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-property-source.properties")
class FullContextThirdExampleTest {

	@Value("${server.port}")
	private String applicationPort;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Test
	void contextLoads() {
		assertEquals("/web-api", contextPath);
		assertEquals("8067", applicationPort);
	}
}
