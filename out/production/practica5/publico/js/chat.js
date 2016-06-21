/**
 * Created by saleta on 6/15/2016.
 */
$(document).ready(function () {
    var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/mensajeServidor");
    var info = get_info(true);
    webSocket.send(JSON.stringify(info));
});

function get_info (inicializando) {
    var info = {
        inicializando: inicializando,
        usuario_origen:("#usuario").val(),
        usuario_destino: ("#usuario_destino").val(),
        mensaje: ""
    };
    return info
}