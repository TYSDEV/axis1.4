package com.bookshop.soap;
/**
 * <p>This class is genarated by the tool WSDL2Ws.
 * It take care of the serialization and the desirialization of
 * the parameter types.
 * except for the serialize(), desirialize() methods it it a bean class.
 * We are specially concerned about the alogorithem used in the desirialize method.
 * If you have any suggestions to improve, your comments are welcome.</p>
 */ 
public class PowerRequest  implements org.apache.axismora.encoding.InOutParameter{
	private java.lang.String power;
	private java.lang.String page;
	private java.lang.String mode;
	private java.lang.String tag;
	private java.lang.String type;
	private java.lang.String devtag;
	private java.lang.String sort;
	private java.lang.String variations;
	private java.lang.String locale;

	public PowerRequest(){
	}
	public PowerRequest(java.lang.String power,java.lang.String page,java.lang.String mode,java.lang.String tag,java.lang.String type,java.lang.String devtag,java.lang.String sort,java.lang.String variations,java.lang.String locale){
		this.power = power;
		this.page = page;
		this.mode = mode;
		this.tag = tag;
		this.type = type;
		this.devtag = devtag;
		this.sort = sort;
		this.variations = variations;
		this.locale = locale;
	}


	public org.apache.axismora.encoding.InParameter desierialize(org.apache.axismora.MessageContext msgdata)throws org.apache.axis.AxisFault{
		int count = 9;
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
				if(localName.equalsIgnoreCase("power")) {
					this.power= new org.apache.axismora.wrappers.simpleType.StringParam(msgdata).getParam();
				}else if (localName.equalsIgnoreCase("page")) {
					this.page= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("mode")) {
					this.mode= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("tag")) {
					this.tag= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("type")) {
					this.type= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("devtag")) {
					this.devtag= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("sort")) {
					this.sort= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("variations")) {
					this.variations= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else if (localName.equalsIgnoreCase("locale")) {
					this.locale= (new org.apache.axismora.wrappers.simpleType.StringParam(msgdata)).getParam();
				}else
					throw new org.apache.axis.AxisFault("unknown tag find "+ localName);
				org.apache.axismora.wsdl2ws.java.ParmWriter.tag = msgdata.getTag();
		}//end of for loop
		return this;

	}

	public void serialize(org.apache.axis.encoding.SerializationContext context)throws java.io.IOException{
		String m_URI ="http://soap.bookshop.com";
		String type_name = "PowerRequest";
			org.apache.axismora.wsdl2ws.java.ParmWriter.tagWritten = false;
		//write the parameters

		context.writeString("<power>");
		if(this.power!=null){
			context.writeString(String.valueOf(power));
		}
		context.writeString("</power>\n");

		context.writeString("<page>");
		if(this.page!=null){
			context.writeString(String.valueOf(page));
		}
		context.writeString("</page>\n");

		context.writeString("<mode>");
		if(this.mode!=null){
			context.writeString(String.valueOf(mode));
		}
		context.writeString("</mode>\n");

		context.writeString("<tag>");
		if(this.tag!=null){
			context.writeString(String.valueOf(tag));
		}
		context.writeString("</tag>\n");

		context.writeString("<type>");
		if(this.type!=null){
			context.writeString(String.valueOf(type));
		}
		context.writeString("</type>\n");

		context.writeString("<devtag>");
		if(this.devtag!=null){
			context.writeString(String.valueOf(devtag));
		}
		context.writeString("</devtag>\n");

		context.writeString("<sort>");
		if(this.sort!=null){
			context.writeString(String.valueOf(sort));
		}
		context.writeString("</sort>\n");

		context.writeString("<variations>");
		if(this.variations!=null){
			context.writeString(String.valueOf(variations));
		}
		context.writeString("</variations>\n");

		context.writeString("<locale>");
		if(this.locale!=null){
			context.writeString(String.valueOf(locale));
		}
		context.writeString("</locale>\n");

	}

	public void setPower(java.lang.String power){
		this.power = power;
	}
	public java.lang.String getPower(){
		return power;
	}
	public void setPage(java.lang.String page){
		this.page = page;
	}
	public java.lang.String getPage(){
		return page;
	}
	public void setMode(java.lang.String mode){
		this.mode = mode;
	}
	public java.lang.String getMode(){
		return mode;
	}
	public void setTag(java.lang.String tag){
		this.tag = tag;
	}
	public java.lang.String getTag(){
		return tag;
	}
	public void setType(java.lang.String type){
		this.type = type;
	}
	public java.lang.String getType(){
		return type;
	}
	public void setDevtag(java.lang.String devtag){
		this.devtag = devtag;
	}
	public java.lang.String getDevtag(){
		return devtag;
	}
	public void setSort(java.lang.String sort){
		this.sort = sort;
	}
	public java.lang.String getSort(){
		return sort;
	}
	public void setVariations(java.lang.String variations){
		this.variations = variations;
	}
	public java.lang.String getVariations(){
		return variations;
	}
	public void setLocale(java.lang.String locale){
		this.locale = locale;
	}
	public java.lang.String getLocale(){
		return locale;
	}
}
