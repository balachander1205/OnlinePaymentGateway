package com.dhfl.OnlinePayment.repo;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dhfl.OnlinePayment.entity.TransactionDetailsEntity;


@Repository
public interface TransactionDetailsRepo extends CrudRepository<TransactionDetailsEntity, Long>{
	@Modifying
	// DB2 query
	@Query(value = "insert into dhfllive.tbl_transaction_reference_details(txn_status,txn_msg,txn_err_msg,"
			+ "clnt_txn_ref,tpsl_bank_cd,tpsl_txn_id,txn_amt,clnt_rqst_meta,tpsl_txn_time,"
			+ "bal_amt,card_id,alias_name,BankTransactionID,mandate_reg_no,token,hash,"
			+ "app_no, loan_code, customer_name, mobile_number,  clientTxnStatus, payType) "
			+ "values (:txn_status,:txn_msg,:txn_err_msg,:clnt_txn_ref,:tpsl_bank_cd,"
			+ ":tpsl_txn_id,:txn_amt,:clnt_rqst_meta,:tpsl_txn_time,:bal_amt,:card_id,"
			+ ":alias_name,:BankTransactionID,:mandate_reg_no,:token,:hash,:appNo, "
			+ ":loanCode,:custName, :mobile_number,  :clientTxnStatus, :payType)", nativeQuery = true)
	// Mysql Query
	/*@Query(value = "insert into tbl_transaction_reference_details(txn_status,txn_msg,txn_err_msg,"
			+ "clnt_txn_ref,tpsl_bank_cd,tpsl_txn_id,txn_amt,clnt_rqst_meta,tpsl_txn_time,"
			+ "bal_amt,card_id,alias_name,BankTransactionID,mandate_reg_no,token,hash,"
			+ "app_no, loan_code, customer_name, mobile_number, clientTxnStatus, payType"
			+ ") "
			+ "values (:txn_status,:txn_msg,:txn_err_msg,:clnt_txn_ref,:tpsl_bank_cd,"
			+ ":tpsl_txn_id,:txn_amt,:clnt_rqst_meta,:tpsl_txn_time,:bal_amt,:card_id,"
			+ ":alias_name,:BankTransactionID,:mandate_reg_no,:token,:hash,:appNo, "
			+ ":loanCode,:custName, :mobile_number, :clientTxnStatus, :payType)", nativeQuery = true)*/
	@Transactional
	int insertTransactionDetails(@Param("txn_status") String txn_status, @Param("txn_msg") String txn_msg, 
			@Param("txn_err_msg") String txn_err_msg, @Param("clnt_txn_ref") String clnt_txn_ref, 
			@Param("tpsl_bank_cd") String tpsl_bank_cd, @Param("tpsl_txn_id") String tpsl_txn_id, 
			@Param("txn_amt") String txn_amt, @Param("clnt_rqst_meta") String clnt_rqst_meta, 
			@Param("tpsl_txn_time") Date tpsl_txn_time, @Param("bal_amt") String bal_amt, 
			@Param("card_id") String card_id, @Param("alias_name") String alias_name, 
			@Param("BankTransactionID") String BankTransactionID, @Param("mandate_reg_no") String mandate_reg_no,
			@Param("token") String token, @Param("hash") String hash,@Param("appNo") String appNo,
			@Param("loanCode") String  loanCode,@Param("custName") String  custName, 
			@Param("mobile_number") String mobile_number, @Param("clientTxnStatus") String clientTxnStatus,
			@Param("payType") String payType);
	
	@Query("select txnRefData from TransactionDetailsEntity txnRefData where txnRefData.clnt_txn_ref = :clnt_txn_ref")
	TransactionDetailsEntity getTxnReference(@Param("clnt_txn_ref") String clnt_txn_ref);
	
	@Transactional
	@Modifying
	@Query("UPDATE TransactionDetailsEntity set txn_status=:txn_status,txn_msg=:txn_msg,"
			+ "txn_err_msg=:txn_err_msg,tpsl_bank_cd=:tpsl_bank_cd,tpsl_txn_id=:tpsl_txn_id,"
			+ "txn_amt=:txn_amt,clnt_rqst_meta=:clnt_rqst_meta,tpsl_txn_time=:tpsl_txn_time,"
			+ "bal_amt=:bal_amt,card_id=:card_id,alias_name=:alias_name,BankTransactionID=:BankTransactionID,"
			+ "mandate_reg_no=:mandate_reg_no,token=:token,hash=:hash,clientTxnStatus=:clientTxnStatus "
			+ "where clnt_txn_ref=:clnt_txn_ref")
	int updateTxnDetails(@Param("txn_status") String txn_status, @Param("txn_msg") String txn_msg,
			@Param("txn_err_msg") String txn_err_msg,@Param("tpsl_bank_cd") String tpsl_bank_cd, 
			@Param("tpsl_txn_id") String tpsl_txn_id, @Param("txn_amt") String txn_amt, 
			@Param("clnt_rqst_meta") String clnt_rqst_meta,	@Param("tpsl_txn_time") Date tpsl_txn_time, 
			@Param("bal_amt") String bal_amt, @Param("card_id") String card_id, 
			@Param("alias_name") String alias_name, @Param("BankTransactionID") String BankTransactionID, 
			@Param("mandate_reg_no") String mandate_reg_no,@Param("token") String token, 
			@Param("hash") String hash,@Param("clientTxnStatus") String clientTxnStatus,
			@Param("clnt_txn_ref") String clnt_txn_ref);
}
