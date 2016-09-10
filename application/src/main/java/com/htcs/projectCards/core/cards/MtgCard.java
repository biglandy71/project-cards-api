package com.htcs.projectCards.core.cards;

public class MtgCard extends Card {
  private final CardType cardType;

  public MtgCard(long id, long cardTypeId, long thirdPartyId, CardType cardType) {
    super(id, cardTypeId, thirdPartyId);
    this.cardType = cardType;
  }

  public CardType getCardType() {
    return cardType;
  }
}
