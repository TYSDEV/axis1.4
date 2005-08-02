/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedbuilder.rss;

import junit.framework.TestCase;
import org.apache.axis2.description.ServiceDescription;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.description.OperationDescription;
import org.apache.wsdl.WSDLEndpoint;
import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedmodel.rss.factory.RSSFactory;
import org.apache.axis.feed.feedmodel.rss.impl.*;
import org.apache.axis.feed.feedmodel.rss.Channel;
import org.apache.axis.feed.feedmodel.rss.Item;
import org.apache.axis.feed.feedmodel.rss.GuidElement;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;
import org.apache.axis.feed.feedmodel.atom.ATOMConstants;

import javax.xml.namespace.QName;
import java.util.Iterator;
import java.util.Properties;
import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;


public class RSSFeedBuilderTest extends TestCase {
     private Integer connt = new Integer(1);
     private  ServiceDescription description;
	public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        description= new ServiceDescription(new QName(ATOMConstants.NAMESPASE_URL,"localname"));
        description.setLastupdate();

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for RSSFeedBuilderTest.
	 * @param name
	 */
	public RSSFeedBuilderTest(String name) {
		super(name);
	}

	public void testInitialiseFeed() {

        assertEquals(true,createChannel("http://www.localhost:8080/axis2")) ;
	}

	public void testAddHOTFeed() {

        assertEquals(true,createRSSFileAppending(description,"http://www.localhost:8080/axis2")) ;

	}

	public void testRemoveFeed() {
		//TODO Implement removeFeed().
	}
    private boolean createChannel(String channel_defaultHome) {
            boolean temp =false;
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
                    temp=true;
                } catch (MalformedURLException e) {
                    temp =false;
                    e.printStackTrace();
                }


            }
         return temp;
        }


        private RSSFeed build() throws Exception {

            Properties properties = System.getProperties();
            RSSFeed rssFeed = (RSSFeed) properties.get(Constants.RSS_KEY);
            if (rssFeed != null) {
                return rssFeed;
            }

            return null;
        }

        private boolean createRSSFileAppending(ServiceDescription serviceDescription, String itemDefualtLink) {
              boolean boolTemp =false;

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
                boolTemp= true;

            } catch (Exception e) {
                boolTemp=false;
                e.printStackTrace();
            }
            return boolTemp;
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
