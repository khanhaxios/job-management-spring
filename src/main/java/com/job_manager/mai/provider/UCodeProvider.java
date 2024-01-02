package com.job_manager.mai.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class UCodeProvider {

    public static final int STANDARD_LENGTH = 12;
    final char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'l', 'z', 'q', 'w', 't', 'y', 'u', 'i', 'o', 'p',
            'v', 'n', 'x', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '0', '9', '8', '7', '6', '5', '4', '3', '2', '1'};

    public int getCharsLength() {
        return this.chars.length;
    }

    public String generateUCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[getRandomNumber(0, getCharsLength())]);
        }
        return stringBuilder.toString();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
