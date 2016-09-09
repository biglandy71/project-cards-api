package com.htcs.projectCards.di;

import com.htcs.projectCards.resources.CardResource;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {ProjectCardsModule.class})
public interface ProjectCardsComponent {
  CardResource getCardResource();

//  DynamoDbHealthCheck getDynamoDbHealthCheck();

}
