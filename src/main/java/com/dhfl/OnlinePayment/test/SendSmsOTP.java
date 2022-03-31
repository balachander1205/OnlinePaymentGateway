package com.dhfl.OnlinePayment.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class SendSmsOTP {
	public static void main(String[] args) {
		sendOtpSms("");
	}
	
	public static String sendOtpSms(String GET_URL) {
		StringBuffer response = new StringBuffer();
		int responseCode = 0;
		try {
			GET_URL = "https://api.equence.in/pushsms?username=dhfl_trans_api"
					+ "&password=glNM_51_&tmplId=1107163662192750779"
					+ "&from=PIRCHF&to=8919180283"
					+ "&text=OTP%20for%20clicktopay-123456,%20Regards%20PCHFL";
			URL obj = new URL(GET_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			responseCode = con.getResponseCode();
			InputStream io = (InputStream) con.getContent();
			System.out.println(io.toString());
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
			System.out.println("Exception@sendOtpSms=" + e.getMessage());
		}
		System.out.println("SMS OTP Url=="+GET_URL);
		return String.valueOf(responseCode);
	}
}
