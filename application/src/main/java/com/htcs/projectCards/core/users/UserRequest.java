package com.htcs.projectCards.core.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

  private final String firstName;

  private final String lastName;

  private final String userName;

  private final String password;

  public UserRequest(@JsonProperty("firstName") String firstName,
                     @JsonProperty("lastName") String lastName,
                     @JsonProperty("userName") String userName,
                     @JsonProperty("password") String password) {
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
