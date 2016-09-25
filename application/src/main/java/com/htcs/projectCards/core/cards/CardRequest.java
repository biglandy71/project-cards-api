package com.htcs.projectCards.core.cards;

import com.fasterxml.jackson.annotation.JsonCreator;

public abstract class CardRequest {
  private final long cardTypeId;
  private final long thirdPartyId;

  @JsonCreator
  public CardRequest(long cardTypeId, long thirdPartyId) {
    this.cardTypeId = cardTypeId;
    this.thirdPartyId = thirdPartyId;
  }

  public long getThirdPartyId() {
    return thirdPartyId;
  }

  public long getCardTypeId() {
    return cardTypeId;
  }
}
