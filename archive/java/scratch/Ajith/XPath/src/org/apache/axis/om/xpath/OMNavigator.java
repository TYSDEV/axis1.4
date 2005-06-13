package org.apache.axis.om.xpath;

import org.jaxen.XPath;
import org.jaxen.UnsupportedAxisException;
import org.apache.axis.om.OMText;
import org.apache.axis.om.OMNamespace;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMAttribute;

import javax.xml.namespace.QName;
import java.util.Iterator;

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
*
*
*/
public class OMNavigator extends org.jaxen.DefaultNavigator{

    private static OMNavigator navigator = null;

    public static OMNavigator getInstance(){
        if (navigator==null){
            navigator = new OMNavigator();
        }
        return navigator;
    }


    private OMNavigator() {
    }


    public String getAttributeName(Object o) {
        OMAttribute attrib = (OMAttribute)o;
        return attrib.getLocalName();
    }


    public String getAttributeNamespaceUri(Object o) {
        OMAttribute attrib = (OMAttribute)o;
        OMNamespace ns = attrib.getNamespace();
        if (ns==null || ns.getName()==null){
            return "";
        }else{
            return ns.getName();
        }
    }


    public String getAttributeQName(Object o) {
        OMAttribute attrib = (OMAttribute)o;
        QName qName = attrib.getQName();
        if (qName.getPrefix()== null ||"".equals(qName.getPrefix())){
            return qName.getLocalPart();
        }else{
            return qName.getPrefix() + ":"+qName.getLocalPart();
        }
    }


    public String getAttributeStringValue(Object o) {
        OMAttribute attrib = (OMAttribute)o;
        return attrib.getValue();
    }


    public String getCommentStringValue(Object o) {
        return null; //not supported yet
    }


    public String getElementName(Object o) {
        OMElement elt = (OMElement)o;
        return elt.getLocalName();
    }

    public String getElementNamespaceUri(Object o) {
        OMElement elt = (OMElement)o;
        OMNamespace ns = elt.getNamespace();
        if (ns==null || ns.getName()==null){
            return "";
        }else{
            return ns.getName();
        }
    }

    public String getElementQName(Object o) {
        OMElement elt = (OMElement)o;
        QName qName = elt.getQName();
        if (qName.getPrefix()== null ||"".equals(qName.getPrefix())){
            return qName.getLocalPart();
        }else{
            return qName.getPrefix() + ":"+qName.getLocalPart();
        }
    }

    public String getElementStringValue(Object o) {
        OMElement elt = (OMElement)o;
        return elt.getText();
    }

    public String getNamespacePrefix(Object o) {
        OMNamespace ns = (OMNamespace)o;
        return ns.getPrefix();
    }

    public String getNamespaceStringValue(Object o) {
        OMNamespace ns = (OMNamespace)o;
        return ns.getName();
    }

    public String getTextStringValue(Object o) {
        OMText txt = (OMText)o;
        return txt.getText();
    }

    public boolean isAttribute(Object o) {
        return (o instanceof OMAttribute);
    }

    public boolean isComment(Object o) {
        return false;  //still not supported
    }

    public boolean isDocument(Object o) {
        return false;  //still not supported
    }

    public boolean isElement(Object o) {
        return (o instanceof OMElement);
    }

    public boolean isNamespace(Object o) {
        return (o instanceof OMNamespace);
    }

    public boolean isProcessingInstruction(Object o) {
        return false; //PI's not supported yet
    }

    public boolean isText(Object o) {
        return (o instanceof OMText);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public XPath parseXPath(String s) throws org.saxpath.SAXPathException {
        return new OMXPath(s);
    }

    public Iterator getAttributeAxisIterator(Object contextNode) throws UnsupportedAxisException {

        if ( ! ( contextNode instanceof OMElement ) ){
            return null;
        }

        OMElement elem = (OMElement) contextNode;
        return elem.getAttributes();
    }

    public Iterator getChildAxisIterator(Object contextNode) throws UnsupportedAxisException {
        if ( contextNode instanceof OMElement ){
            return ((OMElement)contextNode).getChildren();
        }else{
            return null;
        }
    }

}
