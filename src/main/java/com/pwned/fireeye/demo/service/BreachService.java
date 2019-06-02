package com.pwned.fireeye.demo.service;

import com.pwned.fireeye.demo.beans.Breach;
import com.pwned.fireeye.demo.beans.BreachRequest;
import com.pwned.fireeye.demo.beans.BreachResponse;
import com.pwned.fireeye.demo.utils.HttpRequestBuilder;
import com.pwned.fireeye.demo.utils.HttpResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BreachService implements IBreachService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${fireeye.pwned.ssp.app.name}")
    private String appName;

    @Value("${fireeye.pwned.ssp.base.url}")
    private String baseUrl;

    private static final Logger log = LogManager.getLogger(BreachService.class);


    public BreachResponse getAllBreaches(@NotNull BreachRequest breachRequest) {
        log.info("Getting breaches from Pwned for request : {}", breachRequest);

        HttpHeaders httpHeaders = HttpRequestBuilder.buildHeaders(appName);
        Map<String, Object> uriParams = HttpRequestBuilder.buildUriVariables(breachRequest);
        String url;
        if (StringUtils.isEmpty(breachRequest.getAccountID())) {
            url = HttpRequestBuilder.buildUrl(baseUrl, "breaches",
                    null, uriParams);

        } else {
            url = HttpRequestBuilder.buildUrl(baseUrl, "breachedaccount",
                    breachRequest.getAccountID(), uriParams);
        }

        log.info("Calling Pwned Service with url {} and params {}", url, uriParams);
        BreachResponse breachResponse = new BreachResponse();
        try {
            ResponseEntity<List<Breach>> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity(httpHeaders), new ParameterizedTypeReference<List<Breach>>() {
                    }, uriParams);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                breachResponse.setBreaches(response.getBody());
                breachResponse.setComments("Pwned Service successfully responded with the following breaches");
            } else {
                throw new HttpClientErrorException(response.getStatusCode(), response.getStatusCode().getReasonPhrase(),
                        response.getHeaders(), null, Charset.defaultCharset());
            }
        } catch (HttpClientErrorException ex) {
            log.info("Pwned Service responded with error status {}", ex.getStatusCode());
            breachResponse.setComments(HttpResponseUtils.convertExceptionToResponseString(ex));
        }

        return breachResponse;
    }

    public BreachResponse getBreach(@NotNull BreachRequest breachRequest) {
        log.info("Getting breach info from Pwned for {}", breachRequest.getBreachName());
        String url = HttpRequestBuilder.buildUrl(baseUrl, "breach",
                breachRequest.getBreachName(), null);
        HttpHeaders httpHeaders = HttpRequestBuilder.buildHeaders(appName);

        log.info("Calling Pwned Service with url {}", url);
        BreachResponse breachResponse = new BreachResponse();
        try {
            ResponseEntity<Breach> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity(httpHeaders), new ParameterizedTypeReference<Breach>() {
                    });

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                breachResponse.setBreaches(Arrays.asList(new Breach[]{response.getBody()}));
                breachResponse.setComments("Pwned Service successfully responded with the following breaches");
            } else {
                throw new HttpClientErrorException(response.getStatusCode(), response.getStatusCode().getReasonPhrase(),
                        response.getHeaders(), null, Charset.defaultCharset());
            }
        } catch (HttpClientErrorException ex) {
            log.info("Pwned Service responded with error status {}", ex.getStatusCode());
            breachResponse.setComments(HttpResponseUtils.convertExceptionToResponseString(ex));
        }

        return breachResponse;
    }

    public List<String> getBreachDataClasses(){

        log.info("Getting breach data classes");
        String url = HttpRequestBuilder.buildUrl(baseUrl, "dataclasses", null, null);
        HttpHeaders httpHeaders = HttpRequestBuilder.buildHeaders(appName);

        ResponseEntity<List<String>> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity(httpHeaders), new ParameterizedTypeReference<List<String>>() {
                });
        return response.getBody();
    }

}
