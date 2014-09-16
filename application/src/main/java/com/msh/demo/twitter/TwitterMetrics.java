package com.msh.demo.twitter;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.dropwizard.lifecycle.Managed;
import twitter4j.StallWarning;
import twitter4j.Status;

import static com.codahale.metrics.MetricRegistry.name;
import static com.google.common.base.Preconditions.checkNotNull;

public class TwitterMetrics implements Managed {
  private final Counter numStatusUpdates;
  private final Counter numStallWarnings;
  private final EventBus bus;

  public TwitterMetrics(MetricRegistry metrics, EventBus bus) {
    this.numStatusUpdates = metrics.counter(name(getClass(), "num-status-updates"));
    this.numStallWarnings = metrics.counter(name(getClass(), "num-stall-warnings"));
    this.bus = checkNotNull(bus);
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
  @AllowConcurrentEvents
  public void onStatusUpdate(Status update) {
    numStatusUpdates.inc();
  }

  @Subscribe
  @AllowConcurrentEvents
  public void onStallWarning(StallWarning warning) {
    numStallWarnings.inc();
  }
}
