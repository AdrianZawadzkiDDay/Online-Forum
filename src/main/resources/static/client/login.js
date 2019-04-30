$("#login-submit").click(function (event) {
    event.preventDefault();
    const username = $("#input-email").val();
    const password = $("#input-password").val();
    $.ajax({
        url: "http://localhost:8080/login",
        method: "post",
        headers: {
            "Authorization": "Basic " + btoa(username + ":" + password)
        },
        success: function() {
            window.location.href = "main.html";
        },
        error: function() {
            //alert("błędne dane logowania");
            document.getElementById("mistake").innerHTML = "niepoprawne dane logowania";
        }
    });
});

$("#button-logout").click(function (event) {
    event.preventDefault();

    $.ajax({
        url: "http://localhost:8080/logout",
        method: "post",
        success: function() {
            window.location.href = "login.html";
        }
    });
});