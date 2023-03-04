package com.cg.utils.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * FreeMarkers工具类
 * 
 * @author ThinkGem
 * @version 2013-01-15
 */
public class FreeMarkers {


	public static String renderTemplate(Template template, Object model) throws TemplateException, IOException {

		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

}
