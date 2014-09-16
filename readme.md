# Dropwizard Demo

This demo was created for the engineers at [Main Street Hub][msh] to demonstrate a fully functional Dropwizard 
application that leverages several pieces of the Dropwizard ecosystem.  Hopefully it will be useful to others as well.

Components used:

* [Dropwizard][dropwizard] as the glue holding together all of the awesomeness
* [Jersey][jersey] for easy to write REST services
* [Jackson][jackson] for configuration
* [Metrics][metrics] for visibility into your running service
* [JDBI][jdbi] for database connectivity and DAO creation
* [Flyway][flyway] for managing database schemas
* [Swagger][swagger] for interactive and always up to date REST service documentation
* [Mockito][mockito] for writing tests that mock out external dependencies (databases, network services, etc.)
* [Guava][guava] for easy to use java collections and concurrency libraries (event bus)


# Getting started

1. First you'll want to build the application.  This is really easy to do using maven, just check out this repo
and then run `mvn clean package` where the repo was checked out at.  This will build all of the java code and package
it into a self-contained .jar file containing all of the necessary dependencies.

2. Update the `config.yaml` file in the root project directory.  You should add your own Twitter API credentials to
this file in order to allow you to connect to Twitter and see recent tweets.  You can verify that your config file is
well formed by running `java -jar application/target/application-1.0-SNAPSHOT.jar check config.yaml`.

3. In order to run the demo you'll need to have a database to write to.  Fortunately you won't need to download or
install anything special for this (the config.yaml is setup to use [H2][h2] by default which is a Java SQL database
that stores all of its data in a single file.  To start with, let's create the demo database and update it to the
current version of the schema (using flyway).  To do this, just execute
`java -jar application/target/application-1.0-SNAPSHOT.jar db migrate config.yaml`.

4. We're now ready to launch the application.  To do this just execute 
'java -jar application/target/application-1.0-SNAPSHOT.jar server config.yaml`.  At this point you should see output
that looks somewhat like:
```
io.dropwizard.assets.AssetsBundle: Registering AssetBundle with name: assets for path /swagger-static/*
io.dropwizard.server.ServerFactory: Starting DemoApplication


               ,
         (`.  : \               __..----..__
          `.`.| |:          _,-':::''' '  `:`-._
            `.:\||       _,':::::'         `::::`-.
              \\`|    _,':::::::'     `:.     `':::`.
               ;` `-''  `::::::.                  `::\
            ,-'      .::'  `:::::.         `::..    `:\
          ,' /_) -.            `::.           `:.     |
        ,'.:     `    `:.        `:.     .::.          \
   __,-'   ___,..-''-.  `:.        `.   /::::.         |
  |):'_,--'           `.    `::..       |::::::.      ::\
   `-'                 |`--.:_::::|_____\::::::::.__  ::|
                       |   _/|::::|      \::::::|::/\  :|
                       /:./  |:::/        \__:::):/  \  :\
                     ,'::'  /:::|        ,'::::/_/    `. ``-.__
                    ''''   (//|/\      ,';':,-'         `-.__  `'--..__
                                                             `''---::::'


org.eclipse.jetty.setuid.SetUIDListener: Opened application@4d157787{HTTP/1.1}{0.0.0.0:8080}
org.eclipse.jetty.setuid.SetUIDListener: Opened admin@68ed96ca{HTTP/1.1}{0.0.0.0:8081}
org.eclipse.jetty.server.Server: jetty-9.0.z-SNAPSHOT
io.dropwizard.jersey.DropwizardResourceConfig: The following paths were found for the configured resources:

    GET     /api-docs (com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON)
    GET     /api-docs/{route: .+} (com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON)
    GET     /twitter/recent (com.msh.demo.twitter.TwitterResource)
    GET     /twitter/saved (com.msh.demo.twitter.TwitterResource)
    GET     /swagger (io.federecio.dropwizard.swagger.SwaggerResource)

org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@47547132{/,null,AVAILABLE}
io.dropwizard.setup.AdminEnvironment: tasks =

    POST    /tasks/gc (io.dropwizard.servlets.tasks.GarbageCollectionTask)

org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@971e903{/,null,AVAILABLE}
org.eclipse.jetty.server.ServerConnector: Started application@4d157787{HTTP/1.1}{0.0.0.0:8080}
org.eclipse.jetty.server.ServerConnector: Started admin@68ed96ca{HTTP/1.1}{0.0.0.0:8081}
twitter4j.TwitterStreamImpl: Establishing connection.
twitter4j.TwitterStreamImpl: Connection established.
twitter4j.TwitterStreamImpl: Receiving status stream.
```


# Use the REST API

You can test out the REST API using using browser.  For example http://localhost:8080/twitter/recent should show you
some of the recent tweets that the system has processed from twitter.  Additionally http://localhost:8080/twitter/saved
should show you some of the recent tweets that the system has saved to the database.


# Explore metrics

You can explore the metrics for your running service by hitting the metrics resource of the admin servlet at
http://localhost:8081/metrics?pretty=true

Some interesting metrics to check out would be `com.msh.demo.twitter.TwitterMetrics.num-stall-warnings` and
`com.msh.demo.twitter.TwitterMetrics.num-status-updates` which count the number of stall warnings received from Twitter
(should be 0) as well as the number of tweets processed by the application (should be >0).  Also the
`com.msh.demo.twitter.TwitterResource.getRecentTweets` and `com.msh.demo.twitter.TwitterResource.getSavedTweets`
metrics will record the performance of the service methods in the `/twitter` URLs.  Finally the 
`com.msh.demo.twitter.TweetDAO.insert` metric will show the performance of writing tweets to the database.


# Explore the REST API

You can also use Swagger to explore the REST API by browsing to: http://localhost:8080/swagger/#!/twitter.  From within
Swagger you can see documentation for the REST API as well as try out the various methods.


# Read some tests

There is a `TwitterResourceTest` that demonstrates using mockito to test only the `TwitterResource` class logic.  It
mocks out all dependencies and uses the mocks to verify the proper interactions.


# Enhance the database schema

In the `application/src/resources/db/migration` directory you can see a migration script for creating the `tweets`
table in the database.  Try creating a new file in this directory that starts with `V2__` that modifies the `tweets`
table or adds in a new table.  Then you can run 
`java -jar application/target/application-1.0-SNAPSHOT.jar db migrate config.yaml` to migrate the database from V1 to
V2.  Restart the application and see if your tests work.


[msh]: http://www.mainstreethub.com/
[dropwizard]: http://dropwizard.io/
[jersey]: https://jersey.java.net/
[jackson]: https://github.com/FasterXML/jackson
[metrics]: http://metrics.dropwizard.io/
[jdbi]: http://jdbi.org/
[flyway]: http://flywaydb.org/
[swagger]: https://helloreverb.com/developers/swagger
[mockito]: https://code.google.com/p/mockito/
[guava]: https://code.google.com/p/guava-libraries/
[h2]: http://www.h2database.com/html/main.html