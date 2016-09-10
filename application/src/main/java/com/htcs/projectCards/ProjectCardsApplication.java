package com.htcs.projectCards;

import com.htcs.projectCards.di.ProjectCardsComponent;
import com.htcs.projectCards.resources.CardCollectionResource;
import com.htcs.projectCards.resources.CardResource;
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

    //TODO this is the last step to get Auth working
    //TODO this is the last step ... I am missing something ... get get DI working
//    ProjectCardsComponent projectCardsComponent = DaggerClapiComponent.builder().build();

    swagger.onInitialize(bootstrap);
  }

  @Override
  public void run(ProjectCardsConfiguration config, Environment env) throws Exception {
    // Database
    DBIFactory factory = new DBIFactory();
    DBI db = factory.build(env, config.getDataSourceFactory(), "db");

    setupResources(env);

    swagger.onRun(config, env, "localhost");
  }

  private void setupResources(Environment env) {
    //TODO use dependency injection
    env.jersey().register(new CardResource());

    env.jersey().register(new CardCollectionResource());
  }
}
