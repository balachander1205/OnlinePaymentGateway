package com.dhfl.OnlinePayment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePayment.entity.DHFLTransactionRefrence;
import com.dhfl.OnlinePayment.repo.DHFLCustomersRepo;
import com.dhfl.OnlinePayment.repo.DHFLTransactionRefrenceRepo;

@Service
public class DHFLTransactionRefrenceInterImpl implements DHFLTransactionRefrenceInter{
	@Autowired
	DHFLTransactionRefrenceRepo dhflTxnRefRepo;

	@Override
	public int insertTransactionDetails(String clnt_txn_ref, String txn_amt, String clnt_rqst_meta, String app_no,
			String loan_code, String customer_name, Date tpsl_txn_time, String mobile_number, String txn_status, 
			String txn_type, String tpsl_txn_id) {
		int count = dhflTxnRefRepo.insertTransactionRefrence(clnt_txn_ref, txn_amt, clnt_rqst_meta, app_no, loan_code,
				customer_name, tpsl_txn_time, mobile_number, txn_status, txn_type, tpsl_txn_id); 
		return count;
	}

	@Override
	public List<DHFLTransactionRefrence> getAllTransactions() {
		return null;
	}
	
	public DHFLTransactionRefrence getTxnReference(String txnNumber) {
		return dhflTxnRefRepo.getTxnReference(txnNumber);
	}

	@Override
	public int updateTxnStatus(String status, String txnNumber, String tpsl_txn_id) {
		int count = dhflTxnRefRepo.updateTxnStatus(status, txnNumber, tpsl_txn_id);
		return count;
	}
	
}
