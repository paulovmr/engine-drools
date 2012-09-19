package com.ruleengine;

public class RuleEngineUtil {

	public static boolean numberIsBetween(int number, int begin, int end) {

		if (number >= begin && number <= end) {
			return true;
		}

		return false;
	}

	public static boolean isEnumeratedIn(Object object, String text) {

		if (object == null || text == null || text.equals("")) {
			return false;
		}

		for (String value : text.split(",")) {
			if (object.toString().trim().toLowerCase().equals(value.trim().toLowerCase())) {
				return true;
			}
		}

		return false;
	}
}
