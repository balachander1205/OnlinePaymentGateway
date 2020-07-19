---- Table2 ------
CREATE TABLE TBL_TRANSACTION_DETAILS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  txn_status VARCHAR(20) DEFAULT NULL,
  txn_msg VARCHAR(500) DEFAULT NULL,
  txn_err_msg VARCHAR(500) DEFAULT NULL,
  clnt_txn_ref VARCHAR(500) DEFAULT NULL,
  tpsl_bank_cd VARCHAR(20) DEFAULT NULL,
  tpsl_txn_id VARCHAR(200) DEFAULT NULL,
  txn_amt VARCHAR(200) DEFAULT NULL,
  clnt_rqst_meta VARCHAR(500) DEFAULT NULL,
  tpsl_txn_time timestamp,
  bal_amt VARCHAR(200) DEFAULT NULL,
  card_id VARCHAR(200) DEFAULT NULL,
  alias_name VARCHAR(200) DEFAULT NULL,
  BankTransactionID VARCHAR(200) DEFAULT NULL,
  mandate_reg_no VARCHAR(200) DEFAULT NULL,
  token VARCHAR(200) DEFAULT NULL,
  hash VARCHAR(500) DEFAULT NULL,
  app_no VARCHAR(20) DEFAULT NULL,
  loan_code VARCHAR(20) DEFAULT NULL,
  customer_name VARCHAR(200) DEFAULT NULL,
  mobile_number VARCHAR(20) DEFAULT NULL
);
-- Table2 --------
CREATE TABLE TBL_TRANSACTION_REFERENCE_DETAILS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  txn_status VARCHAR(20) DEFAULT NULL,
  txn_msg VARCHAR(500) DEFAULT NULL,
  txn_err_msg VARCHAR(500) DEFAULT NULL,
  clnt_txn_ref VARCHAR(500) DEFAULT NULL,
  tpsl_bank_cd VARCHAR(20) DEFAULT NULL,
  tpsl_txn_id VARCHAR(200) DEFAULT NULL,
  txn_amt VARCHAR(200) DEFAULT NULL,
  clnt_rqst_meta VARCHAR(500) DEFAULT NULL,
  tpsl_txn_time timestamp,
  bal_amt VARCHAR(200) DEFAULT NULL,
  card_id VARCHAR(200) DEFAULT NULL,
  alias_name VARCHAR(200) DEFAULT NULL,
  BankTransactionID VARCHAR(200) DEFAULT NULL,
  mandate_reg_no VARCHAR(200) DEFAULT NULL,
  token VARCHAR(200) DEFAULT NULL,
  hash VARCHAR(500) DEFAULT NULL,
  app_no VARCHAR(20) DEFAULT NULL,
  loan_code VARCHAR(20) DEFAULT NULL,
  customer_name VARCHAR(200) DEFAULT NULL,
  mobile_number VARCHAR(20) DEFAULT NULL,
  clientTxnStatus VARCHAR(10) DEFAULT NULL,
  payType VARCHAR(10) DEFAULT NULL
);
select txnRefData from TransactionDetailsEntity txnRefData where txnRefData.clnt_txn_ref = :clnt_txn_ref
CREATE TABLE TBL_TRANSACTION_REFERENCE (
  id INT AUTO_INCREMENT PRIMARY KEY,
  clnt_txn_ref VARCHAR(500) DEFAULT NULL,
  txn_amt VARCHAR(200) DEFAULT NULL,
  clnt_rqst_meta VARCHAR(500) DEFAULT NULL,
  tpsl_txn_time timestamp,
  app_no VARCHAR(20) DEFAULT NULL,
  loan_code VARCHAR(20) DEFAULT NULL,
  customer_name VARCHAR(200) DEFAULT NULL,
  mobile_number VARCHAR(20) DEFAULT NULL,
  txn_status VARCHAR(20) DEFAULT NULL,
  txn_type VARCHAR(20) DEFAULT NULL,
  tpsl_txn_id VARCHAR(20) DEFAULT NULL
);

INSERT INTO `TBL_CUSTOMERS` (`id`, `loancode`, `customerid`, `applicationno`, `createtime`, `mobileno`) VALUES (NULL, '123456789', '123456789', '123456789', CURRENT_TIMESTAMP, '123456789');

