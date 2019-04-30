
$("#sign-submit").click(function (event) {
    $(".is-invalid").removeClass("is-invalid");

    event.preventDefault();
    const email = $("#input-email").val();
    const password = $("#input-password").val();

    const user = {
        email: email,
        password: password
    };

    $.ajax({
        url: "http://localhost:8080/users",
        method: "post",
        data: JSON.stringify(user),
        contentType: "application/json",
        success: function () {
            alert("dodano nowego użytkownika");
            window.location.href = "login.html";
        },
        error: function (response) {
            const fieldValidationErrors = response.responseJSON;
            for(let i = 0; i < fieldValidationErrors.length; i++){
                const propertyValidationError = fieldValidationErrors[i];
                $("#input-" + propertyValidationError.property).addClass("is-invalid");
            }
        }
    });
});


function timer() {
    var dzisiaj = new Date();

    var dzien = dzisiaj.getDate();
    if(dzien < 10) dzien = "0" + dzisiaj.getDate();
    var miesiac = dzisiaj.getMonth() + 1;
    if(miesiac < 10) miesiac = "0" + (dzisiaj.getMonth() + 1);
    var rok = dzisiaj.getFullYear();

    var godzina = dzisiaj.getHours();
    if(godzina < 10) godzina = "0" + dzisiaj.getHours();
    var minuta = dzisiaj.getMinutes();
    if(minuta < 10) minuta= "0" + dzisiaj.getMinutes();
    var sekunda = dzisiaj.getSeconds();
    if(sekunda < 10) sekunda = "0" + dzisiaj.getSeconds();

    document.getElementById("zegar").innerHTML = dzien + "/" + miesiac + "/" + rok
        + " | " + godzina + ":" + minuta + ":" + sekunda;

    setTimeout("timer()", 1000);
};