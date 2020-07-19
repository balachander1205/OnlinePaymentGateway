package com.dhfl.OnlinePayment.service;

import java.util.Date;
import com.dhfl.OnlinePayment.entity.TransactionDetailsEntity;

public interface TransactionDetailsInter {
	public int insertTransactionDetails(String txn_status,String txn_msg,
			String txn_err_msg,String clnt_txn_ref,String tpsl_bank_cd,
			String tpsl_txn_id,String txn_amt,String clnt_rqst_meta,
			Date tpsl_txn_time,String bal_amt,String card_id,
			String alias_name,String BankTransactionID,String mandate_reg_no,
			String token,String hash, String appNo,String loanCode, 
			String custName, String mobile_number, String clientTxnStatus,
			String payType);
	public TransactionDetailsEntity getTxnReference(String txnNumber);
	
	public int updateTxnDetails(String txn_status, String txn_msg, String txn_err_msg, String tpsl_bank_cd,
			String tpsl_txn_id, String txn_amt, String clnt_rqst_meta, Date tpsl_txn_time, String bal_amt,
			String card_id, String alias_name, String BankTransactionID, String mandate_reg_no, String token,
			String hash, String clientTxnStatus, String clnt_txn_ref);
}
