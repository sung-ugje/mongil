
package com.openapi.framework.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


public class JsonView extends MappingJacksonJsonView {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String JSON_OBJECT_NAME = "result";
	private final Map<String, Object> attributeMap = new HashMap<String, Object>();

	public JsonView(Object obj) {
		attributeMap.put(JSON_OBJECT_NAME, obj);

		super.setContentType("text/html");
		super.setAttributesMap(attributeMap);
	}

	public JsonView(Object obj, String contentType) {
		attributeMap.put(JSON_OBJECT_NAME, obj);

		super.setContentType(contentType);
		super.setAttributesMap(attributeMap);
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		model = super.getAttributesMap();
		super.render(model, req, res);
		res.setContentType(super.getContentType());
	}
}
