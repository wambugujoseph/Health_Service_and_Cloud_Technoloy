package com.cloud.health.mainservice.util;

import java.security.SecureRandom;
import java.util.stream.Stream;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 12/23/2019
 * Time: 3:18 PM
 * Project: cloudHealthMainService
 */

public class Token {
    private static final SecureRandom rng = new SecureRandom(SecureRandom.getSeed(20));

    private boolean useThisCharacter(char c){
        return c>= '0' && c<= 'z' && Character.isLetterOrDigit(c);
    }

    public String generateRandomString(long length){
        Stream<Character> randomCharStream = rng.ints(Character.MIN_CODE_POINT,Character.MAX_CODE_POINT)
                .mapToObj(i->(char)i).filter(this::useThisCharacter).limit(length);
        return randomCharStream.collect(StringBuilder::new,StringBuilder::append,StringBuilder::append).toString();
    }
}
