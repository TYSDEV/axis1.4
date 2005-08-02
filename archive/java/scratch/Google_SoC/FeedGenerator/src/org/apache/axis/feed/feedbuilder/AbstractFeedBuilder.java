package org.apache.axis.feed.feedbuilder;

import org.apache.axis.feed.feedbuilder.atom.ATOMFeedBuilder;
import org.apache.axis.feed.feedbuilder.rss.RSSFeedBuilder;


/**
 * Created by IntelliJ IDEA.
 * User: 020223
 * Date: Jul 16, 2001
 * Time: 1:53:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractFeedBuilder {
    public static FeedBuilder getFeedBuilder(int feedType) {
        if (Constants.ATOM_FEED == feedType) {
            return new ATOMFeedBuilder();
        }
        if (Constants.RSS_FEED == feedType) {
            return new RSSFeedBuilder();
        }
        return null;
    }


}
