package org.apache.axis.feed.feedbuilder.atom;


import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedbuilder.FeedBuilder;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;
import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.ATOMEntry;
import org.apache.axis.feed.feedmodel.atom.ATOMFeed;
import org.apache.axis.feed.feedmodel.atom.LinkElement;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.atom.impl.*;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.description.OperationDescription;
import org.apache.axis2.description.ServiceDescription;
import org.apache.wsdl.WSDLEndpoint;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by IntelliJ IDEA.
 * User: 020223
 * Date: Jul 16, 2001
 * Time: 12:38:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMFeedBuilder implements FeedBuilder {


    private Integer connt = new Integer(1);

    public ATOMFeedBuilder() {

    }


    public void initialiseFeed(String channel_defaultHome) {
        createChannel(channel_defaultHome);
    }

    public void addHOTFeed(ServiceDescription serviceDescription, String itemDefualtLink) {
        this.createATOMFileAppending(serviceDescription, itemDefualtLink);

    }

    public void removeFeed(ServiceDescription serviceDescription, String itemDefualtLink) {

        try {
            ATOMFeed atomFeed;
            atomFeed = build();
            ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
            LinkElement linkElement = fac.createATOMLinkElement(ATOMConstants.LINK_REL_ATTR_VALUE, itemDefualtLink, "text/html");

            if (serviceDescription.getWSDLDefinition() != null) {
                //  linkElement.setHerfAttribute(serviceDescription.getWSDLDefinition().getDocumentBaseURI());
            }
            ATOMEntry atomEntry = fac.createEntryElement(fac.createATOMTitleElement(serviceDescription.getName().getLocalPart()), fac.createATOMModifiedElement(new Date(serviceDescription.getLastupdate())), linkElement, fac.createATOMIsuuedElement(new Date(serviceDescription.getLastupdate())));
            atomEntry.setATOMId(new ATOMID(new Long(System.currentTimeMillis()).toString() + connt.toString()));
            String description = serviceDescription.getServiceDescription() + eprInfo(serviceDescription) + serviceInfo(serviceDescription) + moduleInfo(serviceDescription) + wsdlInfo(serviceDescription);
            description = "<p><ul>" + "<li>" + serviceDescription.getServiceDescription() + "</li>" + "<li>" + eprInfo(serviceDescription) + "</li>" + "<li>" + serviceInfo(serviceDescription) + "</ul></p><li>" + moduleInfo(serviceDescription) + "</li>" + "</li>" + "<li>" + wsdlInfo(serviceDescription) + "</li>" + "</li>" + "</ul></p>";
            atomEntry.setAtomSummaryElement(new ATOMSummaryElement(description));
            atomEntry.setAtomContentElement(new ATOMContentElement(description));
            atomFeed.remove(atomEntry);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void createChannel(String channel_defaultHome) {
        try {
            if (!System.getProperties().containsKey(Constants.ATOM_KEY)) {
                ATOMFeed atomFeed;
                ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
                atomFeed = fac.createFeedElement("0.3", new org.apache.axis.feed.feedmodel.atom.impl.ATOMTitleElement("AXIS2 ATOM FEED CHANNEL "), new ATOMModifiedElement(new Date()), new ATOMLinkElement(ATOMConstants.LINK_REL_ATTR_VALUE, channel_defaultHome, "text/html"), new ATOMAuthorElement(new PersonalNameElementImpl("axis 2")));
                atomFeed.setIdElement(new ATOMID("feedid" + new Long(System.currentTimeMillis()).toString()));
                System.getProperties().put(Constants.ATOM_KEY, atomFeed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private ATOMFeed build() throws Exception {
        ATOMFeed atomFeed = (ATOMFeed) System.getProperties().get(Constants.ATOM_KEY);
        if (atomFeed != null) {

            return atomFeed;
        }
        return null;

    }

    private void createATOMFileAppending(ServiceDescription serviceDescription, String itemDefualtLink) {

        try {
            ATOMFeed atomFeed;
            atomFeed = build();
            ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
            LinkElement linkElement = fac.createATOMLinkElement(ATOMConstants.LINK_REL_ATTR_VALUE, itemDefualtLink, "text/html");

            if (serviceDescription.getWSDLDefinition() != null) {
                //  linkElement.setHerfAttribute(serviceDescription.getWSDLDefinition().getDocumentBaseURI());
            }
            ATOMEntry atomEntry = fac.createEntryElement(fac.createATOMTitleElement(serviceDescription.getName().getLocalPart()), fac.createATOMModifiedElement(new Date(serviceDescription.getLastupdate())), linkElement, fac.createATOMIsuuedElement(new Date(serviceDescription.getLastupdate())));
            atomEntry.setATOMId(new ATOMID(new Long(System.currentTimeMillis()).toString() + connt.toString()));
            String description = serviceDescription.getServiceDescription() + eprInfo(serviceDescription) + serviceInfo(serviceDescription) + moduleInfo(serviceDescription) + wsdlInfo(serviceDescription);
            description = "<p><ul>" + "<li>" + serviceDescription.getServiceDescription() + "</li>" + "<li>" + eprInfo(serviceDescription) + "</li>" + "<li>" + serviceInfo(serviceDescription) + "</ul></p><li>" + moduleInfo(serviceDescription) + "</li>" + "</li>" + "<li>" + wsdlInfo(serviceDescription) + "</li>" + "</li>" + "</ul></p>";
            atomEntry.setAtomSummaryElement(new ATOMSummaryElement(description));
            atomEntry.setAtomContentElement(new ATOMContentElement(description));
            int temp = connt.intValue() + 4;
            connt = new Integer(temp);
            if (atomFeed != null && atomEntry != null) {
                atomFeed.addEntry(atomEntry);
                System.getProperties().put(Constants.ATOM_KEY, atomFeed);

            }

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
