package com.bookshop.soap;
/**
 * <p>This class is genarated by the tool WSDL2Ws.
 * It take care of the serialization and the desirialization of
 * the parameter types.
 * except for the serialize(), desirialize() methods it it a bean class.
 * We are specially concerned about the alogorithem used in the desirialize method.
 * If you have any suggestions to improve, your comments are welcome.</p>
 */ 
public class ListingProductInfo  implements org.apache.axismora.encoding.InOutParameter{
	private com.bookshop.soap.ListingProductDetails[] ListingProductDetails;

	public ListingProductInfo(){
	}
	public ListingProductInfo(com.bookshop.soap.ListingProductDetails[] ListingProductDetails){
		this.ListingProductDetails = ListingProductDetails;
	}


	public org.apache.axismora.encoding.InParameter desierialize(org.apache.axismora.MessageContext msgdata)throws org.apache.axis.AxisFault{
		int count = 1;
		if(org.apache.axismora.wsdl2ws.java.ParmWriter.tag == null)
			org.apache.axismora.wsdl2ws.java.ParmWriter.tag= msgdata.getTag();
		//come to first start tag
		while(!(org.apache.axismora.wsdl2ws.java.ParmWriter.tag.getType() == org.xmlpull.v1.XmlPullParser.START_TAG))
			org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
		//skip it says the type
		org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
		//if the type of the next tag is end tag that means the content of element is null
		if(org.apache.axismora.wsdl2ws.java.ParmWriter.tag.getType() == org.xmlpull.v1.XmlPullParser.END_TAG)			return null;

		for(int i = 0;i<count;i++) {
			while(!(org.apache.axismora.wsdl2ws.java.ParmWriter.tag.getType() == org.xmlpull.v1.XmlPullParser.START_TAG))
				org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
				String localName = org.apache.axismora.wsdl2ws.java.ParmWriter.tag.getLocalpart();
				if(localName.equalsIgnoreCase("ListingProductDetails")) {
					com.bookshop.soap.ListingProductDetailsArray arrayT0 = (new com.bookshop.soap.ListingProductDetailsArray());
					arrayT0.desierialize(msgdata);
					this.ListingProductDetails = arrayT0.getParam();
				}else
					throw new org.apache.axis.AxisFault("unknown tag find "+ localName);
				org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
		}//end of for loop
		return this;

	}

	public void serialize(org.apache.axis.encoding.SerializationContext context)throws java.io.IOException{
		String m_URI ="http://soap.bookshop.com";
		String type_name = "ListingProductInfo";
			org.apache.axismora.wsdl2ws.java.ParmWriter.tagWritten = false;
		//write the parameters

		context.writeString("<ListingProductDetails xsi:type=\"soapenc:Array\" soapenc:arrayType=\"ns2:ListingProductDetails[]\" xmlns:ns2 = \"http://soap.bookshop.com\">");
		if(this.ListingProductDetails!=null){
			context.writeString("\n");
			com.bookshop.soap.ListingProductDetailsArray item0 = new com.bookshop.soap.ListingProductDetailsArray();
			item0.setParam(ListingProductDetails);
			org.apache.axismora.wsdl2ws.java.ParmWriter.tagWritten = true;
			item0.serialize(context);
		}
		context.writeString("</ListingProductDetails>\n");

	}

	public void setListingProductDetails(com.bookshop.soap.ListingProductDetails[] ListingProductDetails){
		this.ListingProductDetails = ListingProductDetails;
	}
	public com.bookshop.soap.ListingProductDetails[] getListingProductDetails(){
		return ListingProductDetails;
	}
}
