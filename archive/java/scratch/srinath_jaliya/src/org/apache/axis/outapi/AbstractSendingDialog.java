package org.apache.axis.outapi;
import org.apache.axis.context.MessageContext;
import org.apache.axis.description.AxisRegistry;
import org.apache.axis.inapi.EndpointDialog;

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
public class AbstractSendingDialog {
    AxisRegistry registry;
    public static class CallbackReciver implements EndpointDialog {
        private CallbacksBag callbacks;
        public CallbackReciver(CallbacksBag callbacks) {
            this.callbacks = callbacks;
        }
        public void handleMessage(MessageContext msgctx) {
            Callback callback = callbacks.getCallBack(msgctx.getMessageID());
            callback.handleResult(msgctx.getEnvelope());
        }
    }

}
