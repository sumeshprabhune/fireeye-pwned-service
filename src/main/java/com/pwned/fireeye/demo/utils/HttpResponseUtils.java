package com.pwned.fireeye.demo.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

public class HttpResponseUtils {
    private HttpResponseUtils() {

    }

    public static String convertExceptionToResponseString(HttpClientErrorException ex) {
        String comments;
        if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            comments = "No results found for the request";
        } else if (ex.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)) {
            if(!StringUtils.isEmpty(ex.getResponseBodyAsString())) {
                comments = ex.getResponseBodyAsString();
            } else {
                comments = "Too many requests done. Please wait for " + ex.getResponseHeaders().get("Retry-After") + " seconds before making next request";
            }
        } else {
            if(!StringUtils.isEmpty(ex.getResponseBodyAsString())) {
                comments = ex.getResponseBodyAsString();
            } else {
                comments = "Internal Error in processing request!";
            }
        }
        return comments;
    }
}
