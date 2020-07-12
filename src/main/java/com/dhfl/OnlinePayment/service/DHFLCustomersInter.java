package com.dhfl.OnlinePayment.service;

import com.dhfl.OnlinePayment.entity.DHFLCustomersEntity;

public interface DHFLCustomersInter {
	public DHFLCustomersEntity searchByBrLoanCode(String brLoanCode);
	public DHFLCustomersEntity searchByAppNo(String appNo);
	public DHFLCustomersEntity searchByMobileNo(String mobileNo);
}
