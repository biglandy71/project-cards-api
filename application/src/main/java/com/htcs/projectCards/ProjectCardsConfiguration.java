package com.htcs.projectCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.apikey.ApiKeyBundleConfiguration;
import io.dropwizard.bundles.apikey.ApiKeyConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProjectCardsConfiguration extends Configuration implements ApiKeyBundleConfiguration {
  @NotNull
  @Valid
  @JsonProperty
  private final DataSourceFactory database = null;

  @Valid
  @NotNull
  @JsonProperty("authentication")
  private ApiKeyConfiguration apiKeyConfiguration;


  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  @Override
  public ApiKeyConfiguration getApiKeyConfiguration() {
    return apiKeyConfiguration;
  }
}
