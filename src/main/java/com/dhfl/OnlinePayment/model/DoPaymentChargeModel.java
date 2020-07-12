package com.dhfl.OnlinePayment.model;

public class DoPaymentChargeModel {
	public String amount_to_pay1;
	public String loan_code1;
	public String cust_id1;
	public String app_no1;
	public String amount1;
	public String amount_to_pay_charge;
	public String mobile_no;
	public String MinimumChargeAmount;
	public String TotalChargesAmount;
		
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getMinimumChargeAmount() {
		return MinimumChargeAmount;
	}
	public void setMinimumChargeAmount(String minimumChargeAmount) {
		MinimumChargeAmount = minimumChargeAmount;
	}
	public String getTotalChargesAmount() {
		return TotalChargesAmount;
	}
	public void setTotalChargesAmount(String totalChargesAmount) {
		TotalChargesAmount = totalChargesAmount;
	}
	public String getAmount_to_pay_charge() {
		return amount_to_pay_charge;
	}
	public void setAmount_to_pay_charge(String amount_to_pay_charge) {
		this.amount_to_pay_charge = amount_to_pay_charge;
	}
	public String getAmount_to_pay1() {
		return amount_to_pay1;
	}
	public void setAmount_to_pay1(String amount_to_pay1) {
		this.amount_to_pay1 = amount_to_pay1;
	}
	public String getLoan_code1() {
		return loan_code1;
	}
	public void setLoan_code1(String loan_code1) {
		this.loan_code1 = loan_code1;
	}
	public String getCust_id1() {
		return cust_id1;
	}
	public void setCust_id1(String cust_id1) {
		this.cust_id1 = cust_id1;
	}
	public String getApp_no1() {
		return app_no1;
	}
	public void setApp_no1(String app_no1) {
		this.app_no1 = app_no1;
	}
	public String getAmount1() {
		return amount1;
	}
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}
	
}
