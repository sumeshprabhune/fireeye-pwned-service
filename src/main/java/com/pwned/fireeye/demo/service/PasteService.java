package com.pwned.fireeye.demo.service;

import com.pwned.fireeye.demo.beans.PasteEntity;
import com.pwned.fireeye.demo.beans.PasteEntityResponse;
import com.pwned.fireeye.demo.utils.HttpRequestBuilder;
import com.pwned.fireeye.demo.utils.HttpResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@Service
public class PasteService implements IPasteService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${fireeye.pwned.ssp.app.name}")
    private String appName;

    @Value("${fireeye.pwned.ssp.base.url}")
    private String baseUrl;

    private static final Logger log = LogManager.getLogger(BreachService.class);

    public PasteEntityResponse getPasteAccounts(String accountId) {
        log.info("Getting paste entities from Pwned for : {}", accountId);

        HttpHeaders httpHeaders = HttpRequestBuilder.buildHeaders(appName);
        String url = HttpRequestBuilder.buildUrl(baseUrl, "pasteaccount", accountId, null);

        log.info("Calling Pwned Service with url {}", url);
        PasteEntityResponse pasteEntityResponse = new PasteEntityResponse();
        try {
            ResponseEntity<List<PasteEntity>> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity(httpHeaders), new ParameterizedTypeReference<List<PasteEntity>>() {
                    });

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                pasteEntityResponse.setPasteEntities(response.getBody());
                pasteEntityResponse.setComments("Pwned Service successfully responded with the following pastes");
            } else {
                throw new HttpClientErrorException(response.getStatusCode(), response.getStatusCode().getReasonPhrase(),
                        response.getHeaders(), null, Charset.defaultCharset());
            }
        } catch (HttpClientErrorException ex) {
            log.info("Pwned Service responded with error status {}", ex.getStatusCode());
            pasteEntityResponse.setComments(HttpResponseUtils.convertExceptionToResponseString(ex));
        }

        return pasteEntityResponse;
    }
}
