package org.apache.axis.messaging;

import org.apache.axis.description.AxisRegistry;

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
public class MessageSender {
    private EPR to;
    private EPR replyTo;
    private AxisRegistry registry;
    
    public MessageSender(AxisRegistry registry){
        this.registry = registry;
    }
    public void send(SOAPEnvelope envelope){
        //find the transport based on the to
        //create MessageContext
        //invoke the Engine 
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
    public void setTo(EPR epr) {
        to = epr;
    }

    /**
     * @return
     */
    public EPR getReplyTo() {
        return replyTo;
    }

    /**
     * @param epr
     */
    public void setReplyTo(EPR epr) {
        replyTo = epr;
    }

}
