/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.serverapi;

import org.apache.axis.context.MessageContext;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RobustInEndpointReciverSync implements EndpointReciver {
    private Provider provider;
    public RobustInEndpointReciverSync(Provider provider) {
        this.provider = provider;
    }

    public void invoke(final MessageContext msgctx) {
        try {
            provider.invoke(msgctx);
        } catch (Exception e) {
            SOAPEnvelope env = new SOAPEnvelope(e);
            MessageSender sender = new MessageSender(msgctx.getRegistry());
            sender.setTo(msgctx.getReplyTo());
            sender.send(env);
        }
    }

}
