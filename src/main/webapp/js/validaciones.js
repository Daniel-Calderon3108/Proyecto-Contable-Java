function message(tipo, mensaje) {
    Swal.fire({
        icon: tipo,
        title: 'Sacart Asociados',
        text: mensaje
    });
}

function numeros(numero,campo) {
    if (/[^0-9]/.test(numero)) {
        document.getElementById(campo).value = numero.slice(0, -1);
    } 
}

function alfanumerico(string,campo) {
    if (/[^a-zA-Z0-9\s]/.test(string)) {
        document.getElementById(campo).value = string.slice(0, -1);
    }
}

function describe(string,campo) {
    if(/[<>'"{}]/.test(string)) {
        document.getElementById(campo).value = string.slice(0, -1);
    }
}


function validarInventario() {
    numeroLote = $('#numerolote').val();
    cantidad = $('#cantidad').val();
    idProducto = $('#idProducto').val();
    
    
    if (numeroLote === "") {
        message('error','Por favor especifique a que lote pertenece');
    } else if (cantidad === "") {
        message('error','Por favor especifique la cantidad a ingresar');
    } else if (idProducto === "0") {
        message('error','Por favor seleccione un producto');
    } else {
        $('#formInventario').submit();
    }
}

function validarProducto() {
    nombre = $('#nombre').val();
    precio = $('#precio').val();
    descripcion = $('#descripcion').val();
    
    if (nombre === "") {
        message('error','Por favor indique el nombre del producto');
    } else if (precio === "") {
        message('error','Por favor indique el precio del producto');
    } else if (descripcion === "") {
        message('error','Por favor escriba una breve descripción del producto');
    } else {
        $('#formProducto').submit();
    }
}

function validarUsuario() {
    nombre = $('#nombre').val();
    correo = $('#correo').val();
    clave = $('#clave').val();
    tel = $('#tel').val();
    direccion = $('#direccion').val();
    idPerfil = $('#idPerfil').val();
    validarEmail =  /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/;
    
    if (nombre === "") {
        message('error','Por favor introduzca un nombre');
    } else if (correo === "" || !validarEmail.test(correo)) {
        message('error','Por favor introduzca un correo valido');
    } else if (clave === "") {
        message('error','Por favor introduzca una clave');
    } else if (tel === "") {
        message('error','Por favor introduzca un numero de telefono');
    } else if (direccion === "") {
        message('error','Por favor introduzca una direccion');
    } else if (idPerfil === '0') {
        message('error','por favor asigne un perfil al usuario');
    } else {
        $('#formUsuario').submit();
    }
}