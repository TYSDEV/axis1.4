/*
 * Created on Mar 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.outapi;

import org.apache.axis.messaging.EPR;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendOnewayDialog extends AbstractSendingDialog {
    public void sendOneWay(SOAPEnvelope env, EPR to){
            String transport = null;
            MessageSender sender = new MessageSender(registry);
            sender.send(env);
            if("http".equals(transport)){
                //check for the HTTP 202 OK
            }
        }
}
