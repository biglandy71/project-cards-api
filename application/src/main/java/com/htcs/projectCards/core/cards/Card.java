package com.htcs.projectCards.core.cards;

import java.util.UUID;

public class Card {
  private final UUID id;

  public Card(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
