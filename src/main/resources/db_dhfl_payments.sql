-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2020 at 01:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_dhfl_payments`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_dhfl_customers`
--

CREATE TABLE `tbl_dhfl_customers` (
  `id` int(11) NOT NULL,
  `brloancode` varchar(11) DEFAULT NULL,
  `applno` varchar(8) DEFAULT NULL,
  `customername` varchar(200) DEFAULT NULL,
  `mobileno` varchar(20) DEFAULT NULL,
  `TotalOverdueEMI` decimal(12,0) DEFAULT NULL,
  `MinimumOverdueAmount` decimal(12,0) DEFAULT NULL,
  `OverdueBlankField` decimal(12,0) DEFAULT NULL,
  `TotalChargesAmount` decimal(12,0) DEFAULT NULL,
  `MinimumChargeAmount` decimal(12,0) DEFAULT NULL,
  `ChargeBlankField` decimal(12,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_dhfl_customers`
--

INSERT INTO `tbl_dhfl_customers` (`id`, `brloancode`, `applno`, `customername`, `mobileno`, `TotalOverdueEMI`, `MinimumOverdueAmount`, `OverdueBlankField`, `TotalChargesAmount`, `MinimumChargeAmount`, `ChargeBlankField`) VALUES
(1, '11200000978', '00710676', 'Rohan Dsouza', '9820738564', '3585', '3000', '0', '4585', '4000', '0'),
(2, '11200001249', '00710766', 'Rohan Dsouza1', '9820738564', '7130', '7000', '0', '7134', '7000', '0'),
(3, '05000019837', '00821389', 'Rohan Dsouza2', '9820738564', '1424', '1400', '0', '4585', '4000', '0'),
(4, '05000031623', '01445673', 'Hema Dsouza', '8830001623', '3585', '3000', '0', '4585', '4000', '0'),
(5, '12345678900', '12345678', 'Bala Chander', '9820738564', '1', '1', '0', '0', '0', '0'),
(6, '11200000979', '00710675', 'Rohan Dsouza', '9820738564', '0', '0', '0', '4585', '4000', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaction_details`
--

CREATE TABLE `tbl_transaction_details` (
  `id` int(11) NOT NULL,
  `txn_status` varchar(20) DEFAULT NULL,
  `txn_msg` varchar(500) DEFAULT NULL,
  `txn_err_msg` varchar(500) DEFAULT NULL,
  `clnt_txn_ref` varchar(500) DEFAULT NULL,
  `tpsl_bank_cd` varchar(20) DEFAULT NULL,
  `tpsl_txn_id` varchar(200) DEFAULT NULL,
  `txn_amt` varchar(200) DEFAULT NULL,
  `clnt_rqst_meta` varchar(500) DEFAULT NULL,
  `tpsl_txn_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `bal_amt` varchar(200) DEFAULT NULL,
  `card_id` varchar(200) DEFAULT NULL,
  `alias_name` varchar(200) DEFAULT NULL,
  `BankTransactionID` varchar(200) DEFAULT NULL,
  `mandate_reg_no` varchar(200) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `hash` varchar(500) DEFAULT NULL,
  `app_no` varchar(20) DEFAULT NULL,
  `loan_code` varchar(20) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_transaction_details`
--

INSERT INTO `tbl_transaction_details` (`id`, `txn_status`, `txn_msg`, `txn_err_msg`, `clnt_txn_ref`, `tpsl_bank_cd`, `tpsl_txn_id`, `txn_amt`, `clnt_rqst_meta`, `tpsl_txn_time`, `bal_amt`, `card_id`, `alias_name`, `BankTransactionID`, `mandate_reg_no`, `token`, `hash`, `app_no`, `loan_code`, `customer_name`, `mobile_number`) VALUES
(1, '0300', 'success', 'NA', 'TXN112000009781594192156092', '470', '1161043536', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-07 13:39:36', 'NA', '1161043536', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161043536', '', 'fee1a2d4-e425-4d05-a2d4-10011bf30a5f', '49fd7074b5b36bdac43ccaa0e20d8c6795adac4d', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(2, '0399', 'failure', '', 'TXN112000009781594192536956', '470', '1161051743', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-07 13:46:00', 'NA', '1161051743', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161051743', '', '8fb7ed2e-531a-4426-b76d-cdbf4d69f2c8', 'aca994b3b0e595c5c6c208b8273b7e8d0e1a6c29', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(3, '0300', 'success', 'NA', 'TXN112000009781594193740968', '470', '1161078091', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-08 02:06:04', 'NA', '1161078091', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161078091', '', 'edeed91e-3c8b-4ffe-849c-0b4ce59ad7f5', '1a10ca4719e5619c9cafd2682f8dcbdc0a65c005', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(4, '0300', 'success', 'NA', 'TXN112000009781594194152423', '470', '1161087110', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-08 02:13:11', 'NA', '1161087110', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161087110', '', '76a3ef22-3fea-402c-9b4a-2c1fb1af8194', '3ef075ba4574ee620073fa207185a8160cfd03f6', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(5, '0300', 'success', 'NA', 'TXN112000009781594195136911', '470', '1161108318', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-08 02:29:22', 'NA', '1161108318', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161108318', '', 'f95e9982-4cbe-4c0b-adcd-d41831822493', '3e2c3161fc5c6a7403a5f6870abe5805604d51ba', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(6, '0300', 'success', 'NA', 'TXN112000009781594207473884', '470', '1161349478', '1.00', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '2020-07-08 05:55:34', 'NA', '1161349478', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '1161349478', '', 'fac835f5-1e5e-4203-b451-8d9ae6073dca', '01e42c4d351d4d1584248a3304680f404ea46de2', '00710676', '11200000978', 'Rohan Dsouza', '9820738564'),
(7, '0399', 'failure', '', 'TXN112000009781594207787367', '470', '1161354295', '1.00', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '2020-07-08 06:00:05', 'NA', '1161354295', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '1161354295', '', 'db2f0871-daf0-49a9-81cd-43c6c27dd943', '0a6ce5e10da8d3664654688b17100fcdb6c0395a', '00710676', '11200000978', 'Rohan Dsouza', '9820738564'),
(8, '0300', 'success', 'NA', 'TXN112000009781594212062791', '470', '1161427208', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-08 07:11:22', 'NA', '1161427208', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161427208', '', '4045f0d8-2445-412b-9e68-f35d4f5961b8', '331fbf233e17464f9840dffffca0802374a92dc4', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(9, '0300', 'success', 'NA', 'TXN112000009781594212062791', '470', '1161427208', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-08 07:11:22', 'NA', '1161427208', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1161427208', '', '4045f0d8-2445-412b-9e68-f35d4f5961b8', '331fbf233e17464f9840dffffca0802374a92dc4', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(10, '0300', 'success', 'NA', 'TXN123456789001594231149579', '470', '1161757408', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Bala Chander}', '2020-07-08 12:29:35', 'NA', '1161757408', '{email:myname@domain.com}{mob:8919180283}{custname:Bala Chander}', '1161757408', '', '772e14ad-80de-40cf-b3f3-f837a52bbc21', 'fe9955232d3e82c7dda1745da8b04e0b239d3cd6', '12345678', '12345678900', 'Bala Chander', '8919180283'),
(11, '0399', 'failure', '', 'TXN112000009781594207787367', '470', '1161354295', '1.00', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '2020-07-08 06:00:05', 'NA', '1161354295', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '1161354295', '', 'db2f0871-daf0-49a9-81cd-43c6c27dd943', '0a6ce5e10da8d3664654688b17100fcdb6c0395a', '00710676', '11200000978', 'Rohan Dsouza', '9820738564'),
(12, '0300', 'success', 'NA', 'TXN112000009781594311219718', '470', '1162735958', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 10:43:57', 'NA', '1162735958', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162735958', '', '48d13a9c-37a6-470b-be5a-e6e36384cdcf', '7b6111acdccca751d3eddb541d54bf6d62eb676a', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(13, '0300', 'success', 'NA', 'TXN112000009781594316783665', '470', '1162809029', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 12:16:47', 'NA', '1162809029', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162809029', '', 'f24ebb8c-fcf8-41c2-8eb8-f37b5ab65dab', '04b9abcd3ad6174470004c22943e993c8c6959b9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(14, '0300', 'success', 'NA', 'TXN112000009781594319836756', '470', '1162832865', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:07:35', 'NA', '1162832865', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162832865', '', '30f06d3f-112d-40b3-9aa5-74f475670553', 'c8e533bac757c40e912c2cb65c361c1d635ab169', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(15, '0300', 'success', 'NA', 'TXN112000009781594316783665', '470', '1162809029', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 12:16:47', 'NA', '1162809029', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162809029', '', 'f24ebb8c-fcf8-41c2-8eb8-f37b5ab65dab', '04b9abcd3ad6174470004c22943e993c8c6959b9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(16, '0300', 'success', 'NA', 'TXN112000009781594320075813', '470', '1162834702', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:11:32', 'NA', '1162834702', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162834702', '', '079c757d-a402-4552-8578-7875516096b9', 'c2c05d8045cd1009142c29b6fc2ec47753f16cc6', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(17, '0300', 'success', 'NA', 'TXN112000009781594319836756', '470', '1162832865', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:07:35', 'NA', '1162832865', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1162832865', '', '30f06d3f-112d-40b3-9aa5-74f475670553', 'c8e533bac757c40e912c2cb65c361c1d635ab169', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(18, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594359027284', 'NA', 'E12369898', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-10 00:02:18', 'NA', 'E12369898', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12369898', '', 'b9f1f990-5f44-457a-8f43-3c2b457cfc52', 'd4060dbdcd1a4fd3cf568f1d836e0efce6996e93', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(19, '0300', 'success', 'NA', 'TXN112000009781594360736189', '470', '1163094265', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-10 00:29:11', 'NA', '1163094265', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1163094265', '', '72e66b30-7584-4319-bd24-0194f3041aa9', '706d97d762ca83913fad2641ce85be74be2d1ea4', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(20, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365220481', 'NA', 'E12370839', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:44:13', 'NA', 'E12370839', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370839', '', '81c7d378-42bd-400b-ad95-aafd51332237', 'da84f80d9173408bf97881913b5c0af84ffa1389', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(21, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594322978466', 'NA', 'E12368811', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:59:56', 'NA', 'E12368811', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12368811', '', '92fa8cbf-41ff-4035-bab5-818653496f14', 'ea6b4f8b351f1ae6b51bf93ce214f987ffff6361', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(22, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365315053', 'NA', 'E12370935', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:48:07', 'NA', 'E12370935', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370935', '', '67ecd8df-da05-4dc5-b2db-1ed1ba22eae7', 'a78e7810099717c79e9955c143b4176575cbb6c9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(23, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594322978466', 'NA', 'E12368811', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:59:56', 'NA', 'E12368811', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12368811', '', '92fa8cbf-41ff-4035-bab5-818653496f14', 'ea6b4f8b351f1ae6b51bf93ce214f987ffff6361', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(24, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594359027284', 'NA', 'E12369898', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-10 00:02:18', 'NA', 'E12369898', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12369898', '', 'b9f1f990-5f44-457a-8f43-3c2b457cfc52', 'd4060dbdcd1a4fd3cf568f1d836e0efce6996e93', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(25, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594359027284', 'NA', 'E12369898', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-10 00:02:18', 'NA', 'E12369898', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12369898', '', 'b9f1f990-5f44-457a-8f43-3c2b457cfc52', 'd4060dbdcd1a4fd3cf568f1d836e0efce6996e93', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(26, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594363400301', 'NA', 'E12370562', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:15:46', 'NA', 'E12370562', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370562', '', '28a46464-8cd8-418e-b076-ed77c33792ae', 'e7a2cfbfb09fc13fd55d5250950159a508620464', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(27, '0300', 'success', 'NA', 'TXN112000009781594360736189', '470', '1163094265', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-10 00:29:11', 'NA', '1163094265', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1163094265', '', '72e66b30-7584-4319-bd24-0194f3041aa9', '706d97d762ca83913fad2641ce85be74be2d1ea4', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(28, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365220481', 'NA', 'E12370839', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:44:13', 'NA', 'E12370839', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370839', '', '81c7d378-42bd-400b-ad95-aafd51332237', 'da84f80d9173408bf97881913b5c0af84ffa1389', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(29, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365315053', 'NA', 'E12370935', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:48:07', 'NA', 'E12370935', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370935', '', '67ecd8df-da05-4dc5-b2db-1ed1ba22eae7', 'a78e7810099717c79e9955c143b4176575cbb6c9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(30, '0300', 'success', 'NA', 'TXN112000009781594365585961', '470', '1163195475', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:50:10', 'NA', '1163195475', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1163195475', '', '52a2bda3-99ea-4c76-9e49-377ca5ce0c4d', '9e816d13b35e40d17ab617f12fd920c622f84ef3', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(31, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365220481', 'NA', 'E12370839', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:44:13', 'NA', 'E12370839', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370839', '', '81c7d378-42bd-400b-ad95-aafd51332237', 'da84f80d9173408bf97881913b5c0af84ffa1389', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(32, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365315053', 'NA', 'E12370935', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:48:07', 'NA', 'E12370935', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370935', '', '67ecd8df-da05-4dc5-b2db-1ed1ba22eae7', 'a78e7810099717c79e9955c143b4176575cbb6c9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(33, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365315053', 'NA', 'E12370935', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:48:07', 'NA', 'E12370935', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370935', '', '67ecd8df-da05-4dc5-b2db-1ed1ba22eae7', 'a78e7810099717c79e9955c143b4176575cbb6c9', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(34, '0300', 'success', 'NA', 'TXN112000009781594365585961', '470', '1163195475', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:50:10', 'NA', '1163195475', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1163195475', '', '52a2bda3-99ea-4c76-9e49-377ca5ce0c4d', '9e816d13b35e40d17ab617f12fd920c622f84ef3', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(35, '0300', 'success', 'NA', 'TXN112000009781594365585961', '470', '1163195475', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:50:10', 'NA', '1163195475', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '1163195475', '', '52a2bda3-99ea-4c76-9e49-377ca5ce0c4d', '9e816d13b35e40d17ab617f12fd920c622f84ef3', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(36, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594365751637', 'NA', 'E12370995', '1.00', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', '2020-07-09 13:52:45', 'NA', 'E12370995', '{email:myname@domain.com}{mob:8919180283}{custname:Rohan Dsouza}', 'E12370995', '', '79576765-5e83-4b2c-9387-7602060f54ec', '516ac9c24eade9e12cbeec4e5305de4b23feef2c', '00710676', '11200000978', 'Rohan Dsouza', '8919180283'),
(37, '0300', 'success', 'NA', 'TXN112000009781594367724078', '470', '1163237035', '1.00', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '2020-07-10 02:25:45', 'NA', '1163237035', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '1163237035', '', '8fb483cc-37f8-40a8-abdb-792313814fac', '6eb0ef0cf64df6654524150e2ebd8063b4172052', '00710676', '11200000978', 'Rohan Dsouza', '9820738564'),
(38, '0392', 'failure', 'Cancelled_BY_User', 'TXN112000009781594367909422', 'NA', 'E12372136', '1.00', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', '2020-07-10 02:29:02', 'NA', 'E12372136', '{email:myname@domain.com}{mob:9820738564}{custname:Rohan Dsouza}', 'E12372136', '', '72cec240-ebf1-44cb-8e62-6e6585ecdc47', 'a4dcd0312d1eb4acf193f80444562531f8f809af', '00710676', '11200000978', 'Rohan Dsouza', '9820738564');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaction_reference`
--

CREATE TABLE `tbl_transaction_reference` (
  `id` int(11) NOT NULL,
  `clnt_txn_ref` varchar(500) DEFAULT NULL,
  `txn_amt` varchar(200) DEFAULT NULL,
  `clnt_rqst_meta` varchar(500) DEFAULT NULL,
  `tpsl_txn_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `app_no` varchar(20) DEFAULT NULL,
  `loan_code` varchar(20) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `txn_status` varchar(20) DEFAULT NULL,
  `txn_type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_transaction_reference`
--

INSERT INTO `tbl_transaction_reference` (`id`, `clnt_txn_ref`, `txn_amt`, `clnt_rqst_meta`, `tpsl_txn_time`, `app_no`, `loan_code`, `customer_name`, `mobile_number`, `txn_status`, `txn_type`) VALUES
(1, 'TXN112000009781594458458626', '1', '8919180283', '2020-07-10 15:37:38', '00710676', '11200000978', 'Rohan Dsouza', '8919180283', 'F', 'overdue'),
(2, 'TXN112000009781594458778587', '1', '8919180283', '2020-07-10 15:42:58', '00710676', '11200000978', 'Rohan Dsouza', '8919180283', 'F', 'overdue'),
(3, 'TXN112000009781594459629280', '1', '8919180283', '2020-07-10 15:57:09', '00710676', '11200000978', 'Rohan Dsouza', '8919180283', 'F', 'overdue');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_dhfl_customers`
--
ALTER TABLE `tbl_dhfl_customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_transaction_details`
--
ALTER TABLE `tbl_transaction_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_transaction_reference`
--
ALTER TABLE `tbl_transaction_reference`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_dhfl_customers`
--
ALTER TABLE `tbl_dhfl_customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_transaction_details`
--
ALTER TABLE `tbl_transaction_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `tbl_transaction_reference`
--
ALTER TABLE `tbl_transaction_reference`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
