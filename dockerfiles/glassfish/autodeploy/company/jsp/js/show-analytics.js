var ip = location.hostname;
var port = location.port;
var contextPath = $('#contextPathHolder').attr('data-contextPath');

$(document).ready(function() {
    var wsocket2;
    function onMessage2(evt) {
        var jsonarr = JSON.parse(evt.data);

        if (jsonarr.length != 0) {
            var appendJson = "<table>\n" +
                "                <caption><b>Analytics</b></caption>\n" +
                "                <thead>\n" +
                "                <tr>\n" +
                "                    <th>id</th>\n" +
                "                    <th>marker</th>\n" +
                "                    <th>jsp_name</th>\n" +
                "                    <th>ip</th>\n" +
                "                    <th>browser_info</th>\n" +
                "                    <th>client_time</th>\n" +
                "                    <th>server_time</th>\n" +
                "                    <th>login</th>\n" +
                "                    <th>cookie</th>\n" +
                "                    <th>p_id</th>\n" +
                "                </tr>\n" +
                "                </thead>\n" +
                "                <tbody>";

            for (var i = 0; i < jsonarr.length; i++) {
                appendJson = appendJson + "<tr>\n" +
                    "                    <td>" + jsonarr[i].id + "</td>\n" +
                    "                    <td>" + jsonarr[i].marker_name + "</td>\n" +
                    "                    <td>" + jsonarr[i].jsp_name + "</td>\n" +
                    "                    <td>" + jsonarr[i].ip + "</td>\n" +
                    "                    <td>" + jsonarr[i].browser_info + "</td>\n" +
                    "                    <td>" + jsonarr[i].client_time + "</td>\n" +
                    "                    <td>" + jsonarr[i].server_time + "</td>\n" +
                    "                    <td>" + jsonarr[i].login + "</td>\n" +
                    "                    <td>" + jsonarr[i].cookie + "</td>\n" +
                    "                    <td>" + jsonarr[i].prev_id + "</td>\n" +
                    "                </tr>";
            }
            appendJson = appendJson + "<tbody>\n" +
                "                <tfoot>\n" +
                "                <tr>\n" +
                "                    <th colspan=\"10\">отслеживание информации по пользователям</th>\n" +
                "                </tr>\n" +
                "                </tfoot>\n" +
                "            </table>";
            $("#trackingdb").html(appendJson);
        }
    }

    function connect() {
        wsocket2 = new WebSocket("ws://" + ip + ":" + port + contextPath + "/trackingserver");
        wsocket2.onmessage = onMessage2;
    }
    connect();
});