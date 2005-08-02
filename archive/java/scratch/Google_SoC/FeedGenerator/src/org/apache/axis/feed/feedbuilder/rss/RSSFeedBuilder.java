package org.apache.axis.feed.feedbuilder.rss;

import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedbuilder.FeedBuilder;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;
import org.apache.axis.feed.feedmodel.rss.Channel;
import org.apache.axis.feed.feedmodel.rss.GuidElement;
import org.apache.axis.feed.feedmodel.rss.Item;
import org.apache.axis.feed.feedmodel.rss.factory.RSSFactory;
import org.apache.axis.feed.feedmodel.rss.impl.*;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.description.OperationDescription;
import org.apache.axis2.description.ServiceDescription;
import org.apache.wsdl.WSDLEndpoint;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 020223
 * <p/>
 * <p/>
 * Date: Jul 16, 2001
 * Time: 12:39:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFeedBuilder implements Constants, FeedBuilder {


    private Integer connt = new Integer(1);

    public RSSFeedBuilder() {
        super();


    }


    public void initialiseFeed(String channel_defaultHome) {
        createChannel(channel_defaultHome);
    }

    public void addHOTFeed(ServiceDescription serviceDescription, String itemDefualtLink) {
        this.createRSSFileAppending(serviceDescription, itemDefualtLink);
    }

    public void removeFeed(ServiceDescription serviceDescription, String itemDefualtLink) {

        try {
            RSSFeed rssFeed;
            rssFeed = build();
            String description = serviceDescription.getServiceDescription() + eprInfo(serviceDescription) + serviceInfo(serviceDescription) + moduleInfo(serviceDescription) + wsdlInfo(serviceDescription);
            Item item = new RSSItem();
            description = "<p><ul>" + "<li>" + serviceDescription.getServiceDescription() + "</li>" + "<li>" + eprInfo(serviceDescription) + "</li>" + "<li>" + serviceInfo(serviceDescription) + "</ul></p><li>" + moduleInfo(serviceDescription) + "</li>" + "</li>" + "<li>" + wsdlInfo(serviceDescription) + "</li>" + "</li>" + "</ul></p>";
            item.setDescription(description);
            if (serviceDescription.getWSDLDefinition() != null) {
                // item.setLink(new URL(serviceDescription.getWSDLDefinition().getDocumentBaseURI()));
                item.setLink(new URL(itemDefualtLink));

            } else {
                item.setLink(new URL(itemDefualtLink));
            }
            item.setTitle(serviceDescription.getName().getLocalPart());
            item.addPubDate(new PubDateImpl(new Date(serviceDescription.getLastupdate())));
            GuidElement guidElement = new RSSGuidElement(new Long(System.currentTimeMillis()).toString() + connt.toString());
            guidElement.setPermaLink(false);
            item.addGuidElement(guidElement);
            rssFeed.getChannel().remove(item);
            System.getProperties().put(Constants.RSS_KEY, rssFeed);


        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void createChannel(String channel_defaultHome) {

        Properties properties = System.getProperties();
        if (!properties.containsKey(Constants.RSS_KEY)) {
            RSSFactory fac = (RSSFactory) AbstractFeedFactory.getFeedFactory("RSS");
            RSSFeed rssFeed = (RSSFeed) fac.createFeed("2.0");
            Channel channel;
            try {
                channel = new RSSChannel("Apache Axis2", new URL(channel_defaultHome), "Apache Axis2 is an implementation of the Simple Object Access Protocol (SOAP) Recommendation from the W3C. Axis2 can be used to provide and consume Web Services.");
                channel.addPudDate(new PubDateImpl(new Date()));
                channel.addLastBuildDate(new LastBuildDateImpl(new Date()));
                channel.addCategoryElement(new RSSCategoryElement("WEB SERVICE FEEDS"));
                channel.setGeneratorElement(new RSSGeneratorElement("Axis 2.0 Feed Generator 0.9"));
                channel.setLanguageElement(new RSSLanguageElement("en-us"));

                rssFeed.addChannel(channel);
                properties.put(Constants.RSS_KEY, rssFeed);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }
    }


    private RSSFeed build() throws Exception {

        Properties properties = System.getProperties();
        RSSFeed rssFeed = (RSSFeed) properties.get(Constants.RSS_KEY);
        if (rssFeed != null) {
            return rssFeed;
        }

        return null;
    }

    private void createRSSFileAppending(ServiceDescription serviceDescription, String itemDefualtLink) {


        try {
            RSSFeed rssFeed;
            rssFeed = build();
            String description = serviceDescription.getServiceDescription() + eprInfo(serviceDescription) + serviceInfo(serviceDescription) + moduleInfo(serviceDescription) + wsdlInfo(serviceDescription);
            Item item = new RSSItem();
            description = "<p><ul>" + "<li>" + serviceDescription.getServiceDescription() + "</li>" + "<li>" + eprInfo(serviceDescription) + "</li>" + "<li>" + serviceInfo(serviceDescription) + "</ul></p><li>" + moduleInfo(serviceDescription) + "</li>" + "</li>" + "<li>" + wsdlInfo(serviceDescription) + "</li>" + "</li>" + "</ul></p>";
            item.setDescription(description);
            if (serviceDescription.getWSDLDefinition() != null) {
                // item.setLink(new URL(serviceDescription.getWSDLDefinition().getDocumentBaseURI()));
                item.setLink(new URL(itemDefualtLink));

            } else {
                item.setLink(new URL(itemDefualtLink));
            }
            item.setTitle(serviceDescription.getName().getLocalPart());
            item.addPubDate(new PubDateImpl(new Date(serviceDescription.getLastupdate())));
            GuidElement guidElement = new RSSGuidElement(new Long(System.currentTimeMillis()).toString() + connt.toString());
            guidElement.setPermaLink(false);
            item.addGuidElement(guidElement);
            int temp = connt.intValue() + 4;
            connt = new Integer(temp);
            rssFeed.getChannel().addLastBuildDate(new LastBuildDateImpl(new Date(serviceDescription.getLastupdate())));
            rssFeed.getChannel().addItem(item);
            System.getProperties().put(Constants.RSS_KEY, rssFeed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String eprInfo(ServiceDescription serviceDescription) {
        String info = "END PONITS :";
        if (!serviceDescription.getEndpoints().isEmpty()) {
            if (!serviceDescription.getEndpoints().values().isEmpty()) {
                Iterator iterator = serviceDescription.getEndpoints().values().iterator();
                while (iterator.hasNext()) {
                    info = info + ((WSDLEndpoint) iterator.next()).getName();
                }
            }
        }
        return info;
    }

    private String wsdlInfo(ServiceDescription serviceDescription) {
        String info = "WSDL INFOMATION :";
        if (serviceDescription.getWSDLDefinition() != null) {
            info += serviceDescription.getWSDLDefinition().getQName() + serviceDescription.getWSDLDefinition().getDocumentBaseURI();
        }
        return info;
    }

    private String moduleInfo(ServiceDescription serviceDescription) {
        String info = "MODULE INFORMATION :";
        if (!serviceDescription.getEngagedModules().isEmpty()) {
            Iterator iterator = serviceDescription.getEngagedModules().iterator();
            while (iterator.hasNext()) {
                ModuleDescription moduleDescription = (ModuleDescription) iterator.next();
                info += moduleDescription.getName();
            }
        }
        return info;
    }

    private String serviceInfo(ServiceDescription serviceDescription) {
        String info = "<p>OPERATION INFORMATION:<ul>";
        if (serviceDescription.getOperations() != null) {
            if (!serviceDescription.getOperations().values().isEmpty()) {
                Iterator iterator = serviceDescription.getOperations().values().iterator();
                while (iterator.hasNext()) {
                    OperationDescription operationDescription = (OperationDescription) iterator.next();
                    info += "<li><ul><li>Operation Name :" + operationDescription.getName() + "</li><li>" + "<A href=\"" + operationDescription.getMessageExchangePattern() + "\">MEP" + operationDescription.getMessageExchangePattern() + "</A>" + "</li></ul></li>";
                }
            }


        }
        return info;
    }


}
