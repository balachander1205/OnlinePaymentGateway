package com.dhfl.OnlinePayment.pg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dhfl.OnlinePayment.controller.PaymentController;

public class CommonUtil {
	static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	public static String maskString(String strText, int start, int end, char maskChar) throws Exception {
		if (strText == null || strText.equals(""))
			return "";
		if (start < 0)
			start = 0;
		if (end > strText.length())
			end = strText.length();
		if (start > end)
			logger.debug("StringMasking End index cannot be greater than start index");
		int maskLength = end - start;
		if (maskLength == 0)
			return strText;
		StringBuilder sbMaskString = new StringBuilder(maskLength);
		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}
		String maskedStr = strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);;
		logger.debug("Masked String value="+maskedStr);
		return maskedStr;
	}
}
