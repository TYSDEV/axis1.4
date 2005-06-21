/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.encoding;

import java.io.Serializable;

/**
 * public interface Serializer
 * extends java.io.Serializable
 * <p>
 * The javax.xml.rpc.encoding.Serializer interface defines the base interface for serializers. A Serializer converts a Java object to an
 * XML representation using a specific XML processing mechanism and based on the specified type mapping and encoding style.
 * @version 1.0
 * @author shaas02
 *
 */
public interface Serializer extends Serializable {

	/**
	 * Gets the type of the XML processing mechanism and representation used by this Serializer.
	 * 
	 * @return XML processing mechanism type
	 */
	java.lang.String getMechanismType();
}
