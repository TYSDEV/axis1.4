/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.xml.rpc;

/**
 * Class ParameterMode
 * The javax.xml.rpc.ParameterMode is a type-safe enumeration for parameter 
 * mode. This class is used in the CallAPI to specify parameter passing modes.
 * 
 * @version 1.0
 * @author sunja07
 * @see Call
 */
public class ParameterMode {

	/**
	 * Field mode
	 */
	private final String mode;
	/**
	 * IN mode for parameter passing 
	 */
	public static final ParameterMode IN = new ParameterMode("in");
	
	/**
	 * OUT mode for parameter passing 
	 */
	public static final ParameterMode OUT = new ParameterMode("out");
	
	/**
	 * INOUT mode for parameter passing 
	 */
	public static final ParameterMode INOUT = new ParameterMode("inOut");
	
	/**
	 * Constructor
	 * @param mode
	 */
	public ParameterMode(String mode) {
		this.mode = mode;
	}
	
	/** 
	 * Method toString
	 * Returns a String describing this ParameterMode object.
	 * @overrides <code>toString</code> in class <code>java.lang.Object</code>
	 * @return A string representation of the object.
	 */
	public java.lang.String toString() {
		return mode;
	}
}
