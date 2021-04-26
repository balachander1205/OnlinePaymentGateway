package com.dhfl.OnlinePayment.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dhfl.OnlinePayment.config.ApplicationConfig;
import com.dhfl.OnlinePayment.config.Constants;
import com.dhfl.OnlinePayment.entity.TransactionDetailsEntity;
import com.dhfl.OnlinePayment.model.DoPaymentModel;
import com.dhfl.OnlinePayment.model.MessageModel;
import com.dhfl.OnlinePayment.pg.CommonUtil;
import com.dhfl.OnlinePayment.pg.MerchantCall;
import com.dhfl.OnlinePayment.rmq.RMqSender;
import com.dhfl.OnlinePayment.service.DHFLCustomersInter;
import com.dhfl.OnlinePayment.service.TransactionDetailsInter;

@Controller
public class CallbackController {
	Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private RMqSender messageSender;

	@Autowired
	DHFLCustomersInter dhflCustomerInter;
	
	@Autowired
	TransactionDetailsInter txnRefDetails;

	@Autowired
	public CallbackController(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public boolean isNull(String value) {
		if (value == null || value == "" || value == "NA" || value.length() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping(value = "/callback")
	public RedirectView callback(MessageModel msg, ModelMap map,
			@ModelAttribute("DoPaymentModel") DoPaymentModel doPaymentModel, RedirectAttributes redir,
			HttpSession httpSession) {
		RedirectView redirectView = null;
		try {
			String exchange = applicationConfig.getApp1Exchange();
			String routingKey = applicationConfig.getApp1RoutingKey();
			String queue = applicationConfig.getApp1Queue();
			map.put("status_msg", msg.getMsg());
			String responseMsg = msg.getMsg();
			String transResponse = "";
			String qrCode = "";
	
			if (responseMsg != null && responseMsg.length() > 0) {
				String key = applicationConfig.getMerchantKey();
				String iv = applicationConfig.getMerchantIv();
				// Decryption of payment dateway response.
				responseMsg = MerchantCall.doDecrypt(responseMsg, key, iv);
				JSONObject respObj = new JSONObject(responseMsg);
				logger.debug("Transaction Response=" + respObj.toString());			
	
				if (isNull(responseMsg)) {
					String txnId = respObj.getString("clnt_txn_ref");
					String txnTime = respObj.getString("tpsl_txn_time");
					String txnAmount = respObj.getString("txn_amt");
					String statusCode = respObj.getString("txn_status");
					String tpslTxnId = respObj.getString("tpsl_txn_id");
					System.out.println("TransactionId in callback="+txnId);
					// Getting transaction reference details
					TransactionDetailsEntity transactionDetailsEntity = txnRefDetails.getTxnReference(txnId);
					String mobileNumber = transactionDetailsEntity.getMobile_number()!=null?transactionDetailsEntity.getMobile_number():"";
					String custName = transactionDetailsEntity.getCustomer_name()!=null?transactionDetailsEntity.getCustomer_name():"";
					String txnType = transactionDetailsEntity.getPayType()!=null?transactionDetailsEntity.getPayType():"";
					String appno = transactionDetailsEntity.getApp_no()!=null?transactionDetailsEntity.getApp_no():"";
					String loancode = transactionDetailsEntity.getLoan_code()!=null?transactionDetailsEntity.getLoan_code():"";
					System.out.println("TxnReferenceDetails= CustomerName="+custName+" Mobile Number="+mobileNumber);
					// Putting message into QUEUE
					try {
						respObj.put("loanCode", loancode);
						respObj.put("appNo", appno);
						respObj.put("custName", custName);
						respObj.put(Constants.KEY_TRANS_TYPE,txnType);
						respObj.put("mobileNumber",mobileNumber);
						logger.debug("Queue : " + queue + " Exchange : " + exchange + " Routing Key : " + routingKey);
						//messageSender.sendMessage(rabbitTemplate, exchange, routingKey, respObj.toString());
						logger.debug("Updating TxnDetails for TxnID="+txnId);
						updateTxnDetails(respObj);
					} catch (Exception e) {
						logger.debug("Xception@ sending message to queue ::  " + e);
					}
					
					String successMsg = "\n Transaction Time : " + txnTime + "<br>" + "\n Transaction ID : " + txnId
							+ "<br>" + "\n Amount Paid : " + respObj.getString("txn_amt");
					httpSession.setAttribute("txnId", txnId);
					httpSession.setAttribute("txnTime", txnTime);
					httpSession.setAttribute("txnAmount", txnAmount);
					//httpSession.setAttribute("loanCode", CommonUtil.maskString(loancode, 0, 6, 'x'));
					httpSession.setAttribute("loanCode", loancode);
					httpSession.setAttribute("custName", custName);
					httpSession.setAttribute("mobileNo", mobileNumber);
					if (statusCode.contains(Constants.PG_0300)) {
						successMsg = applicationConfig.getTranSuccessMsg() + "<br>" + "\n Transaction Time : "
								+ respObj.getString("tpsl_txn_time") + "<br>" + "\n Transaction ID : "
								+ respObj.getString("clnt_txn_ref") + "<br>" + "\n Amount Paid : "
								+ respObj.getString("txn_amt");
						transResponse = applicationConfig.getSuccessImg();
						// + successMsg;
						//qrCode = QRCode.generateQRCodeImage(successMsg);
						redir.addFlashAttribute("qrCode", qrCode);
						redir.addFlashAttribute("status_msg", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.getTranSuccessMsg());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0392)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0392());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0395)) {
						// transResponse = applicationConfig.get_0395();
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0395());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0396)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0396());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0397)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0397());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0399)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0399());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0400)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0400());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0401)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0401());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0402)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0402());
					}
					if (statusCode.equalsIgnoreCase(Constants.PG_0499)) {
						transResponse = applicationConfig.getFailureImg();
						redir.addFlashAttribute("status_msg_fail", transResponse);
						httpSession.setAttribute("txnStatus", applicationConfig.get_0499());
					}
				}
			}
			httpSession.setAttribute("mobileNumber", "");
			httpSession.setAttribute("brLoanCode", "");
			httpSession.setAttribute("disableSearch", "true");
			httpSession.setAttribute("step_image", applicationConfig.getTransStep3Image());
		}catch(Exception e) {
			logger.debug("Exception@/callback="+e);
			e.printStackTrace();
			redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
					applicationConfig.getPgAppTemporarilyUnavailable());
			redirectView = new RedirectView("/payment", true);
		}
		redirectView = new RedirectView("/payment", true);
		return redirectView;
	}
	
	public int updateTxnDetails(JSONObject responseObj) {
		int count = 0;
		try {
			logger.info("Updating TXN Details into DB=: "+responseObj.toString());
			String txn_status = responseObj.getString("txn_status")!=null? responseObj.getString("txn_status"):"";
			String txn_msg = responseObj.getString("txn_msg")!=null? responseObj.getString("txn_msg"):"";
			String txn_err_msg = responseObj.getString("txn_err_msg")!=null? responseObj.getString("txn_err_msg"):"";
			String clnt_txn_ref = responseObj.getString("clnt_txn_ref")!=null? responseObj.getString("clnt_txn_ref"):"";
			String tpsl_bank_cd = responseObj.getString("tpsl_bank_cd")!=null? responseObj.getString("tpsl_bank_cd"):"";
			String tpsl_txn_id = responseObj.getString("tpsl_txn_id")!=null? responseObj.getString("tpsl_txn_id"):"";
			String txn_amt = responseObj.getString("txn_amt")!=null? responseObj.getString("txn_amt"):"";
			String clnt_rqst_meta = responseObj.getString("clnt_rqst_meta")!=null? responseObj.getString("clnt_rqst_meta"):"";
			String tpsl_txn_time = responseObj.getString("tpsl_txn_time")!=null? responseObj.getString("tpsl_txn_time"):"";
			//String tpsl_rfnd_id = responseObj.getString("tpsl_rfnd_id")!=null? responseObj.getString("tpsl_rfnd_id"):"";
			String bal_amt = responseObj.getString("bal_amt")!=null? responseObj.getString("bal_amt"):"";
			//String card_id = responseObj.getString("card_id")!=null? responseObj.getString("card_id"):"";
			//String alias_name = responseObj.getString("alias_name")!=null? responseObj.getString("alias_name"):"";
			String rqst_token = responseObj.getString("rqst_token")!=null? responseObj.getString("rqst_token"):"";
			String hash = responseObj.getString("hash")!=null? responseObj.getString("hash"):"";
			//String appNo = responseObj.getString("appNo")!=null? responseObj.getString("appNo"):"";
			//String loanCode = responseObj.getString("loanCode")!=null? responseObj.getString("loanCode"):"";
			String custName = responseObj.getString("custName")!=null? responseObj.getString("custName"):"";
			//String mobileNumber = responseObj.getString("mobileNumber")!=null? responseObj.getString("mobileNumber"):"";
			String BankTransactionID = tpsl_txn_id;
			String mandate_reg_no = "";
			String token = rqst_token;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			SimpleDateFormat dateFormatTS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if (tpsl_txn_time == "NA" || tpsl_txn_time == "" || tpsl_txn_time == null) {
				long curTimeMiles = System.currentTimeMillis();
				Date curDateTime = new Date(curTimeMiles);
				tpsl_txn_time = dateFormatTS.format(curDateTime);
			}
			Date date = dateFormat.parse(tpsl_txn_time);
			date = set530HHToDate(date);
			//date = dateFormatTS.parse(dateFormatTS.format(date));
			count = txnRefDetails.updateTxnDetails(txn_status, txn_msg, txn_err_msg, tpsl_bank_cd, tpsl_txn_id, txn_amt, 
														clnt_rqst_meta, date, bal_amt, "null", custName, 
														BankTransactionID, mandate_reg_no, token, hash, Constants.TXN_TYPE_SUCCESS,
														clnt_txn_ref);
		}catch (Exception e) {
			System.out.println("Exception1@/callback.updateTxnDetails() :="+e);
			logger.error("Exception1@/callback.updateTxnDetails() :="+e);
		}
		return count;
	}
	
	public Date set530HHToDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.HOUR_OF_DAY, 5);
		cal.add(Calendar.MINUTE, 30);
		Date date = cal.getTime();
		return date;
	}
}
