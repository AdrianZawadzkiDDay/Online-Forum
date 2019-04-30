$.ajax({
    url: "http://localhost:8080/login",
    method: "get",

    success: function(response) {
        $("#name").text(response);
        $("#name1").val(response);
    }
});

$("#add-submit").click(function (event) {
    event.preventDefault();

    const title = $("#title").val();
    const content = $("#textArea").val();

    const post = {
        title: title,
        content: content
    };

    $.ajax({
        url: "http://localhost:8080/posts",
        method: "post",
        data: JSON.stringify(post),
        contentType: "application/json",
        success: function () {
            alert("dodano nowy post");
            window.location.href = "main.html";
        }
    });

});