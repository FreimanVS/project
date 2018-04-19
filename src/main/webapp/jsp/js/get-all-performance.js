var ip = location.hostname;
var port = location.port;
var contextPath = $('#contextPathHolder').attr('data-contextPath');

$(document).ready(function() {
    $.ajax({
        url: contextPath + "/performance",
        type: "GET",
        data: ({}),
        dataType: "JSON",
        beforeSend: function() {
        },
        success: function(data) {

            var appendJson ="<table><caption><b>Производительность</b></caption>" +
                "<thead><tr>" +
                "<th>id</th>" +
                "<th>название</th>" +
                "<th>скорость загрузки</th>" +
                "</tr></thead>" +
                "<tbody>";

            for (var i = 0; i < data.length; i++) {
                appendJson = appendJson + "<tr>"
                    + "<td align=\"center\">" + data[i].id + "</td>"
                    + "<td align=\"center\">" + data[i].name + "</td>"
                    + "<td align=\"center\">" + data[i].ms + "</td>"
                    + "</tr>"
            }

            appendJson = appendJson + "</tbody>" +
                "<tfoot>" +
                "<tr>" +
                "<th colspan=\"3\">производительность</th>" +
                "</tr>" +
                "</tfoot>" +
                "</table>";

            $(".allPerformance").html(appendJson);
        },
        error: function () {

        }
    });
});