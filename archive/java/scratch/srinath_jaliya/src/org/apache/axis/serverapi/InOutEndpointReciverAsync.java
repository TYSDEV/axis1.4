/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.serverapi;

import org.apache.axis.clientapi.Callback;
import org.apache.axis.context.MessageContext;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InOutEndpointReciverAsync implements EndpointReciver{
    private AsyncProvider provider;
    public InOutEndpointReciverAsync(AsyncProvider provider){
        this.provider = provider;
    }

    public void invoke(final MessageContext msgctx) {
        final Callback callback = new Callback() {
            public void handleResult(SOAPEnvelope env) {
                MessageSender sender = new MessageSender(msgctx.getRegistry());
                sender.setTo(msgctx.getReplyTo());
                sender.send(env);
            }
        };
        Thread thrad = new Thread(new Runnable() {
            public void run() {
                provider.invoke(msgctx,callback);
            }
        });
        
    }

}