-- Customer table creation
-- V1 with blank fields
CREATE TABLE TBL_DHFL_CUSTOMERS(
    id INT AUTO_INCREMENT PRIMARY KEY,
    brloancode VARCHAR(11) NOT NULL UNIQUE,
    applno VARCHAR(8) DEFAULT NULL,
    customername VARCHAR(200) DEFAULT NULL,
    mobileno VARCHAR(20),
    TotalOverdueEMI NUMERIC(12, 0),
    MinimumOverdueAmount NUMERIC(12, 0),
    OverdueBlankField NUMERIC(12, 0),
    TotalChargesAmount NUMERIC(12, 0),
    MinimumChargeAmount NUMERIC(12, 0),
    ChargeBlankField NUMERIC(12, 0)
);

-- V2 with out blank fields
CREATE TABLE TBL_DHFL_CUSTOMERS(
    id INT AUTO_INCREMENT PRIMARY KEY,
    brloancode VARCHAR(11) DEFAULT NULL,
    applno VARCHAR(8) DEFAULT NULL,
    customername VARCHAR(200) DEFAULT NULL,
    mobileno VARCHAR(20),
    TotalOverdueEMI NUMERIC(12, 0),
    MinimumOverdueAmount NUMERIC(12, 0),
    TotalChargesAmount NUMERIC(12, 0),
    MinimumChargeAmount NUMERIC(12, 0)
);


INSERT INTO `tbl_dhfl_customers`(`id`, `brloancode`, `applno`, `customername`, `mobileno`, `TotalOverdueEMI`, 
	`MinimumOverdueAmount`, `OverdueBlankField`, `TotalChargesAmount`, `MinimumChargeAmount`, `ChargeBlankField`) 
VALUES ('','11200000978','00710676','Rohan Dsouza','9820738564','3585','3000','','4585','4000','');

INSERT INTO `tbl_dhfl_customers`(`id`, `brloancode`, `applno`, `customername`, `mobileno`, `TotalOverdueEMI`, 
	`MinimumOverdueAmount`, `OverdueBlankField`, `TotalChargesAmount`, `MinimumChargeAmount`, `ChargeBlankField`) 
VALUES ('','11200001249','00710766','Rohan Dsouza1','9820738564','3585','3000','','4585','4000','');

INSERT INTO `tbl_dhfl_customers`(`id`, `brloancode`, `applno`, `customername`, `mobileno`, `TotalOverdueEMI`, 
	`MinimumOverdueAmount`, `OverdueBlankField`, `TotalChargesAmount`, `MinimumChargeAmount`, `ChargeBlankField`) 
VALUES ('','05000019837','00821389','Rohan Dsouza2','9820738564','3585','3000','','4585','4000','');

INSERT INTO `tbl_dhfl_customers`(`id`, `brloancode`, `applno`, `customername`, `mobileno`, `TotalOverdueEMI`, 
	`MinimumOverdueAmount`, `OverdueBlankField`, `TotalChargesAmount`, `MinimumChargeAmount`, `ChargeBlankField`) 
VALUES ('','05000031623','01445673','Hema Dsouza','8830001623','3585','3000','','4585','4000','');

