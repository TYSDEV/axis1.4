package org.apache.axis.description;

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
public class AxisService {
    public AxisService(QName name) {

    }

    private Object receiver;
    /**
     * @return
     */
    public Object getReciever() {
        return receiver;
    }

    /**
     * @param object
     */
    public void setReciever(Object object) {
        receiver = object;
    }

}
