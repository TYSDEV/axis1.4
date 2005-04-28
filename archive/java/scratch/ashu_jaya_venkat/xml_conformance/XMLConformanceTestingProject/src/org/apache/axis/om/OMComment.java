/*
 * Created on Apr 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface OMComment extends OMNode {
	public String getContent();

	//This method is already present in OMNode. So no need to redeclare.
	//public OMElement getParent();
}
