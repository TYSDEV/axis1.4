package org.apache.axis;


import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedmodel.rss.impl.RSSFeed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 18, 2005
 * Time: 12:00:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class RSSServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/xml; charset=utf-8");
        try {

            RSSFeed rssFeed = (RSSFeed) System.getProperties().get(Constants.RSS_KEY);

            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getOutputStream());
            rssFeed.serialize(writer);
            writer.flush();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
