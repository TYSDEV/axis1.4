package org.apache.axis.clientapi;
import java.util.HashMap;

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
public class CallbacksBag {
    private HashMap callbacks = new HashMap();
    public void addCallBack(String messageId,Callback callBack){
        callbacks.put(messageId,callBack);
    }
    public Callback getCallBack(String messageId){
        return (Callback)callbacks.get(messageId);
    }
}
