package com.htcs.projectCards.core.users;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.inject.Inject;

public class UserRequest {
  private final String firstName;
  private final String lastName;
  private final String userName;
  private final String password;

  @JsonCreator
  public UserRequest(String firstName, String lastName, String userName, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}
