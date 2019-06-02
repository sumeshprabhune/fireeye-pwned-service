package com.pwned.fireeye.demo.utils;

import com.pwned.fireeye.demo.beans.BreachRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private HttpRequestBuilder() {

    }

    public static Map<String, Object> buildUriVariables(BreachRequest breachRequest) {
        Map<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(breachRequest.getDomain())) {
            requestParams.put("domain", breachRequest.getDomain());
        }
        if (breachRequest.isIncludeUnverified()) {
            requestParams.put("includeUnverified", breachRequest.isIncludeUnverified());
        }
        if (breachRequest.isTruncateResponse()) {
            requestParams.put("truncateResponse", breachRequest.isTruncateResponse());
        }
        return requestParams;
    }

    public static String buildUrl(String baseUrl, String service, String parameter, Map<String, Object> requestParams) {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("/").append(service);
        if (!StringUtils.isEmpty(parameter)) {
            sb.append("/").append(parameter);
        }

        if (requestParams != null && !requestParams.isEmpty()) {
            boolean first = true;
            for (String key : requestParams.keySet()) {
                if (first) {
                    sb.append("?");
                    first = false;
                }
                sb.append(key).append("=").append("{").append(key).append("}");
                sb.append("&");
            }

            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static HttpHeaders buildHeaders(String appName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", appName);
        return headers;
    }
}
