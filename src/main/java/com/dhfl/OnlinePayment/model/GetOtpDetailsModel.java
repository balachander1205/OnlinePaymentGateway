package com.dhfl.OnlinePayment.model;

public class GetOtpDetailsModel {
	public String brLoanCode;
	public String applNo;
	public String mobileNumber;
	public String captcha;
	public String search_param;
	
	public String getSearch_param() {
		return search_param;
	}
	public void setSearch_param(String search_param) {
		this.search_param = search_param;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getBrLoanCode() {
		return brLoanCode;
	}
	public void setBrLoanCode(String brLoanCode) {
		this.brLoanCode = brLoanCode;
	}
	public String getApplNo() {
		return applNo;
	}
	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}	
