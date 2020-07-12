package com.dhfl.OnlinePayment.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dhfl.OnlinePayment.entity.DHFLTransactionRefrence;

public interface DHFLTransactionRefrenceInter {
	public int insertTransactionDetails(String clnt_txn_ref, 
			String txn_amt, String clnt_rqst_meta, 
			String app_no,	String loan_code, 
			String customer_name, Date tpsl_txn_time, 
			String mobile_number, String txn_status, 
			String txn_type, String tpsl_txn_id);

	public List<DHFLTransactionRefrence> getAllTransactions();
	
	public DHFLTransactionRefrence getTxnReference(String txnNumber);
	
	public int updateTxnStatus(String status, String txnNumber, String tpsl_txn_id);
}
