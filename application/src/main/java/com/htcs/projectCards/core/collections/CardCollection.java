package com.htcs.projectCards.core.collections;

import com.htcs.projectCards.core.cards.CardType;
import com.htcs.projectCards.core.users.User;
import java.util.List;

public class CardCollection {
  private final long id;
  private final String name;
  private final CardType cardType;
  private final String notes;
  private final CardCollectionType cardCollectionType;
  private final User user;
  private final List cards;

  public CardCollection(long id, String name, CardType cardType, String notes, CardCollectionType cardCollectionType, User user, List cards) {
    this.id = id;
    this.name = name;
    this.cardType = cardType;
    this.notes = notes;
    this.cardCollectionType = cardCollectionType;
    this.user = user;
    this.cards = cards;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public CardType getCardType() {
    return cardType;
  }

  public String getNotes() {
    return notes;
  }

  public CardCollectionType getCardCollectionType() {
    return cardCollectionType;
  }

  public User getUser() {
    return user;
  }

  public List getCards() {
    return cards;
  }
}
