package com.dhfl.OnlinePayment.model;

public class DoPaymentModel {
	public String amount_to_pay;
	public String loan_code;
	public String cust_id;
	public String app_no;
	public String amount;
	public String MinimumOverdueAmount;
	public String TotalOverdueEMI; 
	public String mobile_no;
	
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getMinimumOverdueAmount() {
		return MinimumOverdueAmount;
	}
	public void setMinimumOverdueAmount(String minimumOverdueAmount) {
		MinimumOverdueAmount = minimumOverdueAmount;
	}
	public String getTotalOverdueEMI() {
		return TotalOverdueEMI;
	}
	public void setTotalOverdueEMI(String totalOverdueEMI) {
		TotalOverdueEMI = totalOverdueEMI;
	}
	public String getAmount_to_pay() {
		return amount_to_pay;
	}
	public void setAmount_to_pay(String amount_to_pay) {
		this.amount_to_pay = amount_to_pay;
	}
	public String getLoan_code() {
		return loan_code;
	}
	public void setLoan_code(String loan_code) {
		this.loan_code = loan_code;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
}
