package com.pwned.fireeye.demo.beans;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Name",
        "Title",
        "Domain",
        "BreachDate",
        "AddedDate",
        "ModifiedDate",
        "PwnCount",
        "Description",
        "DataClasses",
        "IsVerified",
        "IsFabricated",
        "IsSensitive",
        "IsRetired",
        "IsSpamList"
})
public class Breach {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Domain")
    private String domain;
    @JsonProperty("BreachDate")
    private String breachDate;
    @JsonProperty("AddedDate")
    private String addedDate;
    @JsonProperty("ModifiedDate")
    private String modifiedDate;
    @JsonProperty("PwnCount")
    private Integer pwnCount;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("DataClasses")
    private List<String> dataClasses = null;
    @JsonProperty("IsFabricated")
    private Boolean isFabricated;
    @JsonProperty("IsVerified")
    private Boolean isVerified;
    @JsonProperty("IsSensitive")
    private Boolean isSensitive;
    @JsonProperty("IsRetired")
    private Boolean isRetired;
    @JsonProperty("IsSpamList")
    private Boolean isSpamList;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("Domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("BreachDate")
    public String getBreachDate() {
        return breachDate;
    }

    @JsonProperty("BreachDate")
    public void setBreachDate(String breachDate) {
        this.breachDate = breachDate;
    }

    @JsonProperty("AddedDate")
    public String getAddedDate() {
        return addedDate;
    }

    @JsonProperty("AddedDate")
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    @JsonProperty("ModifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    @JsonProperty("ModifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @JsonProperty("PwnCount")
    public Integer getPwnCount() {
        return pwnCount;
    }

    @JsonProperty("PwnCount")
    public void setPwnCount(Integer pwnCount) {
        this.pwnCount = pwnCount;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("DataClasses")
    public List<String> getDataClasses() {
        return dataClasses;
    }

    @JsonProperty("DataClasses")
    public void setDataClasses(List<String> dataClasses) {
        this.dataClasses = dataClasses;
    }

    @JsonProperty("IsFabricated")
    public Boolean getIsFabricated() {
        return isFabricated;
    }

    @JsonProperty("IsFabricated")
    public void setIsFabricated(Boolean isFabricated) {
        this.isFabricated = isFabricated;
    }

    @JsonProperty("IsVerified")
    public Boolean getIsVerified() {
        return isVerified;
    }

    @JsonProperty("IsVerified")
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @JsonProperty("IsSensitive")
    public Boolean getIsSensitive() {
        return isSensitive;
    }

    @JsonProperty("IsSensitive")
    public void setIsSensitive(Boolean isSensitive) {
        this.isSensitive = isSensitive;
    }

    @JsonProperty("IsRetired")
    public Boolean getIsRetired() {
        return isRetired;
    }

    @JsonProperty("IsRetired")
    public void setIsRetired(Boolean isRetired) {
        this.isRetired = isRetired;
    }

    @JsonProperty("IsSpamList")
    public Boolean getIsSpamList() {
        return isSpamList;
    }

    @JsonProperty("IsSpamList")
    public void setIsSpamList(Boolean isSpamList) {
        this.isSpamList = isSpamList;
    }

}
