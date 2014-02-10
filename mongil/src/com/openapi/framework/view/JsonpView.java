/**
 *
 */
package com.openapi.framework.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.openapi.framework.utils.StringUtil;


public class JsonpView extends MappingJacksonJsonView {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String JSON_OBJECT_NAME = "result";
	private final Map<String, Object> attributeMap = new HashMap<String, Object>();

	public JsonpView(Object obj) {
		attributeMap.put(JSON_OBJECT_NAME, obj);

		super.setContentType("text/html");
		super.setAttributesMap(attributeMap);
	}

	public JsonpView(Object obj, String contentType) {
		attributeMap.put(JSON_OBJECT_NAME, obj);

		super.setContentType(contentType);
		super.setAttributesMap(attributeMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		model = super.getAttributesMap();

		Map<String, String[]> params = req.getParameterMap();

		String callback = com.openapi.framework.utils.StringUtil.htmlSpecialChars(StringUtil.trimToEmpty(params.get("callback")[0]));

		if (params.containsKey("callback")) {
			res.getOutputStream().write(new String(callback + "(").getBytes());
			super.render(model, req, res);
			res.getOutputStream().write(new String(");").getBytes()	);
			res.setContentType(super.getContentType());

		} else {
			super.render(model, req, res);
		}
	}
}
