/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.inapi;

import org.apache.axis.context.MessageContext;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InOutEndpointSyncDialog implements EndpointDialog{
    private Provider provider;


    public void handleMessage(MessageContext msgctx) {
        SOAPEnvelope result = provider.invoke(msgctx);
        MessageSender sender = new MessageSender(msgctx.getRegistry());
        sender.setTo(msgctx.getReplyTo());
        sender.send(result);
    }
    
    /**
     * @return
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * @param provider
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
