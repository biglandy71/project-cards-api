package com.msh.demo.twitter;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface TweetDAO {
  @SqlUpdate("INSERT INTO tweets (created, status) VALUES (:created, :status)")
  void insert(@Bind("created") DateTime created, @Bind("status") String status);

  @SqlQuery("SELECT status FROM tweets ORDER BY created DESC LIMIT 10")
  List<String> findRecentTweets();
}
