/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedbuilder.atom;

import junit.framework.TestCase;
import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedmodel.atom.ATOMFeed;
import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.LinkElement;
import org.apache.axis.feed.feedmodel.atom.ATOMEntry;
import org.apache.axis.feed.feedmodel.atom.impl.*;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;
import org.apache.axis2.description.ServiceDescription;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.description.OperationDescription;
import org.apache.wsdl.WSDLEndpoint;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.Iterator;


public class ATOMFeedBuilderTest extends TestCase {
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

	public void testInitialiseFeed() {
       assertEquals(true,createChannel("http://www.localhost:8080/axis2")) ;
	}

	public void testAddHOTFeed() {
		assertEquals(true,createATOMFileAppending(description,"http://www.localhost:8080/axis2")) ;
	}

	public void testRemoveFeed() {
		//TODO Implement removeFeed().
	}
    private boolean createChannel(String channel_defaultHome) {
        boolean temp =false;
          try {
              if (!System.getProperties().containsKey(Constants.ATOM_KEY)) {
                  ATOMFeed atomFeed;
                  ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
                  atomFeed = fac.createFeedElement("0.3", new org.apache.axis.feed.feedmodel.atom.impl.ATOMTitleElement("AXIS2 ATOM FEED CHANNEL "), new ATOMModifiedElement(new Date()), new ATOMLinkElement(ATOMConstants.LINK_REL_ATTR_VALUE, channel_defaultHome, "text/html"), new ATOMAuthorElement(new PersonalNameElementImpl("axis 2")));
                  atomFeed.setIdElement(new ATOMID("feedid" + new Long(System.currentTimeMillis()).toString()));
                  System.getProperties().put(Constants.ATOM_KEY, atomFeed);
                  temp= true;
              }

          } catch (Exception e) {
              temp = false;
              e.printStackTrace();
          }
        return temp;

      }


      private ATOMFeed build() throws Exception {
          ATOMFeed atomFeed = (ATOMFeed) System.getProperties().get(Constants.ATOM_KEY);
          if (atomFeed != null) {

              return atomFeed;
          }
          return null;

      }

      private boolean createATOMFileAppending(ServiceDescription serviceDescription, String itemDefualtLink) {
            boolean boolTemp =false;
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
              boolTemp= true;
          } catch (Exception e) {
              boolTemp= false;
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
