/*
 * Created on Mar 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.apache.axis.outapi;

import org.apache.axis.description.AxisServiceEndpoint;
import org.apache.axis.messaging.EPR;
import org.apache.axis.messaging.MessageSender;
import org.apache.axis.messaging.SOAPEnvelope;
import org.apache.xml.utils.QName;

/**
 * @author srinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendAndReciveAsyncDialog extends AbstractSendingDialog {
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
      AxisServiceEndpoint service = new AxisServiceEndpoint(new QName(serviceName));
      CallbacksBag bag = registry.getCallBackBag();
      bag.addCallBack(messageId,callback);
      service.setReciever(new CallbackReciver(bag));
      registry.addService(service);
  }
}
