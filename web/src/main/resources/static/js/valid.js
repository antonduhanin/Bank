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
