package com.pwned.fireeye.demo.service;

import com.pwned.fireeye.demo.utils.HttpResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PasswordService implements IPasswordService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${fireeye.pwned.ssp.app.name}")
    private String appName;

    @Value("${fireeye.pwned.password.ssp.base.url}")
    private String baseUrl;

    private static final Logger log = LogManager.getLogger(PasswordService.class);

    public String getPwnedPasswords(String shaCharacters) {
        log.info("Getting matching pwned passwords starting with : {}", shaCharacters);
        String url = baseUrl + "/" + shaCharacters;
        log.info("Calling Pwned Service with url {}", url);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.info("Pwned Service responded with error status {}", ex.getStatusCode());
            return ex.getResponseBodyAsString();
        }
    }
}
