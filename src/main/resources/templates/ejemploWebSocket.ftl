<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ejemplo Cliente de WebSocket</title>
    <style>
        .rojo {
            color: red;
        }
    </style>
</head>
<body>

<h1>Ejemplos de llamada WebSocket entre el cliente y el servidor</h1>
<div id="mensajeServidor"></div>
<br/>
<input id="mensajeCliente" type="text" name="mensajeCliente">
<button id="boton"  type="button" >Enviar Mensaje al Servidor</button>


<p>
    Abra la herramienta de desarrollador web para visualizar las peticiones que realiza el cliente....
</p>

<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<script>
    //abriendo el objeto para el websocket
    var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/mensajeasfServidor");
    //indicando los eventos:
    webSocket.onmessage = function(data){recibirInformacionServidor(data);};
    webSocket.onopen  = function(e){ console.log("Conectado - status "+this.readyState); };
    webSocket.onclose = function(e){ console.log("Desconectado - status "+this.readyState); alert("Conexión Terminada");  };


    $(document).on("ready", function(){
        console.info("Iniciando Jquery -  Ejemplo WebServices");
        $("#boton").click(function(){
            webSocket.send($("#mensajeCliente").val());
        });
    });

    /**
     *
     * @param mensaje
     */
    function recibirInformacionServidor(mensaje){
        console.log("Recibiendo del servidor: "+mensaje.data)
        $("#mensajeServidor").html(mensaje.data);
    }


</script>
</body>
</html>