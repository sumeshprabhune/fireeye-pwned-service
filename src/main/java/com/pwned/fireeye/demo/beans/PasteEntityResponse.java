package com.pwned.fireeye.demo.beans;

import javax.validation.constraints.Past;
import java.util.List;

public class PasteEntityResponse {

    private String comments;
    private List<PasteEntity> pasteEntities;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<PasteEntity> getPasteEntities() {
        return pasteEntities;
    }

    public void setPasteEntities(List<PasteEntity> pasteEntities) {
        this.pasteEntities = pasteEntities;
    }
}
