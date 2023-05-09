package com.atilaacedo.webclientfootball.WebClienteFootball.response;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"code",
"country",
"founded",
"national",
"logo"
})
public class TeamResponse {

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("code")
private String code;
@JsonProperty("country")
private String country;
@JsonProperty("founded")
private Integer founded;
@JsonProperty("national")
private Boolean national;
@JsonProperty("logo")
private String logo;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("founded")
public Integer getFounded() {
return founded;
}

@JsonProperty("founded")
public void setFounded(Integer founded) {
this.founded = founded;
}

@JsonProperty("national")
public Boolean getNational() {
return national;
}

@JsonProperty("national")
public void setNational(Boolean national) {
this.national = national;
}

@JsonProperty("logo")
public String getLogo() {
return logo;
}

@JsonProperty("logo")
public void setLogo(String logo) {
this.logo = logo;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
