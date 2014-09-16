package com.msh.demo.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


public class TwitterConfiguration {
  @NotNull
  @JsonProperty("consumer-key")
  private final String consumerKey = null;

  @NotNull
  @JsonProperty("consumer-secret")
  private final String consumerSecret = null;

  @NotNull
  @JsonProperty("user-key")
  private final String userKey = null;

  @NotNull
  @JsonProperty("user-secret")
  private final String userSecret = null;

  public String getConsumerKey() {
    return consumerKey;
  }

  public String getConsumerSecret() {
    return consumerSecret;
  }

  public String getUserKey() {
    return userKey;
  }

  public String getUserSecret() {
    return userSecret;
  }
}
