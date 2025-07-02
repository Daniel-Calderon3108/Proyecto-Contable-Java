function message(tipo, mensaje) {
    Swal.fire({
        icon: tipo,
        title: 'Sacart Asociados',
        text: mensaje
    });
}

function traerProducto() {
    let params = {
        'opcion': 'traerProducto',
        'idProducto': $('#idProducto').val(),
        'idVenta': $('#idVenta').val()
    };

    $.ajax({
        url: "Venta",
        type: "GET",
        dataType: 'json',
        data: params,
        success: function (response) {
            if (response.error === "false") {
                actualizarDetalle();
            } else {
                console.log(response.mensaje);
            }
        }
    });
}

function actualizarDetalle() {
    let params = {
        'opcion': 'actualizarDetalle',
        'idVenta': $('#idVenta').val()
    };

    $.ajax({
        url: 'Venta',
        type: 'GET',
        datatype: 'json',
        data: params,
        success: function (response) {
            var producto = ``;
            var productsTotal = ``;
            if (response.error === "false") {
                sumaTotal = 0;
                multi = 0;
                response.data.forEach(element => {
                    producto += `
                                <div class="product-form">
                                    <div style="text-align: right; padding: 2px 5px;">
                                        <a href="#" onclick="eliminarProducto(${element.idProducto})">
                                            <i class="fa-sharp fa-solid fa-circle-xmark" style="color: #e93a3a;"></i>
                                        </a>
                                    </div>
                                    <div class="form-body">
                                      <label>Producto</label>
                                      <input type="text" name="producto" value="${element.producto}" readonly="readonly">
                                   </div>
                                   <div class="form-body">
                                     <label>Precio Unidad</label>
                                     <input type="text" name="precioUnidad" id="precio-${element.idProducto}" value="${element.precio}" readonly="readonly">
                                   </div>
                                   <div class="form-body">
                                     <label>Cantidad</label>
                                     <div class="valor-cantidad">
                                        <i class="fa-solid fa-minus" onclick="restarCantidad('${element.idProducto}')"></i>
                                        <input type="number" value="${element.cantidad}" name="cantidad" id="cantidad-${element.idProducto}" readonly="readonly">
                                        <i class="fa-solid fa-plus" onclick="sumarCantidad('${element.idProducto}')"></i>
                                    </div>
                                     <input type="hidden" name="idProducto" value="${element.idProducto}">
                                   </div>
                               </div>`;
                    multi = element.cantidad * element.precioUnidad;

                    productsTotal += `<p>${element.producto}: $${multi}</p>`;

                    sumaTotal = sumaTotal + multi;

                });
                $('#productosAgregados').html(producto);
                $('#products-total').html(productsTotal);
                $('#total').html(sumaTotal);
            }
        }
    });
}

function eliminarProducto(id) {
    let params = {
        'opcion': 'eliminarProducto',
        'idProducto': id,
        'idVenta': $('#idVenta').val()
    };

    $.ajax({
        url: 'Venta',
        type: 'GET',
        dataType: 'json',
        data: params,
        success: function (response) {
            if (response.error === "false") {
                actualizarDetalle();
            } else {
                console.log(response.mensaje);
            }
        }
    });
}
function actualizarVenta(idProducto) {
    let params = {
        'opcion': 'actualizarVenta',
        'idVenta': $('#idVenta').val(),
        'idProducto': idProducto,
        'cantidad': $('#cantidad-' + idProducto).val(),
        'precioUnidad': $('#precio-' + idProducto).val()
    };
    $.ajax({
        url: 'Venta',
        type: 'GET',
        dataType: 'json',
        data: params,
        success: function (response) {
            if (response.error === "false") {
                actualizarDetalle();
            } else {
                console.log(response.mensaje);
            }
        }
    });
}

function restarCantidad(idProducto) {
    cantidad = $('#cantidad-' + idProducto).val();

    if (cantidad > 0) {
        $('#cantidad-' + idProducto).val(parseInt(cantidad) - 1);
    } else {
        message("error", "La cantidad no puede ser menor a 0");
    }
    actualizarVenta(idProducto);

}

function sumarCantidad(idProducto) {
    cantidad = $('#cantidad-' + idProducto).val();

    $('#cantidad-' + idProducto).val(parseInt(cantidad) + 1);
    actualizarVenta(idProducto);
}

function realizarVenta() {
    var idProductos = document.querySelectorAll('input[name=idProducto]');

    for (var i = 0; i < idProductos.length; i++) {
        idProducto = idProductos[i].value;
        if($('#cantidad-' + idProducto).val() === "0") {
            message("error","Las cantidades de los productos no pueden ser igual a 0");
            return false;
        }
    }
    
    let params = {
        'opcion': 'guardarVenta',
        'idVenta': $('#idVenta').val(),
        'total': $('#total').html(),
        'observacion': $('#observacion').val()
    };
    $.ajax({
        url: 'Venta',
        type: 'GET',
        dataType: 'json',
        data: params,
        success: function (response) {
            if (response.error === "false") {
                window.location.href = response.url;
            } else {
                console.log(response.mensaje);
            }
        }
    });
}

function cargarInputs() {
    tamanos = document.getElementById("tamano").value === "" ? 0 : document.getElementById("tamano").value;
    cargarinput ="";
    if(tamanos < 0){
        message("error", "Para poder generar un árbol se debe ingresar un valor mayor a cero");   
    }else{
        for(i = 0; i < tamanos; i++){
            cargarinput += "<div class='form-body'>\n\
                             <label>Numero " + (i+1) + "</label> \n\
                             <input type='text' id='numero-"+ i +"' name='numeros[]' />\n\
                            </div>";
        }
        document.getElementById("numeros").innerHTML = cargarinput;
    }   
}

function validarArbol() {
    tamano = document.getElementById('tamano').value;
    recorrer = document.getElementById('recorrer').value;
    
    if (tamano !== "" || tamano < 0) {
        for (i = 0; i < tamano; i++) {
            if ($('#numero-'+ i).val() === "") {
                message('error','Por favor llenar todos los campos de numero para generar el arbol');
                return false;
            }
        }
    }
    
    if (tamano === "") {
        message('error','Por favor seleccione el tamaño del arbol');
    } else if (recorrer === "") {
        message('error','Por favor seleccione como quiere recorrer el arbol');
    } else {
        $('#formArbol').submit();
    }  
}