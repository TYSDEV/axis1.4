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
public class MessageReciver {
    private EPR to;
    private AxisRegistry registry;
    
    public MessageReciver(AxisRegistry registry){
         this.registry = registry;
     }
    
    
    public  void recive(SOAPEnvelope envelope){
    //find the transport based on the to
    //create MessageContext
    //invoke the Engine 
    }
}
