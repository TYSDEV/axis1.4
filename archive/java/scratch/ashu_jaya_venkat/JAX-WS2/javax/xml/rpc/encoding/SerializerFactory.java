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
 * public interface SerializerFactory
 * extends java.io.Serializable
 * <p>
 * The javax.xml.rpc.encoding.SerializerFactory is a factory of the serializers. A SerializerFactory is registered with a TypeMapping
 *  object as part of the TypeMappingRegistry.
 * @version 1.0
 * @author shaas02
 * @see <code>Serializer</code>
 */
public interface SerializerFactory extends Serializable {
	
	/**
	 * Returns a Serializer for the specified XML processing mechanism type.
	 * 
	 * @param mechanismType - XML processing mechanism type [TBD: definition of valid constants]
	 * @return
	 * @throws JAXRPCException - If SerializerFactory does not support the specified XML processing mechanism
	 * @throws java.lang.IllegalArgumentException - If an invalid mechanism type is specified.
	 */
	Serializer getSerializerAs(java.lang.String mechanismType)
				throws JAXRPCException, java.lang.IllegalArgumentException;
	
	/**
	 * Returns a list of all XML processing mechanism types supported by this SerializerFactory.
	 * @return List of unique identifiers for the supported XML processing mechanism types
	 */
	java.util.Iterator getSupportedMechanismTypes();

}
