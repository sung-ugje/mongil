/**
 * XStream으로 Marshalling시 Object 클래스가 자동으로 attribute에 생성되는 걸 방지
 */
package com.openapi.framework.wrapper;

import org.springframework.oxm.xstream.XStreamMarshaller;

public class XStreamMarshallerWrapper extends XStreamMarshaller {

	/**
	 * Constructor
	 */
	public XStreamMarshallerWrapper() {
		super.getXStream().aliasSystemAttribute(null, "class");
	}
}
