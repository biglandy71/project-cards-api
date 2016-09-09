package com.htcs.projectCards.resources;

import com.codahale.metrics.annotation.Timed;
import com.htcs.projectCards.core.cards.Card;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.dropwizard.auth.Auth;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;


@Path("v1/cards/")
@Api(value = "CardResource", description = "Card resource operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {
  @Inject
  public CardResource() {

  }

  /**
   * Look up card by it's primary identifier.
   *
   * @param cardId the primary identifier of a Card
   * @return a Card
   */
  @GET
  @Path("{cardId}")
  @ApiOperation("Look up a card by it's primary identifier.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Retrieved a Card by cardId", response = Card.class),
      @ApiResponse(code = 404, message = "Unable to look up Card", response = String.class)
  })
  @Timed
  public Response getSubmission(@Auth String apiUser,
                                @PathParam("cardId") UUID cardId) {

    Card card = new Card(UUID.randomUUID());
    return Response.ok()
        .entity(card)
        .build();
  }
}
