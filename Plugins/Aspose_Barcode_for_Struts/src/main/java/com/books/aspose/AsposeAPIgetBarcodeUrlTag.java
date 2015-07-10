package com.books.aspose;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Created by Adeel Ilyas on 7/6/2015.
 */
/*
 * @author: Adeel Ilyas Company: Aspose Pty Ltd.
 */
public class AsposeAPIgetBarcodeUrlTag extends SimpleTagSupport implements
		AsposeAPIConfiguration {

	private String symbology;
	private String billamount;
	private String varName;
	private String scopeName;

	public void setSymbology(String symbology) {
		this.symbology = symbology;
	}

	public void setBillAmount(String billamount) {
		this.billamount = billamount;
	}

	public void setVar(String value) {
		this.varName = value;
	}

	public void setScope(String value) {
		this.scopeName = value;
	}

	public void doTag() throws JspException {
		try {
			ScopedContext scopedContext = (this.scopeName == null) ? ScopedContext.PAGE
					: ScopedContext.getInstance(this.scopeName);
			Object constantValue = getSymbologyValue(BarcodeTypeConstantClass
					+ "." + this.symbology);
			constantValue = BarcodeServiceURL +"?billAmount="+ billamount + "&symbology="+constantValue;
			PageContext pageContext = (PageContext)getJspContext();
		
			String webContext=pageContext.getServletContext().getContextPath();
			ServletRequest servletRequest = pageContext.getRequest();
			String serviceUrl = servletRequest.getScheme()+"://"+servletRequest.getServerName()+":"+servletRequest.getServerPort()+webContext;
			
			getJspContext().setAttribute(this.varName, serviceUrl+constantValue,
					scopedContext.getValue());
		} catch (Exception e) {
			throw new JspException("Exception setting constant "
					+ this.symbology, e);
		}
	}

	public Object getSymbologyValue(String Symbology)
			throws IllegalAccessException, InstantiationException,
			ClassNotFoundException, NoSuchFieldException {
		Field field;
		FieldPathParser parser = new FieldPathParser(Symbology);
		field = Class.forName(parser.getDeclaringClassName()).getField(
				parser.getFieldName());
		if ((!Modifier.isPublic(field.getModifiers()))
				|| (!Modifier.isStatic(field.getModifiers()))
				|| (!Modifier.isFinal(field.getModifiers()))) {
			throw new IllegalArgumentException(Symbology
					+ " is not a public static final member");
		}

		return field.get(null);
	}

}