------------------------- DB2 QUERIES -----------------------------
-------------------------------------------------------------------
CREATE TABLE dhfllive.tbl_dhfl_customers( 
    id integer GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    brloancode VARCHAR(11) DEFAULT NULL,
    applno VARCHAR(8) DEFAULT NULL,
    customername VARCHAR(200) DEFAULT NULL,
    mobileno VARCHAR(20),
    TotalOverdueEMI NUMERIC(12, 0),
    MinimumOverdueAmount NUMERIC(12, 0),
    OverdueBlankField NUMERIC(12, 0),
    TotalChargesAmount NUMERIC(12, 0),
    MinimumChargeAmount NUMERIC(12, 0),
    ChargeBlankField NUMERIC(12, 0)
);
INSERT INTO dhfllive.tbl_dhfl_customers (brloancode, applno,customername, mobileno,TotalOverdueEMI, MinimumOverdueAmount,
OverdueBlankField,TotalChargesAmount,MinimumChargeAmount, ChargeBlankField) VALUES
('11200000978', '00710676', 'Rohan Dsouza', '9820738564', '3585', '3000', '0', '4585', '4000', '0'),
('11200001249', '00710766', 'Rohan Dsouza1', '9820738564', '7130', '7000', '0', '7134', '7000', '0'),
('05000019837', '00821389', 'Rohan Dsouza2', '9820738564', '1424', '1400', '0', '4585', '4000', '0'),
('05000031623', '01445673', 'Hema Dsouza', '8830001623', '3585', '3000', '0', '4585', '4000', '0'),
( '12345678900', '12345678', 'Bala Chander', '9820738564', '1', '1', '0', '0', '0', '0'),
('11200000979', '00710675', 'Rohan Dsouza', '9820738564', '0', '0', '0', '4585', '4000', '0');
----------------------------------
CREATE TABLE dhfllive.tbl_transaction_details (
  id integer GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  txn_status varchar(20) DEFAULT NULL,
  txn_msg varchar(500) DEFAULT NULL,
  txn_err_msg varchar(500) DEFAULT NULL,
  clnt_txn_ref varchar(500) DEFAULT NULL,
  tpsl_bank_cd varchar(20) DEFAULT NULL,
  tpsl_txn_id varchar(200) DEFAULT NULL,
  txn_amt varchar(200) DEFAULT NULL,
  clnt_rqst_meta varchar(500) DEFAULT NULL,
  tpsl_txn_time timestamp NOT NULL DEFAULT,
  bal_amt varchar(200) DEFAULT NULL,
  card_id varchar(200) DEFAULT NULL,
  alias_name varchar(200) DEFAULT NULL,
  BankTransactionID varchar(200) DEFAULT NULL,
  mandate_reg_no varchar(200) DEFAULT NULL,
  token varchar(200) DEFAULT NULL,
  hash varchar(500) DEFAULT NULL,
  app_no varchar(20) DEFAULT NULL,
  loan_code varchar(20) DEFAULT NULL,
  customer_name varchar(200) DEFAULT NULL,
  mobile_number varchar(20) DEFAULT NULL
);
-------------------------------------
CREATE TABLE dhfllive.TBL_TRANSACTION_REFERENCE_DETAILS (
  id integer GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  txn_status VARCHAR(20) DEFAULT NULL,
  txn_msg VARCHAR(500) DEFAULT NULL,
  txn_err_msg VARCHAR(500) DEFAULT NULL,
  clnt_txn_ref VARCHAR(500) DEFAULT NULL,
  tpsl_bank_cd VARCHAR(20) DEFAULT NULL,
  tpsl_txn_id VARCHAR(200) DEFAULT NULL,
  txn_amt VARCHAR(200) DEFAULT NULL,
  clnt_rqst_meta VARCHAR(500) DEFAULT NULL,
  tpsl_txn_time timestamp,
  bal_amt VARCHAR(200) DEFAULT NULL,
  card_id VARCHAR(200) DEFAULT NULL,
  alias_name VARCHAR(200) DEFAULT NULL,
  BankTransactionID VARCHAR(200) DEFAULT NULL,
  mandate_reg_no VARCHAR(200) DEFAULT NULL,
  token VARCHAR(200) DEFAULT NULL,
  hash VARCHAR(500) DEFAULT NULL,
  app_no VARCHAR(20) DEFAULT NULL,
  loan_code VARCHAR(20) DEFAULT NULL,
  customer_name VARCHAR(200) DEFAULT NULL,
  mobile_number VARCHAR(20) DEFAULT NULL,
  clientTxnStatus VARCHAR(10) DEFAULT NULL,
  payType VARCHAR(10) DEFAULT NULL
);
-------------------------------------
CREATE TABLE DHFLLIVE.TBL_TRANSACTION_REFERENCE (
  id integer GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  clnt_txn_ref VARCHAR(500) DEFAULT NULL,
  txn_amt VARCHAR(200) DEFAULT NULL,
  clnt_rqst_meta VARCHAR(500) DEFAULT NULL,
  tpsl_txn_time timestamp,
  app_no VARCHAR(20) DEFAULT NULL,
  loan_code VARCHAR(20) DEFAULT NULL,
  customer_name VARCHAR(200) DEFAULT NULL,
  mobile_number VARCHAR(20) DEFAULT NULL,
  txn_status VARCHAR(20) DEFAULT NULL,
  txn_type VARCHAR(20) DEFAULT NULL,
  tpsl_txn_id VARCHAR(20) DEFAULT NULL
);