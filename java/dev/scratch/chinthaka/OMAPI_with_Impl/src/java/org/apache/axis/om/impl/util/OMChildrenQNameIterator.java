package org.apache.axis.om.impl.util;

import org.apache.axis.om.OMNode;
import org.apache.axis.om.impl.OMNamedNodeImpl;
import org.apache.axis.om.impl.OMNodeImpl;
import org.apache.xml.utils.QName;

import java.util.Iterator;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * User: Eran Chinthaka - Lanka Software Foundation
 * Date: Oct 13, 2004
 * Time: 11:25:29 AM
 */
public class OMChildrenQNameIterator implements Iterator {

    private OMNode omNode;
    private QName qName;

    private boolean picked = false;

    public OMChildrenQNameIterator(OMNodeImpl firstChild, QName qName) {
        this.omNode = firstChild;
        this.qName = qName;
    }

    /**
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation).  This method can be called only once per
     * call to <tt>next</tt>.  The behavior of an iterator is unspecified if
     * the underlying collection is modified while the iteration is in
     * progress in any way other than by calling this method.
     *
     * @throws UnsupportedOperationException if the <tt>remove</tt>
     *                                       operation is not supported by this Iterator.
     * @throws IllegalStateException         if the <tt>next</tt> method has not
     *                                       yet been called, or the <tt>remove</tt> method has already
     *                                       been called after the last call to the <tt>next</tt>
     *                                       method.
     */
    public void remove() {
        throw new UnsupportedOperationException(); //TODO implement this
    }

    /**
     * Returns <tt>true</tt> if the iteration has more elements. (In other
     * words, returns <tt>true</tt> if <tt>next</tt> would return an element
     * rather than throwing an exception.)
     *
     * @return <tt>true</tt> if the iterator has more elements.
     */
    public boolean hasNext() {

        boolean needToMoveForward = true;
        boolean isMatchingNodeFound = false;

        while (needToMoveForward) {
            if (omNode != null) {

                System.out.println("Checking the current node, *" + omNode.getValue() + "*");
                // check the current node for the criteria
                if ((omNode instanceof OMNamedNodeImpl) && ((OMNamedNodeImpl) omNode).getQName().equals(qName)) {
                    isMatchingNodeFound = true;
                    needToMoveForward = false;
                    System.out.println("One Found");
                } else {

                    System.out.println("Moving to next Sibling ...");
                    // get the next named node
                    omNode = omNode.getNextSibling();
                    isMatchingNodeFound = needToMoveForward = !(omNode == null);
                }
            } else {
                needToMoveForward = false;
            }
        }
        return isMatchingNodeFound;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @throws java.util.NoSuchElementException
     *          iteration has no more elements.
     */
    public Object next() {
        OMNode tempNode = omNode;
        omNode = omNode.getNextSibling();
        return tempNode;
    }


}
