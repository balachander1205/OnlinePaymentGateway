<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html xmlns:th="https://www.thymeleaf.org">
<head>
	<link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="/css/font-awesome.css" rel="stylesheet">
	<link href="/css/side-slider.css" rel="stylesheet">
	<script src="/js/jquery-1.11.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.validate.min.js"></script>
	<title>DHFL Online Payment</title>	
</head>
<style>
div#sideslider-smartbutton {
    font-size: 13px;
    font-family: inherit;
    padding: 0;
    background: white;
}
.sideslider-tab {
    border-radius: 3px;
}
div#successReceipt {
    background: white;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
form#form_overdue,
form#form_charges {
    height: 250px;
}
.error {
	color: #ed1c24;
    font-size: 12px;
    font-weight: 100;
}
label#lbl_disclaimer {
    color: red;
    font-size: 14px;
    font-weight: 100;
}
table.trans_tbl>tbody > tr > td {
    width: 50%;
    padding-left: 5%;
}
table.trans_tbl {
    border: 1px solid #999999a1;
    width: 80%;
    margin-left: 10%;
}
/*Transaction animation CSS*/
.ame_paystep {
    display: block;
    clear: both;
    margin: 20px auto;
    width: 445px;
}.ame_paystepbg {
    display: block;
    margin: 0px;
    padding: 0px;
}.ame_paystepinfo {
    display: block;
    margin-bottom: 15%;
    margin: 0px;
    padding: 0px;
    font-size: 11px;
    color: #444444;
    text-align: center;
}.step1.active {
    float: left;
    margin: 0;
    padding: 0;
    width: 90px;
}.step2 {
    float: left;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 59%;
}.step3 {
    float: right;
    margin: 0;
    padding: 0;
    width: 90px;
}.ame_paystepbg > img {
    width: 100%;
}
/* End of Transaction animation CSS*/
#resendOtpBtn{
	float:right;
}
img#img_captcha_refresh {
    width: 100px;
    padding: 2px 0px 2px 0px;
}
img#img_captcha {
    width: 70%;
    height: 5%;
    margin-top: 1px;
    border-radius: 3px;
}
thead>tr {
    background-color: #8080800f;
}
thead>tr {
    background-color: #8080800f;
}
label.lbl_captcha {
    font-size: 18px;
    background-color: #ed1c2480;
    color: #fff;
    padding: 5px 10px 5px 10px;
    border-radius: 5px;
    font-family: cursive;
    font-weight: 100;
}
h1 {
    color: #fff;
    font-size: 4.6rem;
    font-weight: 600;
    text-align: center;
    padding-bottom: 1rem;
    text-transform: none;
}
.rightNav {
    display: inline-block;
    float: right;
    padding-top: 1rem;
}span.call {
    float: left;
    display: inline-block;
}span.call>a {
    color: #fff;
    font-weight: bold;
    font-size: 2.4rem;
    padding-left: 3rem;
    padding-right: 4rem;
    display: inline-block;
    position: relative;
    pointer-events: none;
}
p{
	font-size: 1.6rem;
    padding: 0;
    color: #3e3e3e;
    opacity: .5;
    padding: 2rem 0;
    line-height: 2rem;
    font-weight: 400;
    text-align: center;
}
footer .lastFooter {
    background-color: #ecf0f5;
    /* border-top: 1px solid #d6d6d6;
    padding-bottom: 6.5rem; */
    text-align: center;
}
.col-md-8.form-group.details_frm {
    padding: 15px 15px 15px 15px;
    border: 1px #00000014 solid;
    /* margin-top: 20px; */
    border-top-color: #00a65a;
    background-color: #ecf0f5;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
body {
	background-attachment: fixed;
    background-color: #ecf0f5;
    background-image: url('/images/background1.jpg');
    background-position: top;
    background-repeat: no-repeat;
}
.qrcode_img {
    width: 50%;
}
.form-control {
	display: inherit;
}
th, td {
    font-size: 13px;
    font-family: inherit;
}
.box-title {
    display: inline-block;
    font-size: 18px;
    margin: 0;
    line-height: 1;
    padding-bottom: 5px;
}
.div_amt_details {
    color: black !important;
    width: 16px;
    height: 16px;
    text-align: center;
    border-radius: 16px;
    font-size: 12px;
    border: #00000045 solid 1px;
    font-weight: bolder;
    margin-top: 10px;
    background: #252525;
}
.div_amt_details>a {
    color: white;
    font-weight: 900;
    font-family: monospace;
}
button.glyphicon.glyphicon-search.btn.btn-primary.mb-2 {
    background-color: #428bca;
    border: #428bca;
    padding: 10px 10px 10px 10px;
}
button.glyphicon.btn.btn-primary.mb-2 {
    background-color: #ed1c24;
    border: #ed1c24;
    padding: 10px 10px 10px 10px;
}
form.form-group.details_frm {
	padding: 15px 15px 15px 15px;
    border: 1px #00000014 solid;
    /* margin: 20px; */
    border-top-color: #00a65a;
    background-color: #ecf0f5;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #ed1c24;
    box-shadow: 0 10px 10px rgba(0,0,0,.05);
}
body{
	background-color: #ecf0f5;
}
.row {
    margin-right: 0;
    margin-left: 0;
}
</style>
<body>
<div class="sideslider" id="sideslider" style="margin-left: -265px;">
    <div class="sideslider-tab"><b>Help ?</b></div>
    <a href="#">
        <div id="sideslider-smartbutton">
            <div id="sideslider-text">
                <span class="header" style="color: #ed1c24;">Welcome</span>
                <a href="/download/pdf/selfcare"><span class="line" style="color:#25408e;"><b>Download self-care guide</b></span></a> 
                 <a href="https://www.dhfl.com/contact-us" target="_blank"><span class="line" style="color:#001ffd;"><b>Contact US</b></span></a> 
               
            </div>
            <div class="sideclear"></div>
        </div>

    </a>
    <div class="sideslider-close sideslider-close_en">Close&nbsp;X</div>
</div>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-sm bg-light navbar-light static-top">
		<img style="margin: 25px;" src="/images/logo.png" alt="Logo"
			style="width:40px;">
		<div class="rightNav">
			<span class="call"> <a href="tel:1800223435">1800 22 3435</a>
			</span>
		</div>
	</nav>
	<div class="row ">
		<div class="col-md-12">
			<!-- <div class="col-md-8">
    			<h1>Pay Overdue EMI, Charges online</h1>
			</div> -->
			<!-- search fields  -->
			<div class="col-md-4" id="div_search_form">
				<form id="form_search" method="post" action="/getOtpDetails"
					class="form-group details_frm">
					<div class="row">
						<div class="">
							<div class="">
								<div class="form-group">
									<input type="radio" name="search_param" id="brLoanCodeParam" 
										value="brloancode" checked="true">
									<label for="contain">11 Digit Unique Loan Code</label>
									<input type="radio" name="search_param" id="appNoParam" value="applno">
									<label for="contain">Application Number</label> 
									<input required	class="form-control" id="brLoanCode" type="text"
										name="brLoanCode" value="${brLoanCode}" placeholder="Enter 11 digit Unique Loan Code" maxlength="11"/>
								</div>
							</div>							
							<div class="">
								<div class="form-group">
									<label for="contain">Mobile Number *</label> <input required
										class="form-control" id="mobileNumber" type="text"
										name="mobileNumber" value="${mobileNumber}" placeholder="Enter Mobile Number" maxlength="10"/>
								</div>
							</div>
							<div class="">
								<div class="form-group">
									<!-- Captcha div -->
									<div class="">
										<img id="img_captcha" src="${pageContext.request.contextPath }/captcha">
										<img id="img_captcha_refresh" src="images/refresh.png">
									</div>
									<!-- <div class="">
										<img id="img_captcha_refresh" src="images/refresh.png">
									</div> -->
									<div class="">
										<input required="true" class="form-control" id="captcha"
											type="text" name="captcha" value=""
											placeholder="Captcha" maxlength="6">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label for="contain"></label>
									<!-- data-toggle = "modal" data-target = "#otpValidationModal" -->
									<button type="submit" class="btn btn-primary mb-2">
										<span class="" aria-hidden="true">Get OTP</span>
									</button>
								</div>
							</div>
							<!-- Displaying OTP Unavailable error message -->
							<c:if test="${noOtpErrormsg !=null}">
								<div style="text-align: center; color:red;">${noOtpErrormsg}</div>
							</c:if>
							<!-- Displaying captcha error message -->
							<c:if test="${captchaError !=null}">
								<div style="text-align: center;color:red;">${captchaError}</div>
							</c:if>
							<!-- Displaying invalid search error message -->
							<c:if test="${invalidSearchErrorMsg !=null}">
								<div style="text-align: center; color:red;">${invalidSearchErrorMsg}</div>
							</c:if>
							<!-- Application temporarily unavailable error -->
							<c:if test="${errorTempUnavailableMsg !=null}">
								<div style="text-align: center; color:red;">${errorTempUnavailableMsg}</div>
							</c:if>
							<!-- Displaying OTP Sent message -->
							<c:if test="${otpSentResponse !=null}">
								<script>
									$('#form_search :input').prop('disabled',true);
									$('#brLoanCode').prop('disabled', true);
								</script>
								<div id="otpSentResponse" style="text-align: center; color:green;">${otpSentResponse}</div>
							</c:if>
							<!-- Search parameter type -->
							<c:if test="${searchType == 'brLoanCodeParam'}">
								<script>
									console.log("Search Type="+${searchType});
									$('#brLoanCodeParam').prop('checked',true);</script>
							</c:if>
							<c:if test="${searchType == 'appNoParam'}">
								<script>
									console.log("Search Type="+${searchType});
									$('#appNoParam').prop('checked',true);</script>
							</c:if>					
						</div>
					</div>
				</form>
			</div>
			<!-- OTP Validation form -->
			<c:if test="${otpResponse != null}">
				<div class="col-md-4" id="otpValidateForm">
					<form id="form_validate_otp" method="post" action="/getdetails"
						class="form-group details_frm">
						<div class="row">
							<div class="">
								<div class="">
									<div class="form-group">
										<label for="contain">OTP</label><label style="font-size:12px">&nbsp;&nbsp;(Enter OTP sent to mobile.)</label> <input required
											class="form-control" id="otpData" type="text" name="otpData" maxlength='4' placeholder="Enter OTP"/>
									</div>
								</div>
								<div class="">
									<div class="form-group"></div>
								</div>
								<div class="">
									<div class="form-group">
										<label for="contain"></label>
										<button type="submit"
											class="btn btn-primary mb-2">
											<span class="" aria-hidden="true">Validate OTP</span>
										</button>
										<button id="resendOtpBtn" type="button"
											class="btn btn-primary mb-2">
											<span class="" aria-hidden="true">Resend OTP</span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<!-- Displaying error message -->
						<c:if test="${otperrormsg !=null}">
							<script>
								$('#form_search :input').prop('disabled', true);
								$('input[id^="brLoanCode"]').prop('disabled', true);
							</script>
							<div style="text-align: center;color:red;">${otperrormsg}</div>
						</c:if>
					</form>
				</div>
			</c:if>
			<!-- End of OTP Validation Form -->
			<!--payment gateway status message -->
			<c:if test="${status_msg !=null}">
				<!-- Disabling Search form  -->
				<script>
					$('#div_search_form').empty();
					$('#div_search_form').css({'display':'none'});
				</script>							
				<div class="col-md-12 form-group details_frm" id="successReceipt">
					<!-- For successful transaction -->			
					<div class="col-md-12">
						<div style="text-align: center">${status_msg}<label>&nbsp;&nbsp;${txnStatus}</label></div>
						<!-- Transaction details table -->
						<c:if test="${status_msg !=null}">
							<table class="table trans_tbl">
								<tbody>
									<tr>
										<td>Name</td>
										<td>${custName}</td>
									</tr>
									<tr>
										<td>Mobile Number</td>
										<td>${mobileNo}</td>
									</tr>
									<tr>
										<td>Transaction ID</td>
										<td>${txnId }</td>
									</tr>
									<tr>
										<td>Loan Code</td>
										<td>${loanCode }</td>
									</tr>
									<tr>
										<td>Amount Paid</td>
										<td>${txnAmount}&nbsp;Rs.</td>
									</tr>
									<tr>
										<td>Transaction Time</td>
										<td>${txnTime}</td>
									</tr>
									<tr>
										<td>Status</td>
										<td>${txnStatus}</td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<div class="">
							<div class="form-group">
								<button type="submit" id="" onclick="javascript:window.print();"
									class="btn btn-primary mb-2">
									<span class="" aria-hidden="true">PRINT</span>
								</button>
								<button onclick="window.location='/payment';" type="button" id="btn_make_another_pay" class="btn btn-primary mb-2">
									<span class="" aria-hidden="true">MAKE ANOTHER PAYMENT</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<!-- For failure transaction -->
			<c:if test="${status_msg_fail !=null}">
				<script>
					$('#div_search_form').empty();
					$('#div_search_form').css({'display':'none'});
				</script>
				<div class="col-md-12 form-group details_frm" id="successReceipt">
					<div class="col-md-12">
						<div style="text-align: center">${status_msg_fail}<label>&nbsp;&nbsp;${txnStatus}</label></div>
						<!-- Transaction details table -->
						<c:if test="${status_msg_fail !=null}">
							<table class="table trans_tbl">
								<tbody>
									<tr>
										<td>Name</td>
										<td>${custName}</td>
									</tr>
									<tr>
										<td>Mobile Number</td>
										<td>${mobileNo}</td>
									</tr>
									<tr>
										<td>Transaction ID</td>
										<td>${txnId }</td>
									</tr>
									<tr>
										<td>Loan Code</td>
										<td>${loanCode }</td>
									</tr>
									<tr>
										<td>Amount Paid</td>
										<td>${txnAmount}&nbsp;Rs.</td>
									</tr>
									<tr>
										<td>Transaction Time</td>
										<td>${txnTime}</td>
									</tr>
									<tr>
										<td>Status</td>
										<td>${txnStatus}</td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<div class="">
							<div class="form-group">
								<label for="contain"></label>
								<!-- <button type="submit" id="printReceipt"
									class="glyphicon glyphicon-print btn btn-primary mb-2">
									<span class="" aria-hidden="true">Print</span>
								</button> -->
								<button type="submit" id="" onclick="javascript:window.print();"
									class="glyphicon glyphicon-print btn btn-primary mb-2">
									<span class="" aria-hidden="true">Print</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<!-- Transaction flow animation -->
	<div class="row">
		<div class="col-md-12">
			<div class="ame_paystep">
				<div class="ame_paystepbg">
					<!-- <img src="/images/order1.png"> -->
					<%-- <c:if test="${ step_image!=null}">
						<img src="${step_image}">
					</c:if> --%>					
					<%-- <img src="${step_image_final}" style="display:${display1}"/> --%>
				</div>
				<%-- <c:if test="${ step_image!=null}">
					<div class="ame_paystepinfo">
						<div class="step1 active">
							Provide your <br> Details
						</div>
						<div class="step2">
							Do <br> Payment
						</div>
						<div class="step3">
							Receive Online <br> Confirmation
						</div>
					</div>
				</c:if> --%>
			</div>
		</div>
	</div>
	<!-- Displaying error message -->
	<c:if test="${errormsg !=null}">
		<div style="text-align: center">${errormsg}</div>
	</c:if>
	<!--  Loan Details Row -->
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
			<c:if test="${loancode != null}">
					<script>
						//$('#form_search :input').prop('disabled', true);
						//$('input[id^="brLoanCode"]').prop('disabled', true);
					</script>
					<form class="form-group details_frm" >
					<h3 class="box-title">Loan Details</h3>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Loan Code</th>
								<th scope="col">Application Number</th>
								<th scope="col">Customer Name</th>
								<!-- <th scope="col">Mobile</th> -->
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									${loancode}
									<input type="hidden" name="loan_code" value="${loancode}"/>
								</td>
								<td>
									${applicationno}
									<input type="hidden" name="app_no" value="${applicationno}"/>
								</td>
								<td>
									${customerid}
									<input type="hidden" name="cust_id" value="${customerid}"/>
								</td>
								<%-- <td>
									${mobileno}
									<input type="hidden" name="mobile_no" value="${mobileno}"/>
								</td> --%>
							</tr>
						</tbody>
					</table>
				</form>
			</c:if>
			</div>
		</div>
	</div>
	<!-- End of Loan details row -->
	<!-- Over due EMI table -->
	<div class="row">
		<div class="col-md-12">
			<%-- <c:if test="${divClass}">
				<div class="${divClass}">
			</c:if>	 --%>
			<div class="${divClass}">
			<c:if test="${TotalOverdueEMI != null}">
				<form method="post" action="/dopayment" class="form-group details_frm" id="form_overdue">
						<h3 class="box-title">
							Overdue EMI <label id="lbl_disclaimer" style="color: red">&nbsp;**Only
								one overdue amount to be selected per transaction</label>
						</h3>
						<table class="table">
						<thead>
							<tr>
								<th scope="col">Total Over Due</th>
								<th scope="col">Minimum Overdue Amount</th>
								<th scope="">Amount between Minimum, Total Over Due</th>
								<th scope=""></th>
								<th scope=""></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="radio" name="amount" value="${TotalOverdueEMI}">&nbsp;${TotalOverdueEMI}</input>
									<input type="hidden" name="TotalOverdueEMI" value="${TotalOverdueEMI}" />
								</td>
								<td>
									<input type="radio" name="amount" value="${MinimumOverdueAmount}">&nbsp;${MinimumOverdueAmount}</input>
									<input type="hidden" name="MinimumOverdueAmount" value="${MinimumOverdueAmount}"/>
								</td>
								<td>
									<input type="radio" name="amount" id="pay_emi" value="" />
										<input required min="${min_amount}" max="${max_amount}" class="form-control" type="number" 
										name="amount_to_pay" id="pay_emi_text" style="display: inherit;width: 50%;" placeholder="Enter Amount to Pay" />						
								</td>
								<td>
									<div class="div_amt_details">
										<a href="#" data-toggle="tooltip" title="${amt_info}" class="amot_info">i</a>
									</div>
								</td>							
							</tr>
						</tbody>
					</table>
						<button type="submit" class="btn btn-primary mb-2" name="playOverDue">
							<span class="" aria-hidden="true">Click To Pay</span>
						</button>
					</form>
			</c:if>
			</div>
			<!-- Charges Over Due  -->
			<div class="${divClass}">		
			<c:if test="${TotalChargesAmount != null}">
				<form method="post" action="/doPaymentCharge" class="form-group details_frm" id="form_charges">
						<h3 class="box-title">Charges 
							<label id="lbl_disclaimer" style="color: red">&nbsp;**Only one charge amount to be
							selected per transaction</label></h3>
						<table class="table">
						<thead>
							<tr>
								<th scope="col">Total Charges Amount</th>
								<th scope="col">Minimum Charge Amount</th>
								<th scope="">Amount between Minimum, Total Charges</th>
								<th scope=""></th>
								<th scope=""></th>								
							</tr>
						</thead>
						<tbody>						
							<tr>
								<td>
									<input type="radio" name="amount_to_pay1" value="${TotalChargesAmount}">&nbsp;${TotalChargesAmount}</input>
									<input type="hidden" name="TotalChargesAmount" value="${TotalChargesAmount}" />
								</td>
								<td>
									<input type="radio" name="amount_to_pay1" value="${MinimumChargeAmount}">&nbsp;${MinimumChargeAmount}</input>
									<input type="hidden" name="MinimumChargeAmount" value="${MinimumChargeAmount}" />
								</td>
								<td>
									<input type="radio" id="charge_to_pay_r" name="amount_to_pay1" value="" />
										<input required min="${min_amount_charge}" id="charge_to_pay" max="${max_amount_charge}" class="form-control" type="number" 
										name="amount_to_pay_charge" style="display: inherit;width: 50%;" placeholder="Enter Amount to Pay" />								
								</td>
								<td>
									<div class="div_amt_details">
										<a href="#" data-toggle="tooltip" title="${amt_info_charge}" class="amot_info">i</a>
									</div>
								</td>							
							</tr>
						</tbody>
					</table>
						<button type="submit" class="btn btn-primary mb-2" name="playOverDueCharge">
							<span class="" aria-hidden="true">Click To Pay</span>
						</button>
					</form>
			</c:if>
			</div>
		</div>
	</div>
	<footer>
		<div class="lastFooter">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<p>
							<strong>Disclaimer </strong>- " As regards deposit taking
							activity of the company, the viewers may refer to the
							advertisement in the newspaper / information furnished in the
							application form for soliciting public deposits; published on
							June 28, 2018 in the Free Press Journal and in Navshakti, Mumbai,
							respectively. The Company is having a valid Certificate of
							Registration dated 31/07/2001 issued by the National Housing Bank
							under Section 29A of the National Housing Bank Act, 1987.
							However, the National Housing Bank does not accept any
							responsibility or guarantee about the present position as to the
							financial soundness of the company or for the correctness of any
							of the statements or representations made or opinions expressed
							by the company and for repayment of deposits / discharge of the
							liabilities by thecompany."
						</p>
					</div>
				</div>		
			</div>
		</div>
	</footer>
</body>
<script type='text/javascript'>
    // Tool tip logic
    $(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
	    // --- Disabling click to pay on submit
	    $(':input[name="playOverDue"]').prop('disabled', true);
	    $(':input[name="playOverDueCharge"]').prop('disabled', true);
	    
	    // on change of "Charges to Pay" input text
	    $('#charge_to_pay').keyup(function() {
	    	var originalvalue = $("#charge_to_pay").val();
	    	$("#charge_to_pay_r").text(originalvalue);
	   	});
	   	$('#pay_emi_text').keyup(function() {
	    	var originalvalue = $("#pay_emi_text").val();
	    	$("#pay_emi").text(originalvalue);
	   	});
	    // Disabling search fields on ready state
	   	if($('input[id^="brLoanCodeParam"]').is(':checked')){
			$(':input[id="brLoanCode"]').prop('disabled', false);
			$(':input[id="applNo"]').prop('disabled', true);
		}	
		// Disabling amount input fields
	   	$("#pay_emi_text").prop('disabled', true); 
    	$("#charge_to_pay").prop('disabled', true);
	});
    $(function () {
        var $inputs = $('input[name=brLoanCode],input[name=applNo]');
        $inputs.on('input', function () {
            // Set the required property of the other input to false if this input is not empty.
            $inputs.not(this).prop('required', !$(this).val().length);
        });
    });
    // ------------- Selsction of overdue amount
    $('input[name^="amount"]').change(function () {
    	setOverDueEmiBlock();
    	/*$(':input[name="playOverDue"]').prop('disabled', false);
    	$(':input[name="playOverDueCharge"]').prop('disabled', true);
    	$("#pay_emi_text").prop('disabled', false);    	
    	if($('input[name^="amount_to_pay1"]').is(":checked")){
    		$('input[name^="amount_to_pay1"]').prop('checked', false);
        }*/
    	//$('input[id^="amount"]').prop('checked', true);
    });
	// ------------- Selection of Charges
    $('input[name^="amount_to_pay1"]').change(function () {
    	setOverDueChargesBlock();
    	/*$(':input[name="playOverDue"]').prop('disabled', true);
    	$(':input[name="playOverDueCharge"]').prop('disabled', false);
    	$("#charge_to_pay").prop('disabled', false);
    	if($('input[name^="amount"]').is(":checked")){
    		$('input[id^="amount"]').prop('checked', false);
        }*/
    	//$('input[name^="amount_to_pay1"]').prop('checked', true);
    });
    // ----------------
    /*$('input[id^="pay_emi"]').change(function(){
    	console.log("Checked pay_emi_text");
    	$("#pay_emi_text").prop('disabled', false);
		$(':input[name="playOverDue"]').prop('disabled', false);
  		$(':input[name="playOverDueCharge"]').prop('disabled', true);
  		$("#charge_to_pay").prop('disabled', true);
  		$('input[name^="amount_to_pay1"]').prop('checked', false);
    });$('input[name^="amount_to_pay1"]').change(function () {
    	$(':input[name="playOverDueCharge"]').prop('disabled', false);
    	$(':input[name="playOverDue"]').prop('disabled', true);
    	$("#charge_to_pay").prop('disabled', false);
    	$("#pay_emi_text").prop('disabled', true);
    	$('input[id^="pay_emi"]').prop('checked', false);
    });*/
    /*if(!$('input[id^="pay_emi"]').is(':checked')){
    	$("#charge_to_pay").prop('disabled', true);
    	$("#pay_emi_text").prop('disabled', true);
	}*/
	// -------------- Function type 1 -----------------
  	function setOverDueEmiBlock(){
		$(':input[name="playOverDue"]').prop('disabled', false);
  		$(':input[name="playOverDueCharge"]').prop('disabled', true);
  		$("#pay_emi_text").prop('disabled', true);
  		$("#charge_to_pay").prop('disabled', true);
  		if($('input[id^="pay_emi"]').is(':checked')){
	    	console.log("Checked pay_emi_text");
	    	$("#pay_emi_text").prop('disabled', false);
	    	//$("#charge_to_pay").prop('disabled', true);
	    	//$(':input[name="playOverDue"]').prop('disabled', false);
		    /*$('input[name="amount_to_pay"]').keyup(function() {
		       if($(this).val() != '') {
		          $(':input[name="playOverDue"]').prop('disabled', false);
		       }
		    });*/
	    }if(!$('input[id^="pay_emi"]').is(':checked')){
	    	$("#charge_to_pay").prop('disabled', true);
	    	$("#pay_emi_text").prop('disabled', true);
		}
	}  	

  	function setOverDueChargesBlock(){
		$(':input[name="playOverDueCharge"]').prop('disabled', false);
    	$(':input[name="playOverDue"]').prop('disabled', true);
    	$("#charge_to_pay").prop('disabled', true);
    	$("#pay_emi_text").prop('disabled', true);
  		if($('input[id^="charge_to_pay_r"]').is(':checked')){
	    	console.log("Checked charge_to_pay");
	    	$("#charge_to_pay").prop('disabled', false);
	    	//$("#pay_emi_text").prop('disabled', true);
	    	//$(':input[name="playOverDueCharge"]').prop('disabled', false);
		    /*$('input[name="amount_to_pay_charge"]').keyup(function() {
		       if($(this).val() != '') {
		          $(':input[name="playOverDueCharge"]').prop('disabled', false);
		       }
		    });*/
	    }if(!$('input[id^="charge_to_pay_r"]').is(':checked')){
	    	$("#charge_to_pay").prop('disabled', true);
	    	$("#pay_emi_text").prop('disabled', true);
		}
	}
	// ------------------- End of function type 1 ----------------

	function setOverDue(){
		$(':input[name="playOverDueCharge"]').prop('disabled', true);
    	$(':input[name="playOverDue"]').prop('disabled', false);
    	$("#pay_emi_text").prop('disabled', true);
    	//$('input[name^="amount_to_pay1"]').removeAttr('checked');
    	$('input:radio[name="amount_to_pay1"]').each(function () { $(this).attr('checked', false); });
    	console.log("Changed overdue radio1");
    	if($('input[id^="pay_emi"]').is(':checked')){
    		console.log("Selected overdue amount");
    		$("#pay_emi_text").prop('disabled', false);
        }if(!$('input[id^="pay_emi"]').is(':checked')){
        	console.log("Not Selected overdue amount");
    		$("#pay_emi_text").prop('disabled', true);
        }
	}function setCharges(){
		$(':input[name="playOverDueCharge"]').prop('disabled', false);
    	$(':input[name="playOverDue"]').prop('disabled', true);
    	$("#charge_to_pay").prop('disabled', true);
    	//$('input[name^="amount"]').removeAttr('checked');
    	$('input:radio[name="amount"]').each(function () { $(this).attr('checked', false); });
    	console.log("Changed charges radio1");
    	if($('input[id^="charge_to_pay_r"]').is(':checked')){
    		console.log("Selected overdue amount");
    		$("#charge_to_pay").prop('disabled', false);
        }if(!$('input[id^="charge_to_pay_r"]').is(':checked')){
        	console.log("Not Selected charges amount");
    		$("#charge_to_pay").prop('disabled', true);
        }
	}
	
	
	function printData(divName) {
		var printContents = document.getElementById(divName).innerHTML;
		var originalContents = document.body.innerHTML;
		document.body.innerHTML = printContents;
		window.print();
		document.body.innerHTML = originalContents;
	}

	/*$('#printReceipt').on('click', function() {
		printData();
	})*/

	// Changing Placeholder text
	$('input[name^="search_param"]').change(
		function() {
			if ($('input[id^="brLoanCodeParam"]').is(':checked')) {
				$("input[id^='brLoanCode']").attr('placeholder',
						'Enter 11 digit loan code');
				$("input[id^='brLoanCode']").attr('maxlength', '11');
			}
			if ($('input[id^="appNoParam"]').is(':checked')) {
				$("input[id^='brLoanCode']").attr('placeholder',
						'Enter Application Number');
				$("input[id^='brLoanCode']").attr('maxlength', '8');
			}
		});
	// Refresh Captcha
	$('#img_captcha_refresh').click(function() {
		var d = new Date();
		//console.log("Clicked captcha refresh="+d);
		$('#img_captcha').attr('src', '/captcha?' + d.getTime());
	});
	// onclick resend OTP
	$("#resendOtpBtn").click(function(){
		$('#form_search :input').prop('disabled', false);
		$('input[id^="brLoanCode"]').prop('disabled', false);
		$('#otpValidateForm').empty();
		$('#otpSentResponse').empty();
	});
	// Search Form validation

	$("#form_search").validate({
		rules : {
			brLoanCode : "required",
			captcha : "required",
			mobileNumber : {
				required : true,
				digits : true,
				minlength : 10,
				maxlength : 10,
			},
			password : {
				required : true,
				minlength : 5,
			}
		},
		messages : {
			brLoanCode : {
				required : "Enter Application Number/Loan Code.",
			},
			captcha : {
				required : "Enter Captcha",
			},
			mobileNumber : {
				required : "Please enter phone number",
				digits : "Please enter valid phone number",
			}
		}
	});
	// End of Search form validation
	// OTP form validation
	$("#form_validate_otp").validate({
		rules : {
			otpData : {
				required : true,
				digits : true,
				minlength : 4,
				maxlength : 4,
			}
		},
		messages : {
			otpData : {
				required : "Enter OTP",
			}
		}
	});
	// End of OTP form validation
	// Overdue form validation
	$("#form_overdue").validate({
	  rules : {
	    pay_emi_text : {
	      required : true,
	      digits : true
	    }
	  },
	  messages : {
	    pay_emi_text : {
	      required : jQuery.format("Enter amount between maximum and minimum."),
	    }
	  }
	});
	// End of Overdue form validation
	// Overdue Charges form validation
	$("#form_charges").validate({
		rules : {
			charge_to_pay : {
				required : true,
				digits : true
			}
		},
		messages : {
			charge_to_pay : {
				required : jQuery.format("Enter amount between maximum and minimum."),
			}
		}
	});
	// End of Overdue Charges form validation
	jQuery.extend(jQuery.validator.messages, {
	    //maxlength: jQuery.validator.format("Enter amount between maximum and minimum."),
	    //minlength: jQuery.validator.format("Enter amount between maximum and minimum."),
	    max: jQuery.validator.format("Enter amount between maximum and minimum."),
	    min: jQuery.validator.format("Enter amount between maximum and minimum.")
	});
</script>
<script src="/js/jquery.side-slider.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sideslider').sideSlider();

    });
</script>
</html>