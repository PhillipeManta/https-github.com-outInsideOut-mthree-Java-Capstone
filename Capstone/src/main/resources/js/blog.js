$(document).ready(function() {
    
    $("#fiteredPosts").hide();
    
    $("#addButton").click(function() {
       $("#filteredPosts").show();
       $("#unfilteredPosts").hide();
    });
    
    $("#addButton").click(function() {
       $("#filteredPosts").html(
            "<span th:each=\"blogPost : ${FilterdPosts}\">" +
                "<span th:unless=\"${blogPost.isPosted}\">" +
                  "<span class=\"container row blogPost my-5\">" +
                    "<div class=\"row title-row\">" +
                      "<h3 th:text=\"blogPost.title\">Blog 1</h3>" +
                    "</div>" +
                    "<div class=\"row body-row\">" +
                      "<p th:text=\"blogPost.post\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam maximus nunc nec pulvinar fringilla. Nam in eros eros. Duis est urna, fermentum ac rutrum non, elementum et ex. Pellentesque convallis aliquam nunc sed placerat. Fusce eu elit at ligula malesuada ultrices. Suspendisse nisl arcu, interdum nec luctus in, sodales id velit. Vestibulum viverra, justo non facilisis malesuada, metus orci congue orci, id tristique dui tortor ac erat</p>" +
                    "</div>" +
                    "<div class=\"row img-row mx-auto img-fluid\">" +
                      "<img class=\"rounded mx-auto d-block\" th:src=\"blogPost.imageURL\">" +
                    "</div>" +
                    "<div class=\"row date-row\">" +
                      "<p th:text=\"blogPost.date\">02/11/1678</p>" +
                    "</div>" +
                    "<div class=\"row admin\">" +
                      "<button class=\"btn btn-warning\">DELETE</button>" +
                      "<button class=\"btn btn-success\">APPROVE</button>" +
                    "</div>" +
                    "</span>" +
                    "<hr>" +
                  "</span>" +
                "</span>" 
        );

    });
});


