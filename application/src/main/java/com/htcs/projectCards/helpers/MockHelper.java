package com.htcs.projectCards.helpers;

import com.htcs.projectCards.core.users.User;

import javax.inject.Inject;

public class MockHelper {
  @Inject
  public MockHelper() {

  }

  public User mockUser() {
    return new User(1l, "Dustin", "Lancaster", "biglandy71", "2c00l4sch00l");
  }
}
