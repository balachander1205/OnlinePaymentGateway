package com.dhfl.OnlinePayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePayment.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePayment.repo.DHFLCustomersRepo;

@Service
public class DHFLCustomersInterImpl implements DHFLCustomersInter{
	@Autowired
	DHFLCustomersRepo dhflCustomersRepo;

	@Override
	public DHFLCustomersEntity searchByBrLoanCode(String brLoanCode) {
		return dhflCustomersRepo.searchByBrLoanCode(brLoanCode);
	}

	@Override
	public DHFLCustomersEntity searchByAppNo(String appNo) {
		return dhflCustomersRepo.searchByAppNo(appNo);
	}

	@Override
	public DHFLCustomersEntity searchByMobileNo(String mobileNo) {
		return dhflCustomersRepo.searchByMobileNo(mobileNo);
	}
}
