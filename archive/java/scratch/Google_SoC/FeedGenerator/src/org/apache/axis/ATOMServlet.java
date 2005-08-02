package org.apache.axis;


import org.apache.axis.feed.feedbuilder.Constants;
import org.apache.axis.feed.feedmodel.atom.ATOMFeed;

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
 * Date: Jul 20, 2005
 * Time: 4:35:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/xml; charset=utf-8");
        try {
            ATOMFeed feed = (ATOMFeed) System.getProperties().get(Constants.ATOM_KEY);
            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getOutputStream());
            feed.serialize(writer);
            writer.flush();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
