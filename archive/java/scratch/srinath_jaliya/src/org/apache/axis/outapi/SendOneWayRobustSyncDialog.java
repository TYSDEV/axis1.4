/*
 * Created on Mar 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.outapi;

import java.io.InputStream;

import org.apache.axis.messaging.EPR;
import org.apache.axis.messaging.MessageReciver;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendOneWayRobustSyncDialog extends AbstractSendingDialog {
    public void sendOneWayRobustSync(SOAPEnvelope env, EPR to) throws Exception{
        String transport = null;
        MessageSender sender = new MessageSender(registry);
        sender.send(env);
        if("http".equals(transport)){
            boolean isResponseIsHTTP202 = true;            
            if (isResponseIsHTTP202){
               //return
            }else{
            InputStream in = registry.getEndPointManager().getInStream(to);
            SOAPEnvelope res = new SOAPEnvelope(in);
            MessageReciver reciver = new MessageReciver(registry);
            reciver.recive(res);
            throw new Exception(res.toString());
            
            }
        }else if("mail".equals(transport)){
            //what is here? Not supported I think
        }
        
        
        
    }
}
