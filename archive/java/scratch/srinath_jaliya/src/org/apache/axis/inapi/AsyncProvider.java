/*
 * Created on Mar 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.inapi;

import org.apache.axis.outapi.Callback;
import org.apache.axis.context.MessageContext;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface AsyncProvider {
    public void invoke(MessageContext msgCtx,Callback callback);
}
