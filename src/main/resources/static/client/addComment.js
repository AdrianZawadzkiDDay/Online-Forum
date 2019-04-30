$.ajax({
    url: "http://localhost:8080/login",
    method: "get",

    success: function(response) {
        $("#user-email").val(response);
    }
});

$("#add-comment").click(function (event) {
    event.preventDefault();

    const postId = $("#add-comment").data("post-id");

    const content = $("#commentTextarea").val();
   // const author = $("#user-email").val();

    $("#table-posts tr").on('click', function () {
        const id = $(this).find("td:first-child").text();
    });

    const comment = {
        content: content,
        postId: postId
    };

    $.ajax({
        url: "http://localhost:8080/comments",
        method: "post",
        data: JSON.stringify(comment),
        contentType: "application/json",
        success: function () {
            alert("dodano nowy komentarz");
        }
    });

});

$