package org.apache.axis.clientapi;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.apache.axis.messaging.EPR;

/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TransportEndpointManager {
    private HashMap inMap;
    private HashMap outMap;

    public InputStream getInStream(EPR epr) {
        return (InputStream) inMap.get(epr);
    }
    public OutputStream getOutStream(EPR epr, OutputStream out) {
        return (OutputStream) outMap.get(epr);
    }
    public void setInStream(EPR epr, InputStream in) {
        inMap.put(epr, in);
    }
    public void setOutputStream(EPR epr, OutputStream out) {
        outMap.put(epr, out);
    }

    public void makeSureListenerIsUp(EPR epr) {

    }

}
