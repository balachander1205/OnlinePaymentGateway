package com.dhfl.OnlinePayment.repo;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhfl.OnlinePayment.entity.DHFLTransactionRefrence;

@Repository
public interface DHFLTransactionRefrenceRepo extends CrudRepository<DHFLTransactionRefrence, Long>{
	@Modifying
	@Query(value = "insert into dhfllive.tbl_transaction_reference(clnt_txn_ref, txn_amt, clnt_rqst_meta, tpsl_txn_time, app_no,"
			+ "loan_code, customer_name, mobile_number, txn_status, txn_type, tpsl_txn_id)"
			+ "values (:clnt_txn_ref, :txn_amt, :clnt_rqst_meta, :tpsl_txn_time, :app_no, :loan_code, :customer_name, :mobile_number,"
			+ ":txn_status, :txn_type, :tpsl_txn_id)", nativeQuery = true)
	/*@Query(value = "insert into tbl_transaction_reference(clnt_txn_ref, txn_amt, clnt_rqst_meta, tpsl_txn_time, app_no,"
			+ "loan_code, customer_name, mobile_number, txn_status, txn_type, tpsl_txn_id)"
			+ "values (:clnt_txn_ref, :txn_amt, :clnt_rqst_meta, :tpsl_txn_time, :app_no, :loan_code, :customer_name, :mobile_number,"
			+ ":txn_status, :txn_type, :tpsl_txn_id)", nativeQuery = true)*/
	@Transactional
	int insertTransactionRefrence(@Param("clnt_txn_ref") String clnt_txn_ref, 
			@Param("txn_amt") String txn_amt, @Param("clnt_rqst_meta") String clnt_rqst_meta, 
			@Param("app_no") String app_no,	@Param("loan_code") String loan_code, 
			@Param("customer_name") String customer_name, @Param("tpsl_txn_time") Date tpsl_txn_time, 
			@Param("mobile_number") String mobile_number, @Param("txn_status") String txn_status, 
			@Param("txn_type") String txn_type, @Param("tpsl_txn_id") String tpsl_txn_id);
	
	@SuppressWarnings("unchecked")
	DHFLTransactionRefrence save(DHFLTransactionRefrence trans);
	
	@Query("select txnRefData from DHFLTransactionRefrence txnRefData where txnRefData.clntTxnRef = :clnt_txn_ref")
	DHFLTransactionRefrence getTxnReference(@Param("clnt_txn_ref") String clnt_txn_ref);
	
	@Modifying
	@Transactional
	@Query("update DHFLTransactionRefrence set txnStatus=:status, tpslTxnId=:tpsl_txn_id where clntTxnRef = :clnt_txn_ref")
	int updateTxnStatus(@Param("status") String status, @Param("clnt_txn_ref") String clnt_txn_ref, 
			@Param("tpsl_txn_id") String tpsl_txn_id);
}
