var contextPath = $('#contextPathHolder').attr('data-contextPath');

function makeRequestWithPromise(method, url) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open(method, url);
        xhr.onload = function () {
            if (this.status >= 200 && this.status < 300) {
                resolve(xhr.response);
            } else {
                reject({
                    status: this.status,
                    statusText: xhr.statusText
                });
            }
        };
        xhr.onerror = function () {
            reject({
                status: this.status,
                statusText: xhr.statusText
            });
        };
        xhr.send();
    });
}

$(document).ready(function() {
    var scriptElement = document.getElementById('scriptText');
    scriptElement.addEventListener('blur', function(){

        makeRequestWithPromise('GET', contextPath + '/js?text=' + scriptElement.value)
            .then(function (datums) {
                $('#nashornResult').val(datums);
            })
            .catch(function (err) {
                console.error('Augh, there was an error!', err.statusText);
            });
    });
});