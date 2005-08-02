package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.CloudElement;
import org.apache.axis.feed.feedmodel.rss.Constants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:53:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSCloudElement implements CloudElement {

    private String domain;
    private int port;
    private String path;
    private String registerProcedure;
    private String protocol;

    public RSSCloudElement(int port, String path, String registerProcedure, String protocol, String domain) {
        this.domain = domain;
        this.port = port;
        this.path = path;
        this.registerProcedure = registerProcedure;
        this.protocol = protocol;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {

        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CLOUD_NAME, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CLOUD_PORT_ATTR, new Integer(port).toString());
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CLOUD_PATH_ATTR, path);
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CLOUD_REGPRO_ATTR, registerProcedure);
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CLOUD_PROTOCOL_ATTR, protocol);
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CLOUD_DOMAIN_ATTR, domain);
        streamWriter.writeEndElement();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRegisterProcedure() {
        return registerProcedure;
    }

    public void setRegisterProcedure(String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }


}
