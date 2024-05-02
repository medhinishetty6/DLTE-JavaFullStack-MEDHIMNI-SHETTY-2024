$(document).ready(function () {
    function getUserName() {
        $.ajax({
            type: "GET",
            url: "/card/name",
            dataType: 'text',
            contentType:"application/json;charset=utf-8",

            success: function (response) {
                $('#Username').text("Hey, " + response); // Display the name
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                $('#Username').text("Error fetching name");
            }
        });
    }
    getUserName();
});