package org.apache.axis.feed.feedbuilder;

import org.apache.axis2.description.ServiceDescription;

/**
 * Created by IntelliJ IDEA.
 * User: 020223
 * Date: Jul 16, 2001
 * Time: 12:35:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FeedBuilder {

    public void initialiseFeed(String channel_defaultHome);

    public void addHOTFeed(ServiceDescription serviceDescription, String itemDefualtLink);

    public void removeFeed(ServiceDescription serviceDescription, String itemDefualtLink);


}
