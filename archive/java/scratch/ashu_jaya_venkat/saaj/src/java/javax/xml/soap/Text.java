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
package javax.xml.soap;

/**
 * A representation of a node whose value is text. A <CODE>
 *   Text</CODE> object may represent text that is content or text
 *   that is a comment.
 */
public interface Text extends Node, org.w3c.dom.Text {

    /**
     * Retrieves whether this <CODE>Text</CODE> object
     * represents a comment.
     * @return  <CODE>true</CODE> if this <CODE>Text</CODE> object is
     *     a comment; <CODE>false</CODE> otherwise
     */
    public abstract boolean isComment();
}
