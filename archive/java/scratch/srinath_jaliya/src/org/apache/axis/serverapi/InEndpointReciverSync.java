/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.serverapi;

import org.apache.axis.context.MessageContext;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InEndpointReciverSync implements EndpointReciver{
    private Provider provider;
    public InEndpointReciverSync(Provider provider){
        this.provider = provider;
    }

    public void invoke(final MessageContext msgctx) {
        provider.invoke(msgctx);        
    }

}
