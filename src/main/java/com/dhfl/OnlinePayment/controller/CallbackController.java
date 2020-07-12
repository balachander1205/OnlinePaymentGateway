package com.dhfl.OnlinePayment.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dhfl.OnlinePayment.config.ApplicationConfig;
import com.dhfl.OnlinePayment.config.Constants;
import com.dhfl.OnlinePayment.entity.DHFLTransactionRefrence;
import com.dhfl.OnlinePayment.model.DoPaymentModel;
import com.dhfl.OnlinePayment.model.MessageModel;
import com.dhfl.OnlinePayment.pg.CommonUtil;
import com.dhfl.OnlinePayment.pg.MerchantCall;
import com.dhfl.OnlinePayment.qrcode.QRCode;
import com.dhfl.OnlinePayment.rmq.RMqSender;
import com.dhfl.OnlinePayment.service.DHFLCustomersInter;
import com.dhfl.OnlinePayment.service.DHFLTransactionRefrenceInter;

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
	private Environment env;

	@Autowired
	DHFLCustomersInter dhflCustomerInter;
	
	@Autowired
	DHFLTransactionRefrenceInter dhflTxnRefrence;

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
					// Getting transaction reference details
					DHFLTransactionRefrence txnReference = dhflTxnRefrence.getTxnReference(txnId);
					int countUpdate = dhflTxnRefrence.updateTxnStatus(Constants.TXN_TYPE_SUCCESS, txnId, tpslTxnId);
					System.out.println("TxnReferenceDetails= CustomerName="+txnReference.getCustomername()
										+" Mobile Number="+txnReference.getMobileno());
					String mobileNumber = txnReference.getMobileno()!=null?txnReference.getMobileno():"";
					String custName = txnReference.getCustomername()!=null?txnReference.getCustomername():"";
					String txnType = txnReference.getTxnType()!=null?txnReference.getTxnType():"";
					String appno = txnReference.getApplno()!=null?txnReference.getApplno():"";
					String loancode = txnReference.getBrloancode()!=null?txnReference.getBrloancode():"";
					// Putting message into QUEUE
					try {
						respObj.put("loanCode", loancode);
						respObj.put("appNo", appno);
						respObj.put("custName", custName);
						respObj.put(Constants.KEY_TRANS_TYPE,txnType);
						respObj.put("mobileNumber",mobileNumber);
						logger.debug("Queue : " + queue + " Exchange : " + exchange + " Routing Key : " + routingKey);
						messageSender.sendMessage(rabbitTemplate, exchange, routingKey, respObj.toString());
					} catch (Exception e) {
						logger.debug("Xception@ sending message to queue ::  " + e);
					}
					
					String successMsg = "\n Transaction Time : " + txnTime + "<br>" + "\n Transaction ID : " + txnId
							+ "<br>" + "\n Amount Paid : " + respObj.getString("txn_amt");
					httpSession.setAttribute("txnId", txnId);
					httpSession.setAttribute("txnTime", txnTime);
					httpSession.setAttribute("txnAmount", txnAmount);
					httpSession.setAttribute("loanCode", CommonUtil.maskString(loancode, 0, 6, 'x'));
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
			httpSession.setAttribute("disableSearch", "true");
			httpSession.setAttribute("step_image", applicationConfig.getTransStep3Image());
		}catch(Exception e) {
			logger.debug("Exception@/callback="+e);
			return redirectView = new RedirectView("/payment", true);
		}
		redirectView = new RedirectView("/payment", true);
		return redirectView;
	}
}
