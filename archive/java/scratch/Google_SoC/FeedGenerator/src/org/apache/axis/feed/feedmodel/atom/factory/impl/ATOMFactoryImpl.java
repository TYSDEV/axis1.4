package org.apache.axis.feed.feedmodel.atom.factory.impl;

import org.apache.axis.feed.feedmodel.Feed;
import org.apache.axis.feed.feedmodel.atom.*;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.atom.impl.*;

import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:29:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMFactoryImpl implements ATOMFactory {


    public Feed createFeed(String version) {
        return null;
    }


    public ATOMFeed createFeedElement(String versionAttr, ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, ATOMAuthorElement authorElement) {
        return new ATOMFeedImpl(versionAttr, titleElement, modifiedElement, linkElement, authorElement);
    }

    public ATOMEntry createEntryElement(ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, DateElement issuedElement) {
        return new ATOMEntryElement(titleElement, modifiedElement, linkElement, issuedElement);
    }

    public ContentElement createATOMTitleElement(String title) {
        return new ATOMTitleElement(title);
    }

    public ContentElement createATOMSummaryElement(String summary) {
        return new ATOMSummaryElement(summary);
    }

    public ContentElement createATOMContentElement(String content) {
        return new ATOMContentElement(content);
    }

    public ContentElement createATOMTaglineElement(String tagcontent) {
        return new ATOMTaglineElement(tagcontent);
    }


    public DateElement createATOMCreatedElement(Date date) {
        return new ATOMCreatedElement(date);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public DateElement createATOMIsuuedElement(Date date) {
        return new ATOMIssuedElement(date);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public DateElement createATOMModifiedElement(Date date) {
        return new ATOMModifiedElement(date);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PersonalElement createATOMAuthorElement(PersonalNameElement name) {
        return new ATOMAuthorElement(name);
    }

    public PersonalEmailElement createPersonalEmailElement(String email) {
        return new PersonalEmailElementImpl(email);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PersonalNameElement createPersonalNameElement(String name) {
        return new PersonalNameElementImpl(name);
    }

    public PersonalURLElement createPersonalURLElement(URL url) {
        return new PersonalURLElementImpl(url);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IDElement createATOMIDElement(String id) {
        return new ATOMID(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public LinkElement createATOMLinkElement(String relAttribute, String herfAttribute, String typeAttribute) {
        return new ATOMLinkElement(relAttribute, herfAttribute, typeAttribute);
    }

    public ModeAttribute createModeAttribute(String mode) {
        return new ModeAttributeImpl(mode);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public TypeAttribute createTypeAttribute(String mediaType) {
        return new TypeAttributeImpl(mediaType);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
