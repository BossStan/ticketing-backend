package com.systems.ticketing;

import com.systems.ticketing.registration.EmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern)
				.matcher(emailAddress)
				.matches();
	}
	@Test
	public void testUsingStrictRegex() {
		String emailAddress = "user#kenac.edu.zw";
// 		String regexPattern = "(\\S.*\\S)(@)(kenac)(.\\S[a-z]{2})(.\\S[a-z]{2})";
		String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
		assertTrue(patternMatches(emailAddress, regexPattern));

	}
}
