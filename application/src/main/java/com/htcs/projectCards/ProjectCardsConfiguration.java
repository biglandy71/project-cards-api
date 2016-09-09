package com.htcs.projectCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProjectCardsConfiguration extends Configuration {
  @NotNull
  @Valid
  @JsonProperty
  private final DataSourceFactory database = null;

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }
}
