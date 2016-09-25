package com.htcs.projectCards.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.htcs.projectCards.core.cards.Card;
import com.htcs.projectCards.core.cards.CardType;
import com.htcs.projectCards.core.cards.MtgCard;
import com.htcs.projectCards.core.collections.CardCollection;
import com.htcs.projectCards.core.collections.CardCollectionType;
import com.htcs.projectCards.core.users.User;
import com.htcs.projectCards.helpers.MockHelper;
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
import java.util.List;

@Path("v1/cardCollections/")
@Api(value = "CardCollectionResource", description = "Card collection resource operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardCollectionResource {
  private final MockHelper mockHelper;

  @Inject
  public CardCollectionResource(MockHelper mockHelper) {
    this.mockHelper = mockHelper;
  }


  /**
   * Look up card collection by it's primary identifier.
   *
   * @param collectionId the primary identifier of a CardCollection
   * @return a CardCollection
   */
  @GET
  @Path("{collectionId}")
  @ApiOperation("Look up a CardCollection by it's primary identifier.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Retrieved a CardCollection by cardCollectionId", response = Card.class),
      @ApiResponse(code = 404, message = "Unable to look up CardCollection", response = String.class)
  })
  @Timed
  public Response getCardCollection(@Auth String apiUser,
                                    @PathParam("collectionId") long collectionId) {
    return Response.ok()
        .entity(mockHelper.mockCardCollection(collectionId))
        .build();
  }
}
