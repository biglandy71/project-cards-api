package com.msh.demo;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.skife.jdbi.v2.DBI;

public class ProjectCardsApplication extends Application<ProjectCardsConfiguration> {
  private final SwaggerDropwizard swagger = new SwaggerDropwizard();

  public static void main(String[] args) throws Exception {
    new ProjectCardsApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<ProjectCardsConfiguration> bootstrap) {
    bootstrap.addBundle(new FlywayBundle<ProjectCardsConfiguration>() {
      @Override
      public DataSourceFactory getDataSourceFactory(ProjectCardsConfiguration config) {
        return config.getDataSourceFactory();
      }
    });

    swagger.onInitialize(bootstrap);
  }

  @Override
  public void run(ProjectCardsConfiguration config, Environment env) throws Exception {
    // Database
    DBIFactory factory = new DBIFactory();
    DBI db = factory.build(env, config.getDataSourceFactory(), "db");

    swagger.onRun(config, env, "localhost");
  }
}
