package com.pwned.fireeye.demo.beans;

import java.util.ArrayList;
import java.util.List;

public class BreachResponse {

    String comments;
    List<Breach> breaches = new ArrayList<>();

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Breach> getBreaches() {
        return breaches;
    }

    public void setBreaches(List<Breach> breaches) {
        this.breaches = breaches;
    }
}
