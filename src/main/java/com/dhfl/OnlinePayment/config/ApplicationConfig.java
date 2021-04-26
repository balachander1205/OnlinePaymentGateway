package com.dhfl.OnlinePayment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@ComponentScan("com.xss.filters")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	@Value("${app1.exchange.name}")
	private String app1Exchange;
	
	@Value("${app1.queue.name}")
	private String app1Queue;
	
	@Value("${app1.routing.key}")
	private String app1RoutingKey;
	
	@Value("${pg.error.code.0300}")
	private String _0300;

	@Value("${pg.error.code.0392}")
	private String _0392;

	@Value("${pg.error.code.0395}")
	private String _0395;

	@Value("${pg.error.code.0396}")
	private String _0396;

	@Value("${pg.error.code.0397}")
	private String _0397;
	
	@Value("${pg.error.code.0399}")
	private String _0399;

	@Value("${pg.error.code.0400}")
	private String _0400;

	@Value("${pg.error.code.0401}")
	private String _0401;

	@Value("${pg.error.code.0402}")
	private String _0402;
	
	@Value("${pg.error.code.0499}")
	private String _0499;

	@Value("${pg.error.code.9999}")
	private String _9999;
	
	@Value("${pg.config.merchant.key}")
	private String merchantKey;
	
	@Value("${pg.config.merchant.iv}")
	private String merchantIv;
	
	@Value("${pg.trans.success.msg}")
	public String tranSuccessMsg;
	
	@Value("${pg.sms.otp.url}")
	public String otpUrl;
	
	@Value("${pg.sms.otp.msg}")
	public String otpMsg;
	
	@Value("${pg.trans.incorrect.amount}")
	public String incorrectAmount;
	
	@Value("${pg.trans.callback}")
	public String callbackUrl;

	@Value("${pg.trans.success}")
	public String successImg;
	
	@Value("${pg.config.merchant.code}")
	public String merchantCode;
	
	@Value("${pg.config.merchant.cur}")
	public String merchantCur;
	
	@Value("${pg.config.merchant.webservice}")
	public String merchantWebServiceUrl;
	
	@Value("${pg.trans.invalid.search.params}")
	public String msgInvalidSearchParams;
	
	@Value("${pg.trans.invalid.otp}")
	public String msgInvalidOtp;
	
	@Value("${pg.sms.otp.unavailable}")
	public String otpUnavailable;
	
	@Value("${pg.trans.invalid.application.number}")
	public String invalidApplNumber;
	
	@Value("${pg.trans.status.image.step1}")
	public String transStep1Image;
	
	@Value("${pg.trans.status.image.step3}")
	public String transStep3Image;
	
	@Value("${pg.trans.invalid.loan.code}")
	public String invalidLoanCode;
	
	@Value("${pg.trans.otp.sent.msg}")
	public String otpSentMessage;
	
	@Value("${pg.app.temp.unavailable}")
	public String pgAppTemporarilyUnavailable;
	
	// PDF document properties
	@Value("${pg.self.help.document.location}")
	public String pdfDocLocation;
	
	@Value("${pg.self.help.document.name}")
	public String pdfDocName;
	
	@Value("${pg.self.help.document.type}")
	public String pdfDocType;

	@Value("${pg.trans.invalid.pay.mode}")
	public String invalidPaymentMode;
	
	@Value("${pg.self.tc.document.name}")
	public String tNCpdfDocName;
	
	@Value("${pg.self.tc.document.location}")
	public String tNCpdfDocLocation;
	
	public String gettNCpdfDocName() {
		return tNCpdfDocName;
	}

	public void settNCpdfDocName(String tNCpdfDocName) {
		this.tNCpdfDocName = tNCpdfDocName;
	}

	public String gettNCpdfDocLocation() {
		return tNCpdfDocLocation;
	}

	public void settNCpdfDocLocation(String tNCpdfDocLocation) {
		this.tNCpdfDocLocation = tNCpdfDocLocation;
	}

	public String getInvalidPaymentMode() {
		return invalidPaymentMode;
	}

	public void setInvalidPaymentMode(String invalidPaymentMode) {
		this.invalidPaymentMode = invalidPaymentMode;
	}

	public String getPdfDocLocation() {
		return pdfDocLocation;
	}

	public void setPdfDocLocation(String pdfDocLocation) {
		this.pdfDocLocation = pdfDocLocation;
	}

	public String getPdfDocName() {
		return pdfDocName;
	}

	public void setPdfDocName(String pdfDocName) {
		this.pdfDocName = pdfDocName;
	}

	public String getPdfDocType() {
		return pdfDocType;
	}

	public void setPdfDocType(String pdfDocType) {
		this.pdfDocType = pdfDocType;
	}

	public String getPgAppTemporarilyUnavailable() {
		return pgAppTemporarilyUnavailable;
	}

	public void setPgAppTemporarilyUnavailable(String pgAppTemporarilyUnavailable) {
		this.pgAppTemporarilyUnavailable = pgAppTemporarilyUnavailable;
	}

	public String getOtpSentMessage() {
		return otpSentMessage;
	}

	public void setOtpSentMessage(String otpSentMessage) {
		this.otpSentMessage = otpSentMessage;
	}

	public String getTransStep1Image() {
		return transStep1Image;
	}

	public void setTransStep1Image(String transStep1Image) {
		this.transStep1Image = transStep1Image;
	}

	public String getTransStep3Image() {
		return transStep3Image;
	}

	public void setTransStep3Image(String transStep3Image) {
		this.transStep3Image = transStep3Image;
	}

	public String getInvalidApplNumber() {
		return invalidApplNumber;
	}

	public void setInvalidApplNumber(String invalidApplNumber) {
		this.invalidApplNumber = invalidApplNumber;
	}

	public String getInvalidLoanCode() {
		return invalidLoanCode;
	}

	public void setInvalidLoanCode(String invalidLoanCode) {
		this.invalidLoanCode = invalidLoanCode;
	}

	public String getOtpUnavailable() {
		return otpUnavailable;
	}

	public void setOtpUnavailable(String otpUnavailable) {
		this.otpUnavailable = otpUnavailable;
	}

	public String getMsgInvalidOtp() {
		return msgInvalidOtp;
	}

	public void setMsgInvalidOtp(String msgInvalidOtp) {
		this.msgInvalidOtp = msgInvalidOtp;
	}

	public String getMsgInvalidSearchParams() {
		return msgInvalidSearchParams;
	}

	public void setMsgInvalidSearchParams(String msgInvalidSearchParams) {
		this.msgInvalidSearchParams = msgInvalidSearchParams;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantCur() {
		return merchantCur;
	}

	public void setMerchantCur(String merchantCur) {
		this.merchantCur = merchantCur;
	}

	public String getMerchantWebServiceUrl() {
		return merchantWebServiceUrl;
	}

	public void setMerchantWebServiceUrl(String merchantWebServiceUrl) {
		this.merchantWebServiceUrl = merchantWebServiceUrl;
	}

	@Value("${pg.trans.failure}")
	public String failureImg;
	
	public String getSuccessImg() {
		return successImg;
	}

	public void setSuccessImg(String successImg) {
		this.successImg = successImg;
	}

	public String getFailureImg() {
		return failureImg;
	}

	public void setFailureImg(String failureImg) {
		this.failureImg = failureImg;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getIncorrectAmount() {
		return incorrectAmount;
	}

	public void setIncorrectAmount(String incorrectAmount) {
		this.incorrectAmount = incorrectAmount;
	}

	public String getOtpUrl() {
		return otpUrl;
	}

	public void setOtpUrl(String otpUrl) {
		this.otpUrl = otpUrl;
	}

	public String getOtpMsg() {
		return otpMsg;
	}

	public void setOtpMsg(String otpMsg) {
		this.otpMsg = otpMsg;
	}

	public String getTranSuccessMsg() {
		return tranSuccessMsg;
	}

	public void setTranSuccessMsg(String tranSuccessMsg) {
		this.tranSuccessMsg = tranSuccessMsg;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getMerchantIv() {
		return merchantIv;
	}

	public void setMerchantIv(String merchantIv) {
		this.merchantIv = merchantIv;
	}
	
	public String get_0399() {
		return _0399;
	}

	public void set_0399(String _0399) {
		this._0399 = _0399;
	}

	public String get_0300() {
		return _0300;
	}

	public void set_0300(String _0300) {
		this._0300 = _0300;
	}

	public String get_0392() {
		return _0392;
	}

	public void set_0392(String _0392) {
		this._0392 = _0392;
	}

	public String get_0395() {
		return _0395;
	}

	public void set_0395(String _0395) {
		this._0395 = _0395;
	}

	public String get_0396() {
		return _0396;
	}

	public void set_0396(String _0396) {
		this._0396 = _0396;
	}

	public String get_0397() {
		return _0397;
	}

	public void set_0397(String _0397) {
		this._0397 = _0397;
	}

	public String get_0400() {
		return _0400;
	}

	public void set_0400(String _0400) {
		this._0400 = _0400;
	}

	public String get_0401() {
		return _0401;
	}

	public void set_0401(String _0401) {
		this._0401 = _0401;
	}

	public String get_0402() {
		return _0402;
	}

	public void set_0402(String _0402) {
		this._0402 = _0402;
	}

	public String get_0499() {
		return _0499;
	}

	public void set_0499(String _0499) {
		this._0499 = _0499;
	}

	public String get_9999() {
		return _9999;
	}

	public void set_9999(String _9999) {
		this._9999 = _9999;
	}

	public String getApp1Exchange() {
		return app1Exchange;
	}
	
	public void setApp1Exchange(String app1Exchange) {
		this.app1Exchange = app1Exchange;
	}

	public String getApp1Queue() {
		return app1Queue;
	}

	public void setApp1Queue(String app1Queue) {
		this.app1Queue = app1Queue;
	}

	public String getApp1RoutingKey() {
		return app1RoutingKey;
	}

	public void setApp1RoutingKey(String app1RoutingKey) {
		this.app1RoutingKey = app1RoutingKey;
	}
	
	
	
// All getters and setters
}
