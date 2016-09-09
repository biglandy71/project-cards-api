package com.msh.demo.twitter;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.dropwizard.lifecycle.Managed;
import org.joda.time.DateTime;
import twitter4j.Status;

import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class TweetProcessor implements Managed {
  private final EventBus bus;
  private final TweetDAO dao;
  private final Random random;

  public TweetProcessor(EventBus bus, TweetDAO dao) {
    this.bus = checkNotNull(bus);
    this.dao = checkNotNull(dao);
    this.random = new Random();
  }

  @Override
  public void start() throws Exception {
    bus.register(this);
  }

  @Override
  public void stop() throws Exception {
    bus.unregister(this);
  }

  @Subscribe
  public void onStatusUpdate(Status status) {
    // Only save English tweets
    if (!"en".equals(status.getLang())) {
      return;
    }

    // Only save 1% of the tweets we see to the database
    if (random.nextInt(100) < 1) {
      DateTime now = new DateTime();
      dao.insert(now, status.getText());
    }
  }
}
