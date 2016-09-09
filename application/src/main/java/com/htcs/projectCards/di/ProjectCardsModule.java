package com.htcs.projectCards.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ProjectCardsModule {
  private final ObjectMapper mapper;

  public ProjectCardsModule(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Singleton
  @Provides
  public ObjectMapper provideObjectMapper() {
    mapper.registerModule(new ParameterNamesModule());
    mapper.registerModule(new Jdk8Module());
    mapper.registerModule(new JSR310Module());
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    return mapper;
  }
}
