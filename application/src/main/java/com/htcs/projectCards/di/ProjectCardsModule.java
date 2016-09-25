package com.htcs.projectCards.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htcs.projectCards.core.users.UserService;
import com.htcs.projectCards.core.users.UserServiceImpl;
import com.htcs.projectCards.helpers.MockHelper;
import com.htcs.projectCards.resources.UserResource;
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
//    mapper.registerModule(new ParameterNamesModule());
//    mapper.registerModule(new Jdk8Module());
//    mapper.registerModule(new JSR310Module());
//    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    return mapper;
  }

  @Singleton
  @Provides
  public MockHelper provideMockHelper() {
    return new MockHelper();
  }

  @Singleton
  @Provides
  public UserService provideUserService() {
    return new UserServiceImpl();
  }

  @Singleton
  @Provides
  public UserResource provideUserResource(MockHelper mockHelper, UserService userService) {
    return new UserResource(mockHelper, userService);
  }
}
