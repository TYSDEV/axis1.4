package org.apache.axis.context;

import org.apache.axis.description.AxisRegistry;
import org.apache.axis.messaging.EPR;
import org.apache.axis.messaging.SOAPEnvelope;

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
public class MessageContext {
    private EPR to;
    private EPR replyTo;
    private AxisRegistry registry;
    
    public SOAPEnvelope getEnvelope(){
        return null;
    }
    
    public String getMessageID(){
        return null;
    }
    /**
     * @return
     */
    public EPR getReplyTo() {
        return replyTo;
    }

    /**
     * @return
     */
    public EPR getTo() {
        return to;
    }

    /**
     * @param epr
     */
    public void setReplyTo(EPR epr) {
        replyTo = epr;
    }

    /**
     * @param epr
     */
    public void setTo(EPR epr) {
        to = epr;
    }

    /**
     * @return
     */
    public AxisRegistry getRegistry() {
        return registry;
    }

    /**
     * @param registry
     */
    public void setRegistry(AxisRegistry registry) {
        this.registry = registry;
    }

}
