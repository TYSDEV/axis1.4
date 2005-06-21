/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.encoding;

import java.io.Serializable;

/**
 * The javax.xml.rpc.encoding.Deserializer interface defines a base interface for deserializers. A Deserializer converts an XML
 * representation to a Java object using a specific XML processing mechanism and based on the specified type mapping and
 * encoding style.
 * @version 1.0
 * @author shaas02
 *
 */
public interface Deserializer extends Serializable {
	
	/**
	 * Gets the type of the XML processing mechanism and representation used by this Deserializer.
	 * @return XML processing mechanism type
	 */
	java.lang.String getMechanismType();

}
