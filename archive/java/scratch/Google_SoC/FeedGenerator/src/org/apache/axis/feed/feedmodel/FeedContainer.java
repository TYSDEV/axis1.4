package org.apache.axis.feed.feedmodel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 4:20:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FeedContainer {

    public void addFeed(Feed feed);

    public Iterator getFeedsIterator();

    public ArrayList getFeeds();
}
