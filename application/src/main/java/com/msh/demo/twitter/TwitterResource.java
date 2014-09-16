package com.msh.demo.twitter;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Function;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Lists;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import twitter4j.Status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/twitter")
@Api("/twitter")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class TwitterResource {
  private final TweetDAO dao;
  private final EvictingQueue<Status> recentTweets = EvictingQueue.create(10);

  public TwitterResource(TweetDAO dao, EventBus bus) {
    this.dao = checkNotNull(dao);
    bus.register(this);
  }

  @GET
  @ApiOperation("Get a list of all of the recently seen tweets")
  @Path("/recent")
  @Timed
  public List<String> getRecentTweets() {
    List<Status> statuses;
    synchronized (recentTweets) {
      statuses = Lists.newArrayList(recentTweets.iterator());
    }

    return Lists.transform(statuses, new Function<Status, String>() {
      @Override
      public String apply(Status status) {
        return status.getText();
      }
    });
  }

  @GET
  @ApiOperation("Get a list of all of the recently saved tweets")
  @Path("/saved")
  @Timed
  public List<String> getSavedTweets() {
    return dao.findRecentTweets();
  }

  @GET
  @ApiOperation("Guess a number")
  @Path("/guess")
  @Timed
  public int guess(int i) {
    return i;
  }

  @Subscribe
  @AllowConcurrentEvents
  public void onStatusUpdate(Status status) {
    // Only save English tweets
    if (!"en".equals(status.getLang())) {
      return;
    }

    synchronized (recentTweets) {
      recentTweets.offer(status);
    }
  }
}
