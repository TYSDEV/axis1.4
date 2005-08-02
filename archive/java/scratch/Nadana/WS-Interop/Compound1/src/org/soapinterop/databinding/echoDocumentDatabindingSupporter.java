
    package org.soapinterop.databinding;

    /**
     *  Auto generated supporter class for XML beans by the Axis code generator
     */

    public class echoDocumentDatabindingSupporter {
             
  
          public  static org.apache.axis2.om.OMElement  toOM(org.soapinterop.xsd.XDocumentDocument1 param){
		    org.apache.axis2.om.impl.llom.builder.StAXOMBuilder builder = org.apache.axis2.om.impl.llom.factory.OMXMLBuilderFactory.createStAXOMBuilder
            (org.apache.axis2.om.OMAbstractFactory.getOMFactory(),new org.apache.axis2.clientapi.StreamWrapper(param.newXMLStreamReader())) ;
		    org.apache.axis2.om.OMElement documentElement = builder.getDocumentElement();
            //Building the element is needed to avoid certain stream errors!
            documentElement.build();
            return documentElement;
          }
       
  
          public  static org.apache.axis2.om.OMElement  toOM(org.soapinterop.xsd.ResultDocumentDocument1 param){
		    org.apache.axis2.om.impl.llom.builder.StAXOMBuilder builder = org.apache.axis2.om.impl.llom.factory.OMXMLBuilderFactory.createStAXOMBuilder
            (org.apache.axis2.om.OMAbstractFactory.getOMFactory(),new org.apache.axis2.clientapi.StreamWrapper(param.newXMLStreamReader())) ;
		    org.apache.axis2.om.OMElement documentElement = builder.getDocumentElement();
            //Building the element is needed to avoid certain stream errors!
            documentElement.build();
            return documentElement;
          }
       


          public static org.apache.xmlbeans.XmlObject fromOM(org.apache.axis2.om.OMElement param,
               java.lang.Class type){
                try{
                    
                    if (org.soapinterop.xsd.XDocumentDocument1.class.equals(type)){
                        return org.soapinterop.xsd.XDocumentDocument1.Factory.parse(param.getXMLStreamReader()) ;
                    }
                     
                    if (org.soapinterop.xsd.ResultDocumentDocument1.class.equals(type)){
                        return org.soapinterop.xsd.ResultDocumentDocument1.Factory.parse(param.getXMLStreamReader()) ;
                    }
                     
                 }catch(java.lang.Exception e){
                    throw new RuntimeException("Data binding error",e);
                }
             return null;
          }

        //Generates an empty object for testing
        // Caution - need some manual editing to work properly
         public static org.apache.xmlbeans.XmlObject getTestObject(java.lang.Class type){
                try{
                   
                    if (org.soapinterop.xsd.XDocumentDocument1.class.equals(type)){
                        org.soapinterop.xsd.XDocumentDocument1 emptyObject= org.soapinterop.xsd.XDocumentDocument1.Factory.newInstance();
                        ////////////////////////////////////////////////
                        // TODO
                        // Fill in the empty object with necessaey values. Empty XMLBeans objects do not generate proper events
                        ////////////////////////////////////////////////
                        return emptyObject;
                    }
                     
                    if (org.soapinterop.xsd.ResultDocumentDocument1.class.equals(type)){
                        org.soapinterop.xsd.ResultDocumentDocument1 emptyObject= org.soapinterop.xsd.ResultDocumentDocument1.Factory.newInstance();
                        ////////////////////////////////////////////////
                        // TODO
                        // Fill in the empty object with necessaey values. Empty XMLBeans objects do not generate proper events
                        ////////////////////////////////////////////////
                        return emptyObject;
                    }
                     
                 }catch(java.lang.Exception e){
                   throw new RuntimeException("Test object creation failure",e);
                }
             return null;
          }
     }
    