package com.htcs.projectCards.helpers;

import com.google.common.collect.Lists;
import com.htcs.projectCards.core.cards.Card;
import com.htcs.projectCards.core.cards.CardType;
import com.htcs.projectCards.core.cards.MtgCard;
import com.htcs.projectCards.core.collections.CardCollection;
import com.htcs.projectCards.core.collections.CardCollectionType;
import com.htcs.projectCards.core.users.User;

import javax.inject.Inject;
import java.util.List;

public class MockHelper {
  @Inject
  public MockHelper() {

  }

  public User mockUser() {
    return new User(1l, "Dustin", "Lancaster", "biglandy71", "2c00l4sch00l");
  }

  //TODO move this out to a MockHelpers.class once DI is working and inject it here.
  public CardCollection mockCardCollection(long collectionId) {
    return new CardCollection(collectionId, "Wossum's Card Collection", CardType.MTG, "some random notes",
        CardCollectionType.WISH_LIST, mockUser(), mockCards());
  }

  public List<Card> mockCards() {
    Card card1 = new MtgCard(1l, 1l, 1l, CardType.MTG);
    Card card2 = new MtgCard(2l, 1l, 1l, CardType.MTG);
    return Lists.newArrayList(card1, card2);
  }

  public Card mockMtgCard() {
    return new MtgCard(1l, 1l, 1l, CardType.MTG);
  }

//  public User mockUser() {
//    return new User(1l, "David", "Wossum", "wossom", "Iam2c00l");
//  }
}
