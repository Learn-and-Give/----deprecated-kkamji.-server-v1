package com.kkamjidot.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ApiServerV1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
