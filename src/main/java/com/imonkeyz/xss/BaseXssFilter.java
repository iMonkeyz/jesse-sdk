package com.imonkeyz.xss;


import org.apache.commons.text.StringEscapeUtils;

import java.util.Iterator;
import java.util.Map;

public abstract class BaseXssFilter<T> {

	public abstract void filter(T t);

	protected void filterMap(Map<String, String> map) {
		if ( null != map ) {
			Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
			while ( ite.hasNext() ) {
				Map.Entry<String, String> e = ite.next();
				e.setValue(StringEscapeUtils.escapeHtml4(e.getValue()));
			}
		}
	}
}
