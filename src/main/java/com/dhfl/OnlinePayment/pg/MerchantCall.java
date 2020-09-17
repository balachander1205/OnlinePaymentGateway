package com.dhfl.OnlinePayment.pg;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tp.pg.util.TransactionRequestBean;
import com.tp.pg.util.TransactionResponseBean;

public class MerchantCall {
	private static Logger logger = LoggerFactory.getLogger(MerchantCall.class);
	public static void main(String[] args) {
		String key="3330290559QVHYCU";
		String iv="8168854229WUSGEL";
		String originalMessage = "S0QVwfvP9Q+5vAvihEsw4YEWUa9JgkaVUyBVNkRMOOxOfOCrIQy1AklVKxTIXGajktRClCBPAeKa\r\n" + 
				"rDmJ3lHVQ9/57w9nQA4y5BxqYMblQXuH03OysBD8q0j7RpnjRJUdPLH+b/xl7tfZFZc3Rq3kwhRT\r\n" + 
				"128Jy13jISjgrfg/8XcyAqnmxjnR94n49HXtIXIZ7L91DDDkGxREwE+k0PthFaOpl3w92YEpfopd\r\n" + 
				"Z3Qzav7h1cIi4hdLecxkLH63QKIidbSzqP3gyuzr7CWLYjmnZVnFi4FUC/OWmCfJtlRK65c4Thqb\r\n" + 
				"ZawGXTX+f5zjtD6ZlYxOt0mw35JKRYb6hd8b0pK2jVeB1pyx54xkvHUFxYl/YeO9LMnrM7sddy68\r\n" + 
				"wDOyWPfQu9oK4WGvvwbCihvIehDqpZFKRn6jKgIPOKOVeNr/BjvajcXeQGM2hFx18DrOGEzDbEfq\r\n" + 
				"EsjrqxhgyXZrJ1HBsXkX3kizgd3k3n4m37L1SVlETfvoXiybJyXvy9DIwQeyWs0nw0rWB+invHOb\r\n" + 
				"CQ==";
		doDecrypt(originalMessage, key, iv);
		//getTxNdetails();
		//merchantCall();
	}
	public static String doMerchantCall(String mobileNo, String amount, 
			String key, String iv, String customerName, String loanCode, 
			String callbackUrl, String merchantCode, String merchantWebServiceURl,
			String merchantCur, String txnNumber) {
		long CURR_TMIES = System.currentTimeMillis();
		//String txnNumber = "TXN"+loanCode+String.valueOf(CURR_TMIES);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date txnDt = new Date(CURR_TMIES);
		String txnDate = dateFormat.format(txnDt);
		
		logger.info("Transaction Reference Number="+txnNumber);
		logger.info("Transaction Mobile=" + mobileNo + "|Loan Code=" + loanCode + "|Customer Name=" + customerName
				+ "|TxnAmount=" + amount + "|MarchentCode=" + merchantCode+"|webServiceUrl="+merchantWebServiceURl);
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
		objTransactionRequestBean.setStrRequestType("T");
		objTransactionRequestBean.setStrMerchantCode(merchantCode);
		objTransactionRequestBean.setMerchantTxnRefNumber(txnNumber);
		// TXN0052134656
		objTransactionRequestBean.setStrAmount(amount);
		objTransactionRequestBean.setStrCurrencyCode("INR");
		objTransactionRequestBean.setStrITC(loanCode);
		//objTransactionRequestBean.setStrReturnURL("https://www.tekprocess.co.in/MerchantIntegrationClient/Responsepayload.jsp");
		objTransactionRequestBean.setStrReturnURL(callbackUrl);
		objTransactionRequestBean.setStrS2SReturnURL(callbackUrl);
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate(txnDate);
		//objTransactionRequestBean.setStrBankCode("");
		objTransactionRequestBean.setWebServiceLocator(merchantWebServiceURl);
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber(mobileNo);
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName(customerName);
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		String token = objTransactionRequestBean.getTransactionToken();
	    logger.info("TxnDate="+txnDate+" TxnNumber="+txnNumber+" Payment Token="+token); 
	    return token;
	}
	
	public static String doDecrypt(String originalmsg, String key, String iv) {
		JSONObject resObj = new JSONObject();
		String resrString = "";
		try {
			TransactionResponseBean beanObj = new TransactionResponseBean();
			beanObj.setIv(iv.getBytes());
			beanObj.setKey(key.getBytes());
			beanObj.setResponsePayload(originalmsg);
			resrString = beanObj.getResponsePayload();
			System.out.println(" resrString :" + resrString);
			if(resrString.contains("|")) {
				String[] response = resrString.split("\\|");
				for(String msg : response) {
					String[] msgArr = msg.split("=");
					try {
						if(msgArr.length>=1) {
							resObj.put(msgArr[0], msgArr[1]);
						}
					}catch (Exception e) {
						resObj.put(msgArr[0], "");
					}					
				}
			}
			System.out.println(resObj);
		}catch (Exception e) {
			System.out.println("Exception@doDecrypt() "+e);
		}
		return resObj.toString();
	}
	
	public static String merchantCall() {
		String key="3330290559QVHYCU";
		String iv="8168854229WUSGEL";
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
		objTransactionRequestBean.setStrRequestType("T");
		objTransactionRequestBean.setStrMerchantCode("T519384");
		objTransactionRequestBean.setMerchantTxnRefNumber("TXN0052134656");
		objTransactionRequestBean.setStrAmount("1.00");
		objTransactionRequestBean.setStrCurrencyCode("INR");
		//objTransactionRequestBean.setStrITC("");
		//objTransactionRequestBean.setStrReturnURL("https://www.tekprocess.co.in/MerchantIntegrationClient/Responsepayload.jsp");
		objTransactionRequestBean.setStrReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrS2SReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate("20-01-2020");
		//objTransactionRequestBean.setStrBankCode("");
		objTransactionRequestBean.setWebServiceLocator("https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew");
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber("9999999999");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("xyz");
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		String token = objTransactionRequestBean.getTransactionToken();
	    System.out.println("Token :--->> "+token.length() ); 
	    return token;
	}
	
	public static String getTxNdetails() {
		String key="3330290559QVHYCU";
		String iv="8168854229WUSGEL";
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
		objTransactionRequestBean.setStrRequestType("O");
		objTransactionRequestBean.setStrMerchantCode("T519384");
		objTransactionRequestBean.setMerchantTxnRefNumber("TXN112000009781594541927978");
		objTransactionRequestBean.setStrAmount("1.00");
		objTransactionRequestBean.setStrCurrencyCode("INR");
		//objTransactionRequestBean.setStrITC("");
		//objTransactionRequestBean.setStrReturnURL("https://www.tekprocess.co.in/MerchantIntegrationClient/Responsepayload.jsp");
		objTransactionRequestBean.setStrReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrS2SReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate("20-01-2020");
		//objTransactionRequestBean.setStrBankCode("");
		objTransactionRequestBean.setWebServiceLocator("https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew");
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("1165219539");
		objTransactionRequestBean.setStrMobileNumber("9999999999");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("xyz");
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		String token = objTransactionRequestBean.getTransactionToken();
	    System.out.println("Token :--->> "+token.length() ); 
	    return token;
	}

}