
//Personal info form validation
function validatePersonalInfo() {

var  fullName = $("#fullName").val();
var  dob = $("#dob").val();
var  phoneNo = $("#phoneNo").val();
var  fatherName = $("#fatherName").val();
var  gender = $("input[name='gender']:checked").val();
var  emailAddress = $("#emailAddress").val();
var flag=true;

if(fullName!= ""){

  var validName = /^[A-Za-z]*[\s]{0,1}[A-Za-z]*$/;    //regex for valid name, space is included

  if(fullName.length > 30 || !(fullName.match(validName))){
    $("#error-fullName").html("<em>Invalid Entered Name</em>");

    flag = false;
  }

}
if(fatherName!= ""){

  var validName = /^[A-Za-z]*[\s]{0,1}[A-Za-z]*$/;

  if(fatherName.length > 30 || !(fullName.match(validName))){

    $("#error-fatherName").html("<em>Invalid Entered Name</em>");
    flag = false;
  }

}
if(dob!= ""){

  var dob_date = new Date(dob);
  var currentDate = new Date();
  currentDate.setFullYear(currentDate.getFullYear() - 18);
  var reqYear = currentDate.getFullYear();

  if (currentDate.getFullYear() - dob_date.getFullYear() < 18){
    $("#error-dob").html("<em>Minimum Age is 18. Year should be " + reqYear + " or after</em>");
    flag = false;
  }

}
//if(phoneNo!= ""){
//
//  var validNumber = /^[0-9]{10}$/;
//  if(!(phoneNo.match(validNumber))){
//
//    $("#error-phoneNo").html("<em>Invalid Phone Number</em>");
//    flag = false;
//  }
//
//
//}
if(emailAddress!= ""){

  var mailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if(!(emailAddress.match(mailformat))){

    $("#error-emailAddress").html("<em>Invalid Email Address</em>");
    flag = false;
  }
}

 return flag;
}


//Address info form validation
function validateAddressInfo() {

  var addressLine1 = $("#addressLine1").val();
  var addressLine2 = $("#addressLine2").val();
  // var fullAddress = $("#addressLine1").val() + $("#addressLine2").val();
  var city = $("#city").val();
  var state = $("#state").val();
  var zipCode = $("#zipCode").val();
  var flag = true;

  if(addressLine1 != ""){

    if(addressLine1.length > 70){
      $("#error-addressLine1").html("<em>Character limit upto 70</em>");
      flag = false;
    }
  }
  if(addressLine2 != ""){

    if(addressLine2.length > 50){
      $("#error-addressLine2").html("<em>Character limit upto 50</em>");
      flag = false;
    }
  }
  if(city != ""){

    if(city.length > 30){
      $("#error-city").html("<em>Invalid City name</em>");
      flag = false;
    }
  }
  if(state != ""){

    if(state.length > 30){
      $("#error-state").html("<em>Invalid State name</em>");
      flag = false;
    }
  }
  if(zipCode != ""){

    var validCode = /^[0-9]{6}$/;

    if(!(zipCode.match(validCode))){
      $("#error-zipCode").html("<em>Invalid Zip Code</em>");
      flag = false;
    }
  }

  return flag;
}
//Permanent address validation
function validatePermanentAddInfo() {

  var paddressLine1 = $("#PAddressLine1").val();
  var paddressLine2 = $("#PAddressLine2").val();
  // var fullAddress = $("#addressLine1").val() + $("#addressLine2").val();
  var pcity = $("#Pcity").val();
  var pstate = $("#Pstate").val();
  var pzipCode = $("#PzipCode").val();
  var flag = true;

  if(paddressLine1 != ""){

    if(paddressLine1.length > 70){
      $("#error-PAddressLine1").html("<em>Character limit upto 70</em>");
      flag = false;
    }
  }
  if(paddressLine2 != ""){

    if(paddressLine2.length > 50){
      $("#error-PAddressLine2").html("<em>Character limit upto 50</em>");
      flag = false;
    }
  }
  if(pcity != ""){

    if(pcity.length > 30){
      $("#error-Pcity").html("<em>Invalid City name</em>");
      flag = false;
    }
  }
  if(pstate != ""){

    if(pstate.length > 30){
      $("#error-Pstate").html("<em>Invalid State name</em>");
      flag = false;
    }
  }
  if(pzipCode != ""){

    var validCode = /^[0-9]{6}$/;

    if(!(pzipCode.match(validCode))){
      $("#error-PzipCode").html("<em>Invalid Zip Code</em>");
      flag = false;
    }
  }

  return flag;
}

//Employer info form validation
function validateEmployerInfo() {

  var employerName = $("#empName").val();
  var employerContactNo = $("#empPhoneNumber").val();
  var employerType = $('#employementType :selected').val();
  var fullAddress =	$("#empAddressLine1").val();
  var city = $("#empCity").val();
  var state = $("#empState").val();
  var zipCode = $("#empZipCode").val();
  var flag = true;

  if(employerName!= ""){

    var validName = /^[A-Za-z]*[\s]{0,1}[A-Za-z]*$/;

    if(employerName.length > 30 || !(employerName.match(validName))){
      $("#error-empName").html("<em>Invalid Entered Name</em>");
      flag = false;
    }
  }
  if(employerContactNo!= ""){

    var validNumber = /^[0-9]{10}$/;
    if(!(employerContactNo.match(validNumber))){

      $("#error-empPhoneNumber").html("<em>Invalid Phone Number</em>");
      flag = false;
    }
  }
  if(city != ""){

    if(city.length > 30){
      $("#error-empCity").html("<em>Invalid City name</em>");
      flag = false;
    }
  }
  if(state != ""){

    if(state.length > 30){
      $("#error-empState").html("<em>Invalid State name</em>");
      flag = false;
    }
  }
  if(zipCode != ""){

    var validCode = /^[0-9]{6}$/;

    if(!(zipCode.match(validCode))){
      $("#error-empZipCode").html("<em>Invalid Zip Code</em>");
      flag = false;
    }
  }

  return flag;

}

//Bank info form validation
function validateBankInfo() {

  var accountNo = $("#accountNo").val();
  var bankName = $("#bankName").val();
  var ifscCode = $('#ifscCode').val();
  var branchAddress =	$("#branchAddress").val();
  var bankCity = $("#bankCity").val();
  var bankState = $("#bankState").val();
  var zipCode = $("#zipcode").val();
  var flag = true;

  if(accountNo!= ""){

    if(accountNo.length > 25){
      $("#error-accountNo").html("<em>Invalid Account Number</em>");
      flag = false;
    }
  }
  if(bankName!= ""){

    if(bankName.length > 50){
      $("#error-bankName").html("<em>Invalid Bank Name</em>");
      flag = false;
    }
  }
  if(ifscCode!= ""){

    if(ifscCode.length > 15){
      $("#error-ifscCode").html("<em>Invalid IFSC Code</em>");
      flag = false;
    }
  }
  if(bankCity != ""){

    if(bankCity.length > 30){
      $("#error-bankCity").html("<em>Invalid City name</em>");
      flag = false;
    }
  }
  if(bankState != ""){

    if(bankState.length > 30){
      $("#error-bankState").html("<em>Invalid State name</em>");
      flag = false;
    }
  }
  if(zipCode != ""){

    var validCode = /^[0-9]{6}$/;

    if(!(zipCode.match(validCode))){
      $("#error-zipcode").html("<em>Invalid Zip Code</em>");
      flag = false;
    }
  }

  return flag;

}



