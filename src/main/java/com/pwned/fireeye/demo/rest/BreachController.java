package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.beans.BreachRequest;
import com.pwned.fireeye.demo.beans.BreachResponse;
import com.pwned.fireeye.demo.service.IBreachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("breach")
public class BreachController {

    @Autowired
    private IBreachService breachService;

    @GetMapping("byAccount/{account}")
    public ResponseEntity<BreachResponse> getBreachesForAccount(@PathVariable(value = "account") String accountID,
                                                      @RequestParam(value = "domain", required = false) String domain,
                                                      @RequestParam(value = "truncateResponse", required = false) boolean truncateResponse,
                                                      @RequestParam(value = "includeUnverified", required = false) boolean includeUnverified ){

        BreachRequest breachRequest = new BreachRequest();
        breachRequest.setAccountID(accountID);
        breachRequest.setDomain(domain);
        breachRequest.setTruncateResponse(truncateResponse);
        breachRequest.setIncludeUnverified(includeUnverified);
        BreachResponse breachResponse = breachService.getAllBreaches(breachRequest);
        return new ResponseEntity<>(breachResponse, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<BreachResponse> getAllBreaches(@RequestParam(value = "domain", required = false) String domain){
        BreachRequest breachRequest = new BreachRequest();
        breachRequest.setDomain(domain);
        BreachResponse breachResponse = breachService.getAllBreaches(breachRequest);
        return new ResponseEntity<>(breachResponse, HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<BreachResponse> getBreach(@PathVariable(value = "name") String breachName){

        BreachRequest breachRequest = new BreachRequest();
        breachRequest.setBreachName(breachName);
        BreachResponse breachResponse = breachService.getBreach(breachRequest);
        return new ResponseEntity<>(breachResponse, HttpStatus.OK);
    }

    @GetMapping("/dataClasses")
    public ResponseEntity<List<String>> getBreachDataClasses(){
        return new ResponseEntity<>(breachService.getBreachDataClasses(), HttpStatus.OK);
    }

}
