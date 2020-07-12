package com.dhfl.OnlinePayment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@Table(name = "tbl_transaction_reference", schema="DHFLLIVE")
@Table(name = "tbl_transaction_reference")
public class DHFLTransactionRefrence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "loan_code")
	private String brloancode;
	
	@Column(name = "app_no")
	private String applno;
	
	@Column(name = "customer_name")
	private String customername;
	
	@Column(name = "mobile_number")
	private String mobileno;
	
	@Column(name = "clnt_txn_ref")
	private String clntTxnRef;
	
	@Column(name = "txn_amt")
	private Long txnAmt;
	
	@Column(name = "clnt_rqst_meta")
	private String clntRqstMeta;
	
	@Column(name = "tpsl_txn_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tpsl_txn_time;
	
	@Column(name = "txn_status")
	private String txnStatus;
	
	@Column(name = "txn_type")
	private String txnType;
	
	@Column(name = "tpsl_txn_id")
	private String tpslTxnId;
	
	public String getTpslTxnId() {
		return tpslTxnId;
	}

	public void setTpslTxnId(String tpslTxnId) {
		this.tpslTxnId = tpslTxnId;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrloancode() {
		return brloancode;
	}

	public void setBrloancode(String brloancode) {
		this.brloancode = brloancode;
	}

	public String getApplno() {
		return applno;
	}

	public void setApplno(String applno) {
		this.applno = applno;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getClntTxnRef() {
		return clntTxnRef;
	}

	public void setClntTxnRef(String clntTxnRef) {
		this.clntTxnRef = clntTxnRef;
	}

	public Long getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(Long txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getClntRqstMeta() {
		return clntRqstMeta;
	}

	public void setClntRqstMeta(String clntRqstMeta) {
		this.clntRqstMeta = clntRqstMeta;
	}

	public Date getTpsl_txn_time() {
		return tpsl_txn_time;
	}

	public void setTpsl_txn_time(Date tpsl_txn_time) {
		this.tpsl_txn_time = tpsl_txn_time;
	}
	
}
