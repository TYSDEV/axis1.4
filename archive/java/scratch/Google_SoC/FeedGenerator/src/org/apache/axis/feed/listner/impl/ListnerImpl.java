package org.apache.axis.feed.listner.impl;


import org.apache.axis.feed.feedbuilder.AbstractFeedBuilder;
import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedbuilder.FeedBuilder;
import org.apache.axis.feed.listner.ListnerConstants;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.description.ServiceDescription;
import org.apache.axis2.engine.AxisEvent;
import org.apache.axis2.engine.AxisObserver;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 020223
 * Date: Jul 16, 2001
 * Time: 9:56:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListnerImpl implements AxisObserver {

    private HashMap parameterList = new HashMap();

    public void init() {


    }

    public void update(AxisEvent event) {
        String channel_home = ListnerConstants.CHANNEL_URL_VALUE;
        String item_home = ListnerConstants.ITEM_URL_VALUE;
        if (getParameter(ListnerConstants.CHANNEL_URL_KEY) != null) {
            channel_home = getParameter(ListnerConstants.CHANNEL_URL_KEY).getValue().toString();
        }
        if (getParameter(ListnerConstants.ITEM_URL_KEY) != null) {
            item_home = getParameter(ListnerConstants.ITEM_URL_KEY).getValue().toString();
        }
        FeedBuilder atomBuilder = AbstractFeedBuilder.getFeedBuilder(Constants.ATOM_FEED);
        FeedBuilder rssBuilder = AbstractFeedBuilder.getFeedBuilder(Constants.RSS_FEED);
        atomBuilder.initialiseFeed(channel_home);
        rssBuilder.initialiseFeed(channel_home);

        if (AxisEvent.SERVICE_DEPLOY == event.getEventType()) {
            ServiceDescription serviceDescription = event.getService();
            atomBuilder.addHOTFeed(serviceDescription, item_home);
            rssBuilder.addHOTFeed(serviceDescription, item_home);
        }
        if (AxisEvent.SERVICE_REMOVE == event.getEventType()) {
            atomBuilder.removeFeed(event.getService(), item_home);
            rssBuilder.removeFeed(event.getService(), item_home);

        }

    }

    public void addParameter(Parameter parameter) {

        parameterList.put(parameter.getName(), parameter);
    }

    public Parameter getParameter(String s) {
        return (Parameter) parameterList.get(s);

    }
}
