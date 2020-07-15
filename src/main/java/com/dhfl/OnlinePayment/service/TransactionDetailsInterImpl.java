package com.dhfl.OnlinePayment.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePayment.entity.TransactionDetailsEntity;
import com.dhfl.OnlinePayment.repo.TransactionDetailsRepo;


@Service
public class TransactionDetailsInterImpl implements TransactionDetailsInter{
	
	@Autowired
	TransactionDetailsRepo transDetailsRepo;

	@Override
	public int insertTransactionDetails(String txn_status, String txn_msg,
			String txn_err_msg, String clnt_txn_ref, String tpsl_bank_cd,
			String tpsl_txn_id, String txn_amt, String clnt_rqst_meta,
			Date tpsl_txn_time, String bal_amt, String card_id,
			String alias_name, String BankTransactionID, String mandate_reg_no,
			String token, String hash, String appNo, String loanCode, String custName, 
			String mobile_number, String clientTxnStatus, String payType) {
		int count = transDetailsRepo.insertTransactionDetails(txn_status, txn_msg, 
				txn_err_msg, clnt_txn_ref, tpsl_bank_cd, tpsl_txn_id, txn_amt, 
				clnt_rqst_meta, tpsl_txn_time, bal_amt, card_id, alias_name, 
				BankTransactionID, mandate_reg_no, token, hash, appNo, loanCode, 
				custName, mobile_number, clientTxnStatus, payType);
		return count;
	}

	@Override
	public TransactionDetailsEntity getTxnReference(String txnNumber) {
		TransactionDetailsEntity detailsEntity = transDetailsRepo.getTxnReference(txnNumber);
		return detailsEntity;
	}
	public int updateTxnDetails(String txn_status, String txn_msg,
			String txn_err_msg,String tpsl_bank_cd, 
			String tpsl_txn_id, String txn_amt, 
			String clnt_rqst_meta,Date tpsl_txn_time, 
			String bal_amt, String card_id, 
			String alias_name,  String BankTransactionID, 
			String mandate_reg_no,String token, 
			String hash,String clientTxnStatus,
			String clnt_txn_ref) {
		int count = transDetailsRepo.updateTxnDetails(txn_status, txn_msg, txn_err_msg, tpsl_bank_cd, tpsl_txn_id,
				txn_amt, clnt_rqst_meta, tpsl_txn_time, bal_amt, card_id, alias_name, BankTransactionID, mandate_reg_no,
				token, hash, clientTxnStatus, clnt_txn_ref);
		return count;
	}
	
}
