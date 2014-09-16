package com.msh.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msh.demo.twitter.TwitterConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DemoConfiguration extends Configuration {
  @NotNull
  @Valid
  @JsonProperty
  private final DataSourceFactory database = null;

  @NotNull
  @Valid
  @JsonProperty
  private final TwitterConfiguration twitter = null;

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  public TwitterConfiguration getTwitterConfiguration() {
    return twitter;
  }
}
