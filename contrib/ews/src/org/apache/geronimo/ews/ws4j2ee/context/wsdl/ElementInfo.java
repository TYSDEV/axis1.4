/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

package org.apache.geronimo.ews.ws4j2ee.context.wsdl;

import javax.xml.namespace.QName;

/**
 * <p>This is basically the org.apache.axis.ElementDecl (code cut and pasted).
 * this represent a &lt;element&gt; entry in the schema.</p>
 */
public class ElementInfo {
    /* name of the element */
    private QName name;
    /* type of the element can be from anonymous|type|ref */
    private QName type;

    private int minOccurs = 0;
    private int maxOccurs = 1;

    private boolean nillable = false;

    // Indicate if the ElementDecl represents
    // an xsd:any element
    private boolean anyElement = false;

    public ElementInfo() {
    }

    public ElementInfo(QName name, QName type) {
        this.type = type;
        this.name = name;
    }

    public QName getType() {
        return type;
    }

    public void setType(QName type) {
        this.type = type;
    }

    public QName getName() {
        return name;
    }

    public void setName(QName name) {
        this.name = name;
    }

    public boolean getMinOccursIs0() {
        return (minOccurs == 0);
    }

    public void setNillable(boolean nillable) {
        this.nillable = nillable;
    }

    public boolean getNillable() {
        return nillable;
    }

    /**
     * @return
     */
    public int getMaxOccurs() {
        return maxOccurs;
    }

    /**
     * @return
     */
    public int getMinOccurs() {
        return minOccurs;
    }

    /**
     * @param i
     */
    public void setMaxOccurs(int i) {
        maxOccurs = i;
    }

    /**
     * @param i
     */
    public void setMinOccurs(int i) {
        minOccurs = i;
    }

}
