package com.msh.demo.twitter;

import com.google.common.eventbus.EventBus;
import io.dropwizard.lifecycle.Managed;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

import static com.google.common.base.Preconditions.checkNotNull;

public class TwitterService implements Managed {
  private final TwitterConfiguration config;
  private final EventBus bus;

  private final TwitterStreamFactory streamFactory = new TwitterStreamFactory();
  private TwitterStream sampleStream;

  public TwitterService(TwitterConfiguration config, EventBus bus) {
    this.config = checkNotNull(config);
    this.bus = checkNotNull(bus);
  }

  @Override
  public void start() throws Exception {
    sampleStream = streamFactory.getInstance();
    sampleStream.setOAuthConsumer(config.getConsumerKey(), config.getConsumerSecret());
    sampleStream.setOAuthAccessToken(new AccessToken(config.getUserKey(), config.getUserSecret()));

    sampleStream.addListener(new TweetListener(bus));
    sampleStream.sample();
  }

  @Override
  public void stop() throws Exception {
    sampleStream.clearListeners();
    sampleStream.cleanUp();
    sampleStream = null;
  }

  private static final class TweetListener extends StatusAdapter {
    private final EventBus bus;

    TweetListener(EventBus bus) {
      this.bus = checkNotNull(bus);
    }

    @Override
    public void onStatus(Status status) {
      bus.post(status);
    }

    @Override
    public void onStallWarning(StallWarning warning) {
      bus.post(warning);
    }
  }
}
