package com.systems.ticketing.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate email
        return s.matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2})(.\\S[a-z]{2})");
    }
}
