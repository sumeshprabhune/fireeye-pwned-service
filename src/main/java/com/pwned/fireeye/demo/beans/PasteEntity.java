package com.pwned.fireeye.demo.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Source",
        "Id",
        "Title",
        "Date",
        "EmailCount"
})
public class PasteEntity {

    @JsonProperty("Source")
    private String source;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("EmailCount")
    private Integer emailCount;

    @JsonProperty("Source")
    public String getSource() {
        return source;
    }

    @JsonProperty("Source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("EmailCount")
    public Integer getEmailCount() {
        return emailCount;
    }

    @JsonProperty("EmailCount")
    public void setEmailCount(Integer emailCount) {
        this.emailCount = emailCount;
    }

}