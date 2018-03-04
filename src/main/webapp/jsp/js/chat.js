var ws = new WebSocket("ws://localhost:8080/chatserver");

function init() {
    ws.onopen = function(event) {
        console.log("WebSocket is open");
    }

    ws.onmessage = function(event) {
        console.log ("Message via websocket is:", event.data);
        var messages = $("#messages");
        messages.val(messages.val() + event.data + "\n");
    }

    ws.onclose = function(event) {
        console.log ("socket is closed");
    }
}

function sendMessage() {
    var messageField = $("#message");
    var userNameField = $("#username");
    if (typeof userNameField.val() != 'undefined') {
        var message = userNameField.val() + ":" + messageField.val();
        ws.send(message);
        messageField.val("");
    }
}

$(function() {
    init();

    $("input[name='submitmsg']").click(sendMessage);

    $("#message").keypress(function() {
        if ( event.which == 13) {
            event.preventDefault();
            $("input[name='submitmsg']").click();
        }
    });
});