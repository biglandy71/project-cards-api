package com.htcs.projectCards.core.users;

import com.fasterxml.jackson.annotation.JsonCreator;

public class User {
  private final long id;
  private final String firstName;
  private final String lastName;
  private final String userName;
  private final String password;

  @JsonCreator
  public User(long id, String firstName, String lastName, String userName, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
  }

  public long getId() {
    return id;
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
