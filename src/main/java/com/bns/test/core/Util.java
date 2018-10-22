package com.bns.test.core;

import org.apache.commons.lang3.StringUtils;

public class Util {
	public static final String getShortName(String text) {
		assert StringUtils.isNotBlank(text);
		
		if(!text.contains(" ") && ! text.contains(".")) {
			return text;
		}
		
		StringBuilder sb = new StringBuilder();
		// if it's too short, no need to do the lead char thing, just replace the space to '.'
		if(text.length() < 15) {
			for (String s : text.split(" ")) {
				sb.append(s).append(".");
			}
		}
		else {
			text = text.replaceAll("[.,]", " "); // Replace dots, etc (optional)
			for (String s : text.split(" ")) {
				sb.append(s.charAt(0)).append(".");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
}
