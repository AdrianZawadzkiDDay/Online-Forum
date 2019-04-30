$.ajax({
    url: "http://localhost:8080/login",
    method: "post",
    error: function () {
        window.location.href = "login.html";
    }

});

//$("#tu-chce-zeby-byl-login").text(odpowiedzZSerwera);

$.ajax({
    url: "http://localhost:8080/login",
    method: "get",

    success: function(response) {
        $("#name").text(response);
    }
});

function reloadTablePosts() {
    $.ajax({
        url: "http://localhost:8080/posts",
        method: "get",

        success: function (posts) {
            const $trPostsTemplate = $("#tr-posts-template");
            const $tbody = $("#table-posts tbody");
            $tbody.children("tr:not(#tr-posts-template)").remove();

            for (let i = 0; i < posts.length; i++) {
                const post = posts[i];
                const $trPost = $trPostsTemplate.clone();
                $trPost.removeAttr("id");
                $trPost.children(".td-post-id").text(post.id);
                $trPost.children(".td-post-title").text(post.title);
                $trPost.children(".td-post-author").text(post.author.email);

                $tbody.append($trPost);

                console.log(2);
            }
            $("#table-posts tr").on('click', function () {
                const id = $(this).find("td:first-child").text();

                $.ajax({
                    url: "http://localhost:8080/posts/" + id,
                    method: "get",

                    success: function (post) {
                        $("#add-comment").data("post-id", post.id);
                        $("#contentTextarea").val(post.content);

                        const comments = post.comments;

                        const $trComentsTemplate = $("#tr-comments-template");
                        const $tbody = $("#table-comments tbody");
                        $tbody.children("tr:not(#tr-comments-template)").remove();

                        for (let i = 0; i < comments.length; i++) {
                            const comment = comments[i];
                            const $trComment = $trComentsTemplate.clone();
                            $trComment.removeAttr("id");
                            $trComment.children(".td-comment-id").text(comment.id);
                            $trComment.children(".td-comment-content").text(comment.content);
                            $trComment.children(".td-comment-author").text(comment.author.email);

                            $tbody.append($trComment);
                        }

                    }
                });

            });
        }
    });
}

reloadTablePosts();
