package com.bookshop.soap;
/**
 * <p>This class is genarated by the tool WSDL2Ws.
 * It take care of the serialization and the desirialization of
 * the parameter types.
 * except for the serialize(), desirialize() methods it it a bean class.
 * We are specially concerned about the alogorithem used in the desirialize method.
 * If you have any suggestions to improve, your comments are welcome.</p>
 */ 
public class MarketplaceSearchDetails  implements org.apache.axismora.encoding.InOutParameter{
	private java.lang.String NumberOfOpenListings;
	private com.bookshop.soap.ListingProductInfo ListingProductInfo;

	public MarketplaceSearchDetails(){
	}
	public MarketplaceSearchDetails(java.lang.String NumberOfOpenListings,com.bookshop.soap.ListingProductInfo ListingProductInfo){
		this.NumberOfOpenListings = NumberOfOpenListings;
		this.ListingProductInfo = ListingProductInfo;
	}


	public org.apache.axismora.encoding.InParameter desierialize(org.apache.axismora.MessageContext msgdata)throws org.apache.axis.AxisFault{
		int count = 2;
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
				if(localName.equalsIgnoreCase("NumberOfOpenListings")) {
					this.NumberOfOpenListings= new org.apache.axismora.wrappers.simpleType.StringParam(msgdata).getParam();
				}else if (localName.equalsIgnoreCase("ListingProductInfo")) {
					this.ListingProductInfo= (new com.bookshop.soap.ListingProductInfo());
					this.ListingProductInfo.desierialize(msgdata);
				}else
					throw new org.apache.axis.AxisFault("unknown tag find "+ localName);
				org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
		}//end of for loop
		return this;

	}

	public void serialize(org.apache.axis.encoding.SerializationContext context)throws java.io.IOException{
		String m_URI ="http://soap.bookshop.com";
		String type_name = "MarketplaceSearchDetails";
			org.apache.axismora.wsdl2ws.java.ParmWriter.tagWritten = false;
		//write the parameters

		context.writeString("<NumberOfOpenListings>");
		if(this.NumberOfOpenListings!=null){
			context.writeString(String.valueOf(NumberOfOpenListings));
		}
		context.writeString("</NumberOfOpenListings>\n");

		context.writeString("<ListingProductInfo>");
		if(this.ListingProductInfo!=null){
			org.apache.axismora.wsdl2ws.java.ParmWriter.tagWritten = true;
			ListingProductInfo.serialize(context);
		}
		context.writeString("</ListingProductInfo>\n");

	}

	public void setNumberOfOpenListings(java.lang.String NumberOfOpenListings){
		this.NumberOfOpenListings = NumberOfOpenListings;
	}
	public java.lang.String getNumberOfOpenListings(){
		return NumberOfOpenListings;
	}
	public void setListingProductInfo(com.bookshop.soap.ListingProductInfo ListingProductInfo){
		this.ListingProductInfo = ListingProductInfo;
	}
	public com.bookshop.soap.ListingProductInfo getListingProductInfo(){
		return ListingProductInfo;
	}
}
