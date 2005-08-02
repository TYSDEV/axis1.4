package org.apache.axis.feed.feedmodel.rss.util.impl;

import org.apache.axis.feed.feedmodel.Feed;
import org.apache.axis.feed.feedmodel.FeedContainer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 3:33:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class FeedContainerImpl implements FeedContainer {
    private ArrayList feedList;

    public FeedContainerImpl() {
    }

    public void addFeed(Feed feed) {
        //To change body of implemented methods use File | Settings | File Templates.
        feedList = new ArrayList();
        feedList.add(feed);
    }

    public Iterator getFeedsIterator() {
        return this.feedList.iterator();
    }

    public ArrayList getFeeds() {
        return this.feedList;
    }
}
