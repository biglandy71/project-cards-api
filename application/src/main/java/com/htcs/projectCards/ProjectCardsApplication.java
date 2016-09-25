package com.htcs.projectCards;

import com.htcs.projectCards.di.DaggerProjectCardsComponent;
import com.htcs.projectCards.di.ProjectCardsComponent;
import com.htcs.projectCards.di.ProjectCardsModule;
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

    //TODO Auth is not working

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
    ProjectCardsComponent component = DaggerProjectCardsComponent.builder()
        .projectCardsModule(new ProjectCardsModule(env.getObjectMapper()))
        .build();

    env.jersey().register(component.getCardResource());
    env.jersey().register(component.getCardCollectionResource());
    env.jersey().register(component.getUserResource());
  }

}
