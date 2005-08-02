package org.apache.axis.feed.feedmodel;


import org.apache.axis.feed.feedmodel.atom.factory.impl.ATOMFactoryImpl;
import org.apache.axis.feed.feedmodel.rss.factory.impl.RSSFactoryImpl;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 1, 2005
 * Time: 9:11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractFeedFactory {

    public static FeedFactory getFeedFactory(String factoryName) {
        FeedFactory feedFac = null;
        if (factoryName.matches("RSS")) {
            feedFac = new RSSFactoryImpl();
        }
        if (factoryName.matches("ATOM")) {
            feedFac = new ATOMFactoryImpl();
        }

        return feedFac;
    }

}
