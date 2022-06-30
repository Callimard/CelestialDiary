package org.bandrsoftwares.celestialdiary.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class HttpUtils {

    private HttpUtils() {
    }

    public static String basicAuthorization(String username, String password) {
        String credentials = Base64.encodeBase64String((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        return "Basic " + credentials;
    }

    public static String bearerAuthorization(String token) {
        return "Bearer " + token;
    }
}
