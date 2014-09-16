package com.msh.demo.twitter;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TwitterResourceTest {
  private TweetDAO dao;
  private EventBus bus;

  @Before
  public void setup() {
    dao = mock(TweetDAO.class);
    bus = mock(EventBus.class);
  }

  @Test
  public void testRegistersWithBusOnCreation() {
    TwitterResource resource = new TwitterResource(dao, bus);
    verify(bus).register(same(resource));
  }

  @Test
  public void testReturnsTenMostRecentTweets() {
    TwitterResource resource = new TwitterResource(dao, bus);
    for (int i = 0; i < 100; i++) {
      Status status = mock(Status.class);
      when(status.getLang()).thenReturn("en");
      when(status.getText()).thenReturn("Tweet #" + i);

      resource.onStatusUpdate(status);
    }

    List<String> recent = resource.getRecentTweets();
    assertEquals("Tweet #90", recent.get(0));
    assertEquals("Tweet #91", recent.get(1));
    assertEquals("Tweet #92", recent.get(2));
    assertEquals("Tweet #93", recent.get(3));
    assertEquals("Tweet #94", recent.get(4));
    assertEquals("Tweet #95", recent.get(5));
    assertEquals("Tweet #96", recent.get(6));
    assertEquals("Tweet #97", recent.get(7));
    assertEquals("Tweet #98", recent.get(8));
    assertEquals("Tweet #99", recent.get(9));
  }

  @Test
  public void testIgnoresNonEnglishTweets() {
    TwitterResource resource = new TwitterResource(dao, bus);

    // Start with a few English tweets
    for (int i = 0; i < 5; i++) {
      Status status = mock(Status.class);
      when(status.getLang()).thenReturn("en");
      when(status.getText()).thenReturn("English #" + i);

      resource.onStatusUpdate(status);
    }

    // Then send in a bunch of non-English tweets
    for (int i = 0; i < 100; i++) {
      Status status = mock(Status.class);
      when(status.getLang()).thenReturn("fr");
      when(status.getText()).thenReturn("French #" + i);

      resource.onStatusUpdate(status);
    }

    // Then finish with some more English tweets
    for (int i = 5; i < 10; i++) {
      Status status = mock(Status.class);
      when(status.getLang()).thenReturn("en");
      when(status.getText()).thenReturn("English #" + i);

      resource.onStatusUpdate(status);
    }

    List<String> recent = resource.getRecentTweets();
    assertEquals("English #0", recent.get(0));
    assertEquals("English #1", recent.get(1));
    assertEquals("English #2", recent.get(2));
    assertEquals("English #3", recent.get(3));
    assertEquals("English #4", recent.get(4));
    assertEquals("English #5", recent.get(5));
    assertEquals("English #6", recent.get(6));
    assertEquals("English #7", recent.get(7));
    assertEquals("English #8", recent.get(8));
    assertEquals("English #9", recent.get(9));
  }
}
