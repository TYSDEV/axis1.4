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

import java.util.concurrent.Future;

//TODO: Needs a revisit, b'coz generics are involved.
/**
 * Interface Response<T>
 * 
 * @author sunja07
 */
public interface Response<T> extends Future<T> {
	
	/**
	 * Method getContext
	 * Gets the contained response context.
	 * 
	 * @return The contained response context. May be null if a response is not yet available.
	 */
	JAXRPCContext getContext();
}
