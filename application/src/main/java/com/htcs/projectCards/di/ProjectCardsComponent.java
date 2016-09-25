package com.htcs.projectCards.di;

import com.htcs.projectCards.resources.CardCollectionResource;
import com.htcs.projectCards.resources.CardResource;
import com.htcs.projectCards.resources.UserResource;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {ProjectCardsModule.class})
public interface ProjectCardsComponent {
  CardResource getCardResource();

  CardCollectionResource getCardCollectionResource();

  UserResource getUserResource();

//  DynamoDbHealthCheck getDynamoDbHealthCheck();
}
