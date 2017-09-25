


var category = document.getElementById("categoryChips");

function validateCategory(){
  if(password.value != "") {
    category.setCustomValidity("Please enter Category");
  } else {
    confirm_password.setCustomValidity('');
  }
}

category.onchange = validatePassword;
