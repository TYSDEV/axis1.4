package org.apache.axis.clientapi;
import java.io.InputStream;

import org.apache.axis.context.MessageContext;
import org.apache.axis.description.AxisRegistry;
import org.apache.axis.description.AxisService;
import org.apache.axis.messaging.EPR;
import org.apache.axis.messaging.MessageReciver;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;
import org.apache.axis.serverapi.EndpointReciver;
import org.apache.xml.utils.QName;

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
public class Call {
    AxisRegistry registry;
    public SOAPEnvelope sendAndReciveSync(SOAPEnvelope env, EPR to) {
        MessageSender sender = new MessageSender(registry);
        sender.send(env);
        InputStream in = registry.getEndPointManager().getInStream(to);
        SOAPEnvelope res = new SOAPEnvelope(in);
        MessageReciver reciver = new MessageReciver(registry);
        reciver.recive(res);
        return res;
    }

    public void sendAndReciveAsync(
        SOAPEnvelope env,
        EPR to,
        Callback callback) {
        MessageSender sender = new MessageSender(registry);
        sender.send(env);

        String serviceName = "replyService";
        EPR replyTo = new EPR("http:127.0.0.1:8080/services/"+serviceName);
        registry.getEndPointManager().makeSureListenerIsUp(replyTo);
        String messageId = String.valueOf(System.currentTimeMillis());
        AxisService service = new AxisService(new QName(serviceName));
        CallbacksBag bag = registry.getCallBackBag();
        bag.addCallBack(messageId,callback);
        service.setReciever(new CallbackReciver(bag));
        registry.addService(service);
    }
    
    public void sendOneWay(SOAPEnvelope env, EPR to){
        String transport = null;
        MessageSender sender = new MessageSender(registry);
        sender.send(env);
        if("http".equals(transport)){
            //check for the HTTP 202 OK
        }
    }
    
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
    public void sendOneWayRobustAsync(SOAPEnvelope env, EPR to,Callback callback) throws Exception{
        String transport = null;
        MessageSender sender = new MessageSender(registry);
        sender.send(env);
        if("http".equals(transport)){
            boolean isResponseIsHTTP202 = true;            
            if (isResponseIsHTTP202){
               //return
            }
        }
        String serviceName = "replyService";
        EPR replyTo = new EPR("http:127.0.0.1:8080/services/"+serviceName);
        registry.getEndPointManager().makeSureListenerIsUp(replyTo);
        String messageId = String.valueOf(System.currentTimeMillis());
        AxisService service = new AxisService(new QName(serviceName));
        CallbacksBag bag = registry.getCallBackBag();
        bag.addCallBack(messageId,callback);
        service.setReciever(new CallbackReciver(bag));
        registry.addService(service);
    }

    public class CallbackReciver implements EndpointReciver {
        private CallbacksBag callbacks;
        public CallbackReciver(CallbacksBag callbacks) {
            this.callbacks = callbacks;
        }
        public void invoke(MessageContext msgctx) {
            Callback callback = callbacks.getCallBack(msgctx.getMessageID());
            callback.handleResult(msgctx.getEnvelope());
        }
    }

}
