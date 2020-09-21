package com.dhfl.OnlinePayment.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dhfl.OnlinePayment.config.ApplicationConfig;
import com.dhfl.OnlinePayment.config.Constants;
import com.dhfl.OnlinePayment.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePayment.model.DoPaymentChargeModel;
import com.dhfl.OnlinePayment.model.DoPaymentModel;
import com.dhfl.OnlinePayment.model.GetOtpDetailsModel;
import com.dhfl.OnlinePayment.pg.MerchantCall;
import com.dhfl.OnlinePayment.pg.SendSmsOTP;
import com.dhfl.OnlinePayment.rmq.RMqSender;
import com.dhfl.OnlinePayment.service.DHFLCustomersInter;
import com.dhfl.OnlinePayment.service.TransactionDetailsInter;
import com.xss.filters.annotation.XxsFilter;

@Controller
public class PaymentController {
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
	TransactionDetailsInter txnDetailsInter;

	@Autowired
	public PaymentController(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	List<String> captchaList = new ArrayList<String>();

	@GetMapping("/home")
	@ResponseBody
	public String home() {
		return "home page";
	}

	@GetMapping("/status")
	public String status() {
		return "it is runing on" + (env.getProperty("local.server.port"));
	}

	@GetMapping("/exception")
	public String exception() {
		return "exception";
	}

	public boolean isNull(String value) {
		if (value == null || value == "" || value == "NA" || value.length() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	// Home page
	@GetMapping("/payment")
	public String payment(HttpSession httpSession, ModelMap modelMap, RedirectAttributes redir) {
		System.out.println("it is runing on :>>--->>" + (env.getProperty("local.server.port")));
		String stepImage = httpSession.getAttribute("step_image") != null
				? (String) httpSession.getAttribute("step_image")
				: applicationConfig.getTransStep1Image();
		logger.debug("Step Image=" + stepImage);
		// httpSession.setAttribute("step_image", stepImage);
		return "payment";
	}

	// Searching for loan details
	@XxsFilter
	@RequestMapping(value = "/getdetails", method = RequestMethod.POST)
	public RedirectView getDetails(ModelMap map, @RequestParam("otpData") String otpData, RedirectAttributes redir,
			HttpSession httpSession) {
		String searchField = "";
		RedirectView redirectView = null;
		DHFLCustomersEntity data = null;
		try {
			String loancode = (String) httpSession.getAttribute("brLoanCode") != null
					? (String) httpSession.getAttribute("brLoanCode")
					: "";
			String appno = (String) httpSession.getAttribute("applNo") != null
					? (String) httpSession.getAttribute("applNo")
					: "";
			String sessionOtp = (String) httpSession.getAttribute("otp") != null
					? (String) httpSession.getAttribute("otp")
					: "";
			otpData = otpData != null ? otpData : "";

			boolean OTP_FLAG = false;
			if ((otpData != null || otpData != "") && otpData.length() == 4)
				if (sessionOtp.equalsIgnoreCase(otpData)) {
					OTP_FLAG = true;
				}
			if (OTP_FLAG) {
				logger.debug("OTP Validation is successful, Loancode=" + loancode + " AppNo=" + appno + " sessionOtp="
						+ sessionOtp + "");
				if (appno == "" && loancode.length() > 0) {
					System.out.println("AppNo# is present.");
					searchField = loancode;
					data = dhflCustomerInter.searchByBrLoanCode(loancode);
				} else if (loancode == "" && appno.length() > 0) {
					System.out.println("loan code is present.");
					searchField = appno;
					data = dhflCustomerInter.searchByAppNo(appno);
				} else if ((appno.length() > 0 && loancode.length() > 0)) {
					System.out.println("App no, loan code is present.");
					searchField = loancode;
					data = dhflCustomerInter.searchByBrLoanCode(loancode);
				} else {
					String errorMsg = "<img style=\"width: 2%;border-radius: 20px;border-radius: 20px;margin-right: 5px;padding: 2px 5px 5px 5px;\" src=\"images/fail.png\" alt=\"Logo\">"
							+ "No details found for above search data.";
					httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
					redirectView = new RedirectView("/payment", true);
					redir.addFlashAttribute("errormsg", errorMsg);
					return redirectView;
				}
				System.out.println(
						"Search filed value=" + searchField + " Appl No=" + appno + " BrLoan Code=" + loancode);
				if (data != null) {
					try {
						redir.addFlashAttribute("mobileno", data.getMobileno());
						redir.addFlashAttribute("loancode", data.getBrloancode());
						redir.addFlashAttribute("applicationno", data.getApplno());
						redir.addFlashAttribute("customerid", data.getCustomername());
						// Overdue EMI
						if (isNull(String.valueOf(data.getTotalOverdueEMI())) && data.getTotalOverdueEMI() > 0) {
							logger.debug("Overdue EMI details present for LoanCode=" + loancode + " |applicationNumber="
									+ appno);
							redir.addFlashAttribute("TotalOverdueEMI", data.getTotalOverdueEMI());
							redir.addFlashAttribute("MinimumOverdueAmount", data.getMinimumOverdueAmount());
							redir.addFlashAttribute("min_amount", 0);
							redir.addFlashAttribute("max_amount", data.getTotalOverdueEMI());
							redir.addFlashAttribute("amt_info", "Enter amount between " + data.getMinimumOverdueAmount()
									+ "Rs. - " + data.getTotalOverdueEMI() + "Rs. to do payment.");
						}
						// redir.addFlashAttribute("min_amount", data.getMinimumOverdueAmount());
						// Charges
						if (isNull(String.valueOf(data.getTotalChargesAmount())) && data.getTotalChargesAmount() > 0) {
							logger.debug("Overdue Charge details present for LoanCode=" + loancode
									+ " |applicationNumber=" + appno);
							redir.addFlashAttribute("TotalChargesAmount", data.getTotalChargesAmount());
							redir.addFlashAttribute("MinimumChargeAmount", data.getMinimumChargeAmount());
							redir.addFlashAttribute("min_amount_charge", 0);
							redir.addFlashAttribute("max_amount_charge", data.getTotalChargesAmount());
							redir.addFlashAttribute("amt_info_charge",
									"Enter amount between " + data.getMinimumChargeAmount() + "Rs. - "
											+ data.getTotalChargesAmount() + "Rs. to do payment.");
						}
						String divClass = Constants.DIV_CLASS_COL_MD_12;
						if ((isNull(String.valueOf(data.getTotalOverdueEMI())) && data.getTotalOverdueEMI() > 0)
								&& (isNull(String.valueOf(data.getTotalChargesAmount()))
										&& data.getTotalChargesAmount() > 0)) {
							divClass = Constants.DIV_CLASS_COL_MD_6;
						}
						httpSession.setAttribute(Constants.DIV_CLASS, divClass);
						httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
						redirectView = new RedirectView("/payment", true);

					} catch (Exception e) {
						logger.debug("Xception at /searchform data : " + e);
						redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
								applicationConfig.getPgAppTemporarilyUnavailable());
						redirectView = new RedirectView("/payment", true);
					}
					return redirectView;
				} else {
					String errorMsg = "<img style=\"width: 2%;border-radius: 20px;border-radius: 20px;margin-right: 5px;padding: 2px 5px 5px 5px;\" src=\"images/fail.png\" alt=\"Logo\">"
							+ "No details found.";
					httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
					redirectView = new RedirectView("/payment", true);
					redir.addFlashAttribute("errormsg", errorMsg);
					return redirectView;
				}
			} else {
				String errorMsg = applicationConfig.getMsgInvalidOtp();
				httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
				redirectView = new RedirectView("/payment", true);
				redir.addFlashAttribute("otpResponse", "200");
				redir.addFlashAttribute(Constants.KEY_OTP_ERROR_MSG, errorMsg);
				return redirectView;
			}
		} catch (Exception e) {
			logger.debug("Exception@/getdetails=" + e);
			redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
					applicationConfig.getPgAppTemporarilyUnavailable());
			redirectView = new RedirectView("/payment", true);
		}
		return redirectView;
	}

	// Requesting for OTP before search.
	@XxsFilter
	@RequestMapping(value = "/getOtpDetails", method = { RequestMethod.POST, RequestMethod.GET }, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public RedirectView getOTPDetails(ModelMap map,
			@ModelAttribute("GetOtpDetailsModel") GetOtpDetailsModel getOtpDetailsModel, RedirectAttributes redir,
			HttpSession httpSession, HttpServletRequest request) {
		RedirectView redirectView = null;
		try {
			String searchField = "";
			DHFLCustomersEntity data = null;
			String otpUrl = applicationConfig.getOtpUrl();
			String otpMSg = applicationConfig.getOtpMsg();
			String otpResponse = "";
			String otp = SendSmsOTP.getOtp();
			//String otp = "1234";
			String loancode = getOtpDetailsModel.getBrLoanCode() != null ? getOtpDetailsModel.getBrLoanCode() : "";
			String appno = getOtpDetailsModel.getBrLoanCode() != null ? getOtpDetailsModel.getBrLoanCode() : "";
			// getOtpDetailsModel.getApplNo() != null ? getOtpDetailsModel.getApplNo() : "";
			String mobileNo = getOtpDetailsModel.getMobileNumber() != null ? getOtpDetailsModel.getMobileNumber() : "";
			String sessionCaptcha = (String) httpSession.getAttribute("captcha_security");
			String captcha = request.getParameter("captcha");

			httpSession.setAttribute("brLoanCode", loancode);
			httpSession.setAttribute("applNo", appno);
			httpSession.setAttribute(Constants.KEY_MOB_NUMBER, mobileNo);
			httpSession.setAttribute("mobileNumber", mobileNo);
			httpSession.setAttribute("searchType", getOtpDetailsModel.getSearch_param());
			// redir.addAttribute("searchType", getOtpDetailsModel.getSearch_param());
			boolean CAPTCHA_FLAG = false;
			if (captcha.equals(sessionCaptcha)) {
				CAPTCHA_FLAG = true;
				logger.debug(
						"Captch validation is successful sessionCaptch=" + sessionCaptcha + " User Captch=" + captcha);
			} else {
				logger.debug("Captch validation is not successful sessionCaptch=" + sessionCaptcha + " User Captch="
						+ captcha);
			}
			httpSession.setAttribute("otp", otp);
			// Validation of Captcha
			logger.debug("Search Type=" + getOtpDetailsModel.getSearch_param() + "| Mobile Number=" + mobileNo
					+ "|Session captcha=" + sessionCaptcha + "|USer Captcha=" + getOtpDetailsModel.getCaptcha()
					+ "|ApplNumber=" + appno + "|Loan Code=" + loancode);
			if (CAPTCHA_FLAG == true) {
				if (getOtpDetailsModel.getSearch_param().equals("brloancode") && loancode.length() > 0) {
					logger.debug("Loan Code is present.");
					searchField = loancode;
					data = dhflCustomerInter.searchByBrLoanCode(loancode);
				} else if (getOtpDetailsModel.getSearch_param().equals("applno") && appno.length() > 0) {
					logger.debug("Application Number is present.");
					searchField = appno;
					data = dhflCustomerInter.searchByAppNo(appno);
				} else {
					logger.debug("Loan details not found for search parameters appNo=" + appno + " |loanCode="
							+ loancode + "|SearchType=" + getOtpDetailsModel.getSearch_param());
					String errorMsg = applicationConfig.getMsgInvalidSearchParams();
					httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
					redirectView = new RedirectView("/payment", true);
					redir.addFlashAttribute(Constants.KEY_INVALID_SEARCH_ERROR_MSG, errorMsg);
					return redirectView;
				}
			} else {
				logger.debug("Invalid Captcha Search Type=" + getOtpDetailsModel.getSearch_param() + "| Mobile Number="
						+ mobileNo + "|Session captcha=" + sessionCaptcha + "|USer Captcha="
						+ getOtpDetailsModel.getCaptcha() + "|ApplNumber=" + appno + "|Loan Code=" + loancode);
				httpSession.setAttribute("brLoanCode", loancode);
				httpSession.setAttribute("applNo", appno);
				httpSession.setAttribute(Constants.KEY_MOB_NUMBER, mobileNo);
				httpSession.setAttribute("mobileNumber", mobileNo);
				httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
				redirectView = new RedirectView("/payment", true);
				redir.addFlashAttribute(Constants.KEY_ERROR_MSG_CAPTCHA, Constants.MSG_INVALID_CAPTCHA);
				return redirectView;
			}
			// End of Captcha validation
			String otpData = otpUrl + "&to=" + mobileNo + "&text=" + otpMSg + "%20" + otp;
			System.out.println("OTP Data=" + otpData);
			boolean isInvalidPayMode = false;
			// Sending OTP message if data is present in DB
			if (data != null) {
				// Data is present with zero values
				if (isNull(String.valueOf(data.getTotalChargesAmount())) && data.getTotalChargesAmount() <= 0
						&& isNull(String.valueOf(data.getMinimumChargeAmount())) && data.getMinimumChargeAmount() <= 0
						&& isNull(String.valueOf(data.getTotalOverdueEMI())) && data.getTotalOverdueEMI() <= 0) {
					redir.addFlashAttribute(Constants.KEY_INVALID_SEARCH_ERROR_MSG,
							applicationConfig.getInvalidPaymentMode());
					redirectView = new RedirectView("/payment", true);
					return redirectView;
				} else {
					// send OTP
					// otpResponse = SendSmsOTP.sendOtpSms(otpData);
					otpResponse = "200";
					httpSession.setAttribute("brLoanCode", data.getBrloancode());
					httpSession.setAttribute("applNo", data.getApplno());
					httpSession.setAttribute(Constants.KEY_MOB_NUMBER, mobileNo);
					httpSession.setAttribute("mobileNumber", mobileNo);
					httpSession.setAttribute(Constants.KEY_CUST_NAME, data.getCustomername());
					// Over due amount
					httpSession.setAttribute("minOverDue",
							data.getMinimumOverdueAmount() != null ? data.getMinimumOverdueAmount() : 0);
					httpSession.setAttribute("maxOverDue",
							data.getTotalOverdueEMI() != null ? data.getTotalOverdueEMI() : 0);
					// Over due charges
					httpSession.setAttribute("minChargeDue",
							data.getMinimumChargeAmount() != null ? data.getMinimumChargeAmount() : 0);
					httpSession.setAttribute("maxChargeDue",
							data.getTotalChargesAmount() != null ? data.getTotalChargesAmount() : 0);
					httpSession.setAttribute(Constants.KEY_MOB_NUMBER, mobileNo);
					logger.debug("SMS OTP Response=" + otpResponse);
				}
			} else {
				String errorMsg = "";
				if (getOtpDetailsModel.getSearch_param().equalsIgnoreCase("applno")) {
					errorMsg = applicationConfig.getInvalidApplNumber();
				}
				if (getOtpDetailsModel.getSearch_param().equalsIgnoreCase("brloancode")) {
					errorMsg = applicationConfig.getInvalidLoanCode();
				}
				httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
				redirectView = new RedirectView("/payment", true);
				redir.addFlashAttribute(Constants.KEY_INVALID_SEARCH_ERROR_MSG, errorMsg);
				return redirectView;
			}
			System.out.println("Search filed value=" + searchField + "|Appl No=" + appno + "|BrLoan Code=" + loancode);
			if (otpResponse != null && otpResponse.equalsIgnoreCase("200")) {
				httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
				redirectView = new RedirectView("/payment", true);
				redir.addFlashAttribute(Constants.KEY_OTP_REPONSE, otpResponse);
				redir.addFlashAttribute(Constants.KEY_OTP_SENT_MSG, applicationConfig.getOtpSentMessage());
				return redirectView;
			} else {
				httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
				redirectView = new RedirectView("/payment", true);
				redir.addFlashAttribute(Constants.KEY_ERROR_MSG_OTP_UNAVAILABLE, applicationConfig.getOtpUnavailable());
				return redirectView;
			}
		} catch (Exception e) {
			logger.debug("Exception@/doOverDueEmiPayment=" + e);
			redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
					applicationConfig.getPgAppTemporarilyUnavailable());
			redirectView = new RedirectView("/payment", true);
		}
		return redirectView;
	}

	@XxsFilter
	@RequestMapping(value = "/dopayment", method = RequestMethod.POST)
	public RedirectView doOverDueEmiPayment(ModelMap map,
			@ModelAttribute("DoPaymentModel") DoPaymentModel doPaymentModel, RedirectAttributes redir,
			HttpSession httpSession) {
		RedirectView redirectView = null;
		String key = applicationConfig.getMerchantKey();
		String iv = applicationConfig.getMerchantIv();
		String callbackUrl = applicationConfig.getCallbackUrl();
		String merchantCode = applicationConfig.getMerchantCode();
		String merchantCur = applicationConfig.getMerchantCur();
		String merchantWsUrl = applicationConfig.getMerchantWebServiceUrl();

		String amount = "";
		if (doPaymentModel.getAmount() == null || doPaymentModel.getAmount() == "") {
			amount = doPaymentModel.getAmount_to_pay();
		} else {
			amount = doPaymentModel.getAmount();
		}

		try {
			String loanCode = (String) httpSession.getAttribute("brLoanCode");
			String custId = (String) httpSession.getAttribute(Constants.KEY_CUST_NAME);
			String mobileNo = (String) httpSession.getAttribute(Constants.KEY_MOB_NUMBER);
			String applNo = (String) httpSession.getAttribute("applNo");
			httpSession.setAttribute(Constants.KEY_TRANS_TYPE, "overdue");

			Long minAmount = Long.parseLong(String.valueOf(httpSession.getAttribute("minOverDue")) != null
					? String.valueOf(httpSession.getAttribute("minOverDue"))
					: "0");
			Long maxAmount = Long.parseLong(String.valueOf(httpSession.getAttribute("maxOverDue")) != null
					? String.valueOf(httpSession.getAttribute("maxOverDue"))
					: "0");
			logger.debug("Amount to Pay=" + amount + "|Mobile Number=" + mobileNo + "|minAmount=" + minAmount
					+ "|maxAmount=" + maxAmount + "|Type=overdue");
			long CURR_TMIES = System.currentTimeMillis();
			Date curDate = new Date(CURR_TMIES);
			String txnNumber = "TXN" + loanCode + String.valueOf(CURR_TMIES);
			int count = 0;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String txnFmtDate = dateFormat.format(curDate);
				Date txnDate = dateFormat.parse(txnFmtDate);
				// SimpleDateFormat dateFormatTS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				// txnDate = dateFormatTS.parse(dateFormatTS.format(txnDate));
				count = txnDetailsInter.insertTransactionDetails("null", "null", "null", txnNumber, "null", "null",
						amount, loanCode + "|" + custId + "|" + mobileNo, txnDate, "null", "null", custId, "null",
						"null", "null", "null", applNo, loanCode, custId, mobileNo, Constants.TXN_TYPE_PENDING,
						Constants.TXN_TYPE_OVERDUE);
				System.out.println(
						"Transaction Reference doOverDueEmiPayment Count=" + count + " txnFmtDate=" + txnFmtDate);
			} catch (Exception e) {
				logger.debug("Exception in doOverDueEMIPayment inserting TxnReference Details=" + e);
				redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
						applicationConfig.getPgAppTemporarilyUnavailable());
				redirectView = new RedirectView("/payment", true);
			}
			if (count >= 1) {
				logger.debug("Transaction Reference Inserted TxnNumber=" + txnNumber + " |Amount=" + amount
						+ " |mobileNumber=" + mobileNo + " |LoanCode=" + loanCode);
				// if((Long.parseLong(amount) >= minAmount) &&
				// (Long.parseLong(amount)<=maxAmount)) {
				if ((Long.parseLong(amount) <= maxAmount)) {
					String paymentUrl = MerchantCall.doMerchantCall(mobileNo, amount, key, iv, custId, loanCode,
							callbackUrl, merchantCode, merchantWsUrl, merchantCur, txnNumber);
					logger.debug("Redirecting to Payment=" + paymentUrl + " with amount=" + Float.parseFloat(amount)
							+ " TxnId=" + txnNumber);
					redirectView = new RedirectView(paymentUrl, true);
				} else {
					logger.debug("Entered invalid loan amount=" + amount + "|Mobile Number=" + mobileNo + "|minAmount="
							+ minAmount + "|maxAmount=" + maxAmount + "|Type=overdue");
					redir.addFlashAttribute("mobileno", doPaymentModel.getMobile_no());
					redir.addFlashAttribute("loancode", loanCode);
					redir.addFlashAttribute("applicationno", doPaymentModel.getApp_no());
					redir.addFlashAttribute("customerid", custId);
					redir.addFlashAttribute("amt_info",
							"Enter amount between " + minAmount + "Rs. - " + maxAmount + "1Rs. to do payment.");
					redir.addFlashAttribute("min_amount", minAmount);
					redir.addFlashAttribute("max_amount", maxAmount);
					redir.addFlashAttribute(Constants.KEY_ERROR_MSG, applicationConfig.getIncorrectAmount());
					httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
					redirectView = new RedirectView("/payment", true);
				}
			} else {
				logger.debug("Transaction Reference Details Not Inserted TxnNumber=" + txnNumber + " |Amount=" + amount
						+ " |mobileNumber=" + mobileNo + " |LoanCode=" + loanCode);
				// httpSession.setAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
				// applicationConfig.getPgAppTemporarilyUnavailable());
				redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
						applicationConfig.getPgAppTemporarilyUnavailable());
				redirectView = new RedirectView("/payment", true);
			}
		} catch (Exception e) {
			logger.debug("Exception@/doOverDueEmiPayment=" + e);
			redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
					applicationConfig.getPgAppTemporarilyUnavailable());
			redirectView = new RedirectView("/payment", true);
		}
		return redirectView;
	}

	@XxsFilter
	@RequestMapping(value = "/doPaymentCharge", method = RequestMethod.POST)
	public RedirectView doPaymentCharges(ModelMap map,
			@ModelAttribute("DoPaymentChargeModel") DoPaymentChargeModel doPaymentModel, RedirectAttributes redir,
			HttpSession httpSession) {
		RedirectView redirectView = null;
		String key = applicationConfig.getMerchantKey();
		String iv = applicationConfig.getMerchantIv();
		String callbackUrl = applicationConfig.getCallbackUrl();
		String merchantCode = applicationConfig.getMerchantCode();
		String merchantCur = applicationConfig.getMerchantCur();
		String merchantWsUrl = applicationConfig.getMerchantWebServiceUrl();

		String amount = "";
		if ((doPaymentModel.getAmount_to_pay1() != null || doPaymentModel.getAmount_to_pay1() != "")
				&& doPaymentModel.getAmount_to_pay1().length() > 0) {
			amount = doPaymentModel.getAmount_to_pay1();
		} else if ((doPaymentModel.getAmount_to_pay_charge() != null || doPaymentModel.getAmount_to_pay_charge() != "")
				&& doPaymentModel.getAmount_to_pay_charge().length() > 0) {
			amount = doPaymentModel.getAmount_to_pay_charge();
		}

		try {
			String loanCode = (String) httpSession.getAttribute("brLoanCode");
			String applNo = (String) httpSession.getAttribute("applNo");
			String customerName = (String) httpSession.getAttribute(Constants.KEY_CUST_NAME);
			String mobileNo = (String) httpSession.getAttribute(Constants.KEY_MOB_NUMBER);
			httpSession.setAttribute(Constants.KEY_TRANS_TYPE, "charge");

			Long minAmount = Long.parseLong(String.valueOf(httpSession.getAttribute("minChargeDue")) != null
					? String.valueOf(httpSession.getAttribute("minChargeDue"))
					: "0");
			Long maxAmount = Long.parseLong(String.valueOf(httpSession.getAttribute("maxChargeDue")) != null
					? String.valueOf(httpSession.getAttribute("maxChargeDue"))
					: "0");
			long CURR_TMIES = System.currentTimeMillis();
			String txnNumber = "TXN" + loanCode + String.valueOf(CURR_TMIES);
			Date curDate = new Date(CURR_TMIES);
			int count = 0;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				String txnFmtDate = dateFormat.format(curDate);
				Date txnDate = dateFormat.parse(txnFmtDate);
				System.out.println("Insertion 1..." + count);
				count = txnDetailsInter.insertTransactionDetails("null", "null", "null", txnNumber, "null", "null",
						amount, loanCode + "|" + customerName + "|" + mobileNo, txnDate, "null", "null", customerName,
						"null", "null", "null", "null", applNo, loanCode, customerName, mobileNo,
						Constants.TXN_TYPE_PENDING, Constants.TXN_TYPE_CHARGE);
				System.out.println("Transaction Reference doOverDueChargesPayment Count=" + count);
			} catch (Exception e) {
				logger.debug("Exception in doOverDueChargesPayment inserting TxnReference Details=" + e);
				redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
						applicationConfig.getPgAppTemporarilyUnavailable());
				redirectView = new RedirectView("/payment", true);
			}

			logger.debug("Amount to Pay=" + amount + "|Mobile Number=" + mobileNo + "|minAmount=" + minAmount
					+ "|maxAmount=" + maxAmount + "|Type=charge");
			if (count >= 1) {
				// if(Long.parseLong(amount)>=minAmount && Long.parseLong(amount)<=maxAmount) {
				if (Long.parseLong(amount) <= maxAmount) {
					String paymentUrl = MerchantCall.doMerchantCall(mobileNo, amount, key, iv, customerName, loanCode,
							callbackUrl, merchantCode, merchantWsUrl, merchantCur, txnNumber);
					logger.debug("Redirecting to Payment=" + paymentUrl + " with amount=" + Float.parseFloat(amount)
							+ " TxnId=" + txnNumber);
					redirectView = new RedirectView(paymentUrl, true);
				} else {
					logger.debug("Entered invalid loan amount=" + amount + "|Mobile Number=" + mobileNo + "|minAmount="
							+ minAmount + "|maxAmount=" + maxAmount + "|Type=charge");
					redir.addFlashAttribute("mobileno", doPaymentModel.getMobile_no());
					redir.addFlashAttribute("loancode", doPaymentModel.getLoan_code1());
					redir.addFlashAttribute("applicationno", doPaymentModel.getApp_no1());
					redir.addFlashAttribute("customerid", customerName);
					redir.addFlashAttribute("amt_info",
							"Enter amount between " + minAmount + "Rs. - " + maxAmount + "Rs. to do payment.");
					redir.addFlashAttribute("min_amount", minAmount);
					redir.addFlashAttribute("max_amount", maxAmount);
					redir.addFlashAttribute(Constants.KEY_ERROR_MSG, Constants.MSG_INVALID_AMOUNT);
					httpSession.setAttribute("step_image", applicationConfig.getTransStep1Image());
					redirectView = new RedirectView("/payment", true);
				}
			} else {
				logger.debug("Transaction Reference Details Charges Not Inserted TxnNumber=" + txnNumber + " |Amount="
						+ amount + " |mobileNumber=" + mobileNo + " |LoanCode=" + loanCode);
				// httpSession.setAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
				// applicationConfig.getPgAppTemporarilyUnavailable());
				redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
						applicationConfig.getPgAppTemporarilyUnavailable());
				redirectView = new RedirectView("/payment", true);
			}
		} catch (Exception e) {
			logger.debug("Exception@/doPaymentCharges=" + e);
			redir.addFlashAttribute(Constants.KEY_ERROR__TEMP_UNAVAILABLE_MSG,
					applicationConfig.getPgAppTemporarilyUnavailable());
			redirectView = new RedirectView("/payment", true);
		}
		return redirectView;
	}

	// Resend OTP
	@XxsFilter
	@RequestMapping(value = "/resendOtp", method = RequestMethod.POST)
	@ResponseBody
	public String resendOTP(RedirectAttributes redir, HttpSession httpSession, HttpServletRequest request) {
		String response = "";
		try {
			String otpUrl = applicationConfig.getOtpUrl();
			String otpMSg = applicationConfig.getOtpMsg();
			String mobileNo = httpSession.getAttribute("mobileNumber") != null
					? (String) httpSession.getAttribute("mobileNumber")
					: "8919180283";
			String otpResponse = "";
			String otp = SendSmsOTP.getOtp();
			httpSession.setAttribute("otp", otp);
			String otpData = otpUrl + "&to=" + mobileNo + "&text=" + otpMSg + "%20" + otp;
			System.out.println("Resend OTP Data=" + otpData);
			otpResponse = SendSmsOTP.sendOtpSms(otpData);
			if (otpResponse.contains("200")) {
				response = applicationConfig.getOtpSentMessage();
			} else {
				response = applicationConfig.getOtpUnavailable();
			}
		} catch (Exception e) {
			System.out.println("Exception@resendOTP()=" + e);
			response = applicationConfig.getOtpUnavailable();
		}
		return response;
	}
}
