/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.inapi;

import org.apache.axis.context.MessageContext;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InEndpointAsyncDialog implements EndpointDialog{
    private Provider provider;

    public void handleMessage(final MessageContext msgctx) {
        Thread thrad = new Thread(new Runnable() {
            public void run() {
                provider.invoke(msgctx);
            }
        });
        thrad.start();
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
