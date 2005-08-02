package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:20:02 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CloudElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getDomain();

    public void setDomain(String domain);

    public int getPort();

    public void setPort(int port);

    public String getPath();

    public void setPath(String path);

    public String getRegisterProcedure();

    public void setRegisterProcedure(String registerProcedure);

    public String getProtocol();

    public void setProtocol(String protocol);


}
