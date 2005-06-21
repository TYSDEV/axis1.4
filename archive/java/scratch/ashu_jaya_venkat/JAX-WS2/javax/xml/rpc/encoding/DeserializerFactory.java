/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.encoding;

import java.io.Serializable;
import javax.xml.rpc.JAXRPCException;

/**
 * public interface DeserializerFactory
 * extends java.io.Serializable
 * <p>
 * The javax.xml.rpc.encoding.DeserializerFactory is a factory of deserializers. A DeserializerFactory is registered with a
 * TypeMapping instance as part of the TypeMappingRegistry.
 * @version 1.0
 * @author shaas02
 * @see <code>Serializer</code>
 */
public interface DeserializerFactory extends Serializable {

	/**
	 * Returns a Deserializer for the specified XML processing mechanism type.
	 * 
	 * @param mechanismType - XML processing mechanism type [TBD: definition of valid constants]
	 * @return
	 * @throws JAXRPCException - If DeserializerFactory does not support the specified XML processing mechanism
	 */
	Deserializer getDeserializerAs(java.lang.String mechanismType) throws JAXRPCException;
	
	/**
	 * Returns a list of all XML processing mechanism types supported by this DeserializerFactory.
	 * 
	 * @return List of unique identifiers for the supported XML processing mechanism types
	 */
	java.util.Iterator getSupportedMechanismTypes();
}
