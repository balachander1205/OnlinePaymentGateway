package com.dhfl.OnlinePayment.pg;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSmsOTP {
	static Logger logger = LoggerFactory.getLogger(SendSmsOTP.class);
	public static String sendOtpSms(String GET_URL) {
		StringBuffer response = new StringBuffer();
		int responseCode = 0;
		try {
			URL obj = new URL(GET_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				/*
				 * BufferedReader in = new BufferedReader(new
				 * InputStreamReader(con.getInputStream())); String inputLine; while ((inputLine
				 * = in.readLine()) != null) { response.append(inputLine); } in.close();
				 */
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			logger.debug("Exception@sendOtpSms=" + e.getMessage());
		}
		logger.debug("SMS OTP Url=="+GET_URL);
		return String.valueOf(responseCode);
	}

	// Generating OTP
	public static String getOtp() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(9999));
		System.out.println("OTP===>>" + id);
		logger.debug("SMS OTP====>>>>>>"+id);
		return id;
	}
}
