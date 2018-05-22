function validate(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function validateWithComma(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if ((charCode > 31 && (charCode < 48 || charCode > 57)) && charCode != 46) {
        return false;
    }
    return true;
}

function validateEmail(email)
{
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}
function validateName(name) {
    if (/^[a-z0-9][a-z0-9._\-]*$/.exec(name)) {
        return true;
    }
    return false;
}
function alphanumeric(inputtxt)
{
    var letters = /^[0-9a-zA-Z]+$/;
    if(inputtxt.value.match(letters))
    {
        return true;
    }
    else
    {
        return false;
    }
}