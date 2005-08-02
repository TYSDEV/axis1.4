package org.apache.axis.feed.feedmodel.atom.factory;

import org.apache.axis.feed.feedmodel.FeedFactory;
import org.apache.axis.feed.feedmodel.atom.*;
import org.apache.axis.feed.feedmodel.atom.impl.ATOMAuthorElement;

import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:23:41 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ATOMFactory extends FeedFactory {

    public ATOMFeed createFeedElement(String versionAttr, ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, ATOMAuthorElement authorElement);

    public ATOMEntry createEntryElement(ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, DateElement issuedElement);

    public ContentElement createATOMTitleElement(String title);

    public ContentElement createATOMContentElement(String content);

    public ContentElement createATOMSummaryElement(String summary);

    public ContentElement createATOMTaglineElement(String tagcontent);


    public DateElement createATOMCreatedElement(Date date);

    public DateElement createATOMIsuuedElement(Date date);

    public DateElement createATOMModifiedElement(Date date);

    public PersonalElement createATOMAuthorElement(PersonalNameElement name);

    public PersonalEmailElement createPersonalEmailElement(String email);

    public PersonalNameElement createPersonalNameElement(String name);

    public PersonalURLElement createPersonalURLElement(URL url);

    public IDElement createATOMIDElement(String id);

    public LinkElement createATOMLinkElement(String relAttribute, String herfAttribute, String typeAttribute);

    public ModeAttribute createModeAttribute(String mode);

    public TypeAttribute createTypeAttribute(String mediaType);


}
