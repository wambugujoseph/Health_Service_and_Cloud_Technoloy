package com.cloud.health.authorizationservice.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Stream;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Friday
 * Date: 12/27/2019
 * Time: 11:40 AM
 * Project: CloudHealthAuthorizationService
 */
@Component
public class AutoGenToken {

    private static final SecureRandom rng = new SecureRandom(SecureRandom.getSeed(100));

    private boolean useThisCharacter(char c){
        return c>= '0' && c<= 'z' && Character.isLetterOrDigit(c);
    }

    public String generateRandomString(long length){
        Stream<Character> randomCharStream = rng.ints(Character.MIN_CODE_POINT,Character.MAX_CODE_POINT)
                .mapToObj(i->(char)i).filter(this::useThisCharacter).limit(length);
        return randomCharStream.collect(StringBuilder::new,StringBuilder::append,StringBuilder::append).toString();
    }
}
