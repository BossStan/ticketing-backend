package com.systems.ticketing;

import com.systems.ticketing.registration.EmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

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
		String emailAddress = "user@kenac.edu";
 		String regexPattern = "(\\S.*\\S)(@)(kenac)(.\\S[a-z]{2})(.\\S[a-z]{2})";
		assertTrue(patternMatches(emailAddress, regexPattern));

	}
}
