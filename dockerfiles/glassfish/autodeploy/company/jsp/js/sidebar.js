var ip = location.hostname;
var port = location.port;
var contextPath = $('#contextPathHolder').attr('data-contextPath');

$(document).ready(function() {
    //rate on ajax
    // $.ajax({
    //     url: contextPath + "/xslt",
    //     type: "GET",
    //     data: ({}),
    //     dataType: "JSON",
    //     beforeSend: function() {
    //     },
    //     success: function(data) {
    //
    //         var jsonarr = data.valute;
    //
    //             if (jsonarr.length != 0) {
    //
    //                 var appendJson = "<ul style='list-style-type:none;'>";
    //                 for (var i = 0; i < jsonarr.length; i++) {
    //                     appendJson = appendJson + "<li>" + jsonarr[i].charcode + " <span style='padding-left: 0.5em'>"
    //                         + jsonarr[i].value + "</span></li></br>";
    //                 }
    //                 appendJson = appendJson + "</ul>";
    //
    //                 $("#curs").html(appendJson);
    //             }
    //     },
    //     error: function () {
    //
    //     }
    // });

    // rate on websocket
    var wsocket;

    function onMessage(evt) {
        var jsonarr = JSON.parse(evt.data).valute;

        if (jsonarr.length != 0) {

            var appendJson = "<ul style='list-style-type:none;'>";
            for (var i = 0; i < jsonarr.length; i++) {
                appendJson = appendJson + "<li>" + jsonarr[i].charcode + " <span style='padding-left: 0.5em'>"
                    + jsonarr[i].value + "</span></li></br>";
            }
            appendJson = appendJson + "</ul>";

            $("#curs").html(appendJson);
        }
    }


    //geolocation soap
    //it doesn't work because of the soap service webservicex.net is offline
    // $.ajax({
    //     url: contextPath + "/api/v1/geoIP",
    //     type: "GET",
    //     data: ({}),
    //     dataType: "JSON",
    //     beforeSend: function() {
    //     },
    //     success: function(data) {
    //         $("#advertisement").html("Тут находится реклама, учитывающая вашу геолокацию:</br>"
    //             + "Country " + data.country + " and IP " + data.ip);
    //     },
    //     error: function () {
    //
    //     }
    // });

    //geolocation
    // function displayLocation(latitude,longitude){
    //     var request = new XMLHttpRequest();
    //
    //     var method = 'GET';
    //     var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=true';
    //     var async = true;
    //
    //     request.open(method, url, async);
    //     request.onreadystatechange = function(){
    //         if(request.readyState == 4 && request.status == 200){
    //             var data = JSON.parse(request.responseText);
    //             var address = data.results[0];
    //             $("#advertisement").html("<div style='margin-left:40px;'>Тут находится реклама, учитывающая вашу геолокацию:</br>"
    //                 + address.formatted_address + "</div>");
    //         }
    //     };
    //     request.send();
    // };
    //
    // var successCallback = function(position){
    //     var x = position.coords.latitude;
    //     var y = position.coords.longitude;
    //     displayLocation(x,y);
    // };
    //
    // var errorCallback = function(error){
    //     var errorMessage = 'Unknown error';
    //     switch(error.code) {
    //         case 1:
    //             errorMessage = 'Permission denied';
    //             break;
    //         case 2:
    //             errorMessage = 'Position unavailable';
    //             break;
    //         case 3:
    //             errorMessage = 'Timeout';
    //             break;
    //     }
    //     // alert(errorMessage);
    // };
    //
    // var options = {
    //     enableHighAccuracy: true,
    //     timeout: 1000,
    //     maximumAge: 0
    // };
    //
    // navigator.geolocation.getCurrentPosition(successCallback,errorCallback,options);




    //find cities by a country
    //it doesn't work because of the soap service webservicex.net is offline
    // $("#submit_country").click(function () {
    //     var country = $("#country").val();
    //     var url = "http://" + ip + ":" + port + contextPath + "/api/v1/countries/" + country + "/cities";
    //     window.open(url);
    // });

    //news on ajax
    // $.getJSON(contextPath + "/jsoup", function(data) {
    //     var appendJson = "<ul style='list-style-type:none;'>";
    //     for (var i = 0; i < data.length; i++) {
    //         appendJson = appendJson + "<li>" + data[i].name + "</li></br>";
    //     }
    //     appendJson = appendJson + "</ul>";
    //     $("#news").html(appendJson);
    //
    // });

    //news on websocket
    var wsocket2;
    function onMessage2(evt) {
        var jsonarr = JSON.parse(evt.data);
        if (jsonarr.length != 0) {
            var appendJson = "<ul style='list-style-type:none;'>";

            for (var i = 0; i < jsonarr.length; i++) {
                appendJson = appendJson + "<li>" + jsonarr[i].name + "</li></br>";
            }
            appendJson = appendJson + "</ul>";
            $("#news").html(appendJson);
        }
    }

    function connect() {
        wsocket = new WebSocket("ws://" + ip + ":" + port + contextPath + "/rateserver");
        wsocket.onmessage = onMessage;

        wsocket2 = new WebSocket("ws://" + ip + ":" + port + contextPath + "/jsoupserver");
        wsocket2.onmessage = onMessage2;
    }

    connect();
});