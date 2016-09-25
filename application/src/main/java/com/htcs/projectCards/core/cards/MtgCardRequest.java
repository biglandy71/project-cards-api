package com.htcs.projectCards.core.cards;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MtgCardRequest  extends CardRequest{
  private final CardType cardType;

  @JsonCreator
  public MtgCardRequest(long cardTypeId, long thirdPartyId, CardType cardType) {
    super(cardTypeId, thirdPartyId);
    this.cardType = cardType;
  }

  public CardType getCardType() {
    return cardType;
  }
}
