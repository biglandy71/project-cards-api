package com.msh.demo;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.msh.demo.twitter.TweetDAO;
import com.msh.demo.twitter.TweetProcessor;
import com.msh.demo.twitter.TwitterMetrics;
import com.msh.demo.twitter.TwitterResource;
import com.msh.demo.twitter.TwitterService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.skife.jdbi.v2.DBI;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DemoApplication extends Application<DemoConfiguration> {
  public static void main(String[] args) throws Exception {
    new DemoApplication().run(args);
  }

  private final SwaggerDropwizard swagger = new SwaggerDropwizard();

  @Override
  public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
    bootstrap.addBundle(new FlywayBundle<DemoConfiguration>() {
      @Override
      public DataSourceFactory getDataSourceFactory(DemoConfiguration config) {
        return config.getDataSourceFactory();
      }
    });

    swagger.onInitialize(bootstrap);
  }

  @Override
  public void run(DemoConfiguration config, Environment env) throws Exception {
    // Event Bus
    Executor executor = Executors.newCachedThreadPool();
    EventBus bus = new AsyncEventBus(executor);

    // Database
    DBIFactory factory = new DBIFactory();
    DBI db = factory.build(env, config.getDataSourceFactory(), "db");
    TweetDAO dao = db.onDemand(TweetDAO.class);

    // Register managed services
    TwitterService service = new TwitterService(config.getTwitterConfiguration(), bus);
    env.lifecycle().manage(service);

    TweetProcessor processor = new TweetProcessor(bus, dao);
    env.lifecycle().manage(processor);

    TwitterMetrics metrics = new TwitterMetrics(env.metrics(), bus);
    env.lifecycle().manage(metrics);

    // Register resources
    TwitterResource resource = new TwitterResource(dao, bus);
    env.jersey().register(resource);

    swagger.onRun(config, env, "localhost");
  }
}
