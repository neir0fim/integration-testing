package com.kuzin.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = { "server.port=8079" })
class FullContextSecondExampleTest {

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
