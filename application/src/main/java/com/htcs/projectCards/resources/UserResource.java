package com.htcs.projectCards.resources;

import com.codahale.metrics.annotation.Timed;
import com.htcs.projectCards.core.users.User;
import com.htcs.projectCards.core.users.UserRequest;
import com.htcs.projectCards.core.users.UserService;
import com.htcs.projectCards.helpers.MockHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.dropwizard.auth.Auth;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/users")
@Api(value = "UserResource", description = "User resource operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
  private final MockHelper mockHelper;
  private final UserService userService;

  @Inject
  public UserResource(MockHelper mockHelper, UserService userService){
    this.mockHelper = mockHelper;
    this.userService = userService;
  }

  /**
   * Look up user by it's primary identifier.
   *
   * @param userId the primary identifier of a User
   * @return a User
   */
  @GET
  @Path("/{userId}")
  @ApiOperation("Look up a card by it's primary identifier.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Retrieved a User by userId", response = User.class),
      @ApiResponse(code = 404, message = "Unable to look up User", response = String.class)
  })
  @Timed
  public Response getUser(@Auth String apiUser,
                          @PathParam("userId") long userId) {
    return Response.ok()
        .entity(mockHelper.mockUser())
        .build();
  }

  @POST
  @ApiOperation("Post an action on a submission to run through the state machine.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Retrieved a submission by id", response = UserRequest.class),
      @ApiResponse(code = 404, message = "Unable to look up Submission", response = String.class),
      @ApiResponse(code = 409, message = "Version mismatch for a submission", response = String.class),
      @ApiResponse(code = 422, message = "State machine error", response = String.class),
      @ApiResponse(code = 500, message = "Enforcing content or actions for a response. " +
          "Requirements not met.", response = String.class)
  })
  @Timed
  public Response createUser(@ApiParam("userRequest") UserRequest userRequest) {
    return Response.ok().entity(userRequest).build();
  }
}
