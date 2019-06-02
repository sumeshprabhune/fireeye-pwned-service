package com.pwned.fireeye.demo.service;

import com.pwned.fireeye.demo.beans.BreachRequest;
import com.pwned.fireeye.demo.beans.BreachResponse;
import java.util.List;

public interface IBreachService {

    BreachResponse getAllBreaches(BreachRequest breachRequest);

    BreachResponse getBreach(BreachRequest breachRequest);

    List<String> getBreachDataClasses();

}
