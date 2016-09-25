package com.htcs.projectCards.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.htcs.projectCards.core.cards.Card;
import com.htcs.projectCards.core.cards.CardType;
import com.htcs.projectCards.core.cards.MtgCard;
import com.htcs.projectCards.core.collections.CardCollection;
import com.htcs.projectCards.core.collections.CardCollectionType;
import com.htcs.projectCards.core.users.User;
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
  @Inject
  public CardCollectionResource() {
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
  public Response getCard(@Auth String apiUser,
                          @PathParam("collectionId") long collectionId) {
    return Response.ok()
        .entity(mockCardCollection(collectionId))
        .build();
  }

  //TODO move this out to a MockHelpers.class once DI is working and inject it here.
  private CardCollection mockCardCollection(long collectionId) {
    return new CardCollection(collectionId, "Wossum's Card Collection", CardType.MTG, "some random notes",
        CardCollectionType.WISH_LIST, mockUser(), mockCards());
  }

  private List<Card> mockCards() {
    Card card1 = new MtgCard(1l, 1l, 1l, CardType.MTG);
    Card card2 = new MtgCard(2l, 1l, 1l, CardType.MTG);
    return Lists.newArrayList(card1, card2);
  }

  private User mockUser() {
    return new User(1l, "David", "Wossum", "wossom", "Iam2c00l");
  }
}
