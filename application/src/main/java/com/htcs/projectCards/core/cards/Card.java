package com.htcs.projectCards.core.cards;

import java.util.UUID;

public abstract class Card {
  private final long id;
  private final long cardTypeId;
  private final long thirdPartyId;

  public Card(long id, long cardTypeId, long thirdPartyId) {
    this.id = id;
    this.cardTypeId = cardTypeId;
    this.thirdPartyId = thirdPartyId;
  }

  public long getId() {
    return id;
  }

  public long getCardTypeId() {
    return cardTypeId;
  }

  public long getThirdPartyId() {
    return thirdPartyId;
  }
}
