/*
 * Created on Mar 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.impl.llom.mtom;

/**
 * @author THilina Gunarathne
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AttachmentPart {
    String cid;
    OMBlob blob;

    public AttachmentPart(String cid, OMBlob blob) {
        this.cid = cid;
        this.blob = blob;
    }

    public String getCid() {
        return cid;
    }

    public OMBlob getBlob() {
        return blob;
    }

}
