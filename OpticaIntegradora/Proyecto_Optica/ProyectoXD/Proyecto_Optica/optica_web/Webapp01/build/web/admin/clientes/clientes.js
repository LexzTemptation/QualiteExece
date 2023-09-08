let d = document;
let ventanaModal = "";
let tablas = d.getElementById("tablas-clientes");
let currentContent;

let clientes = [];

export function inicializar(lugar) {
    configureTableFilter(d.getElementById("txtSearch"),
                         d.getElementById("tblClientes"));
    setDetalleVisible(lugar);
}

function clearCliente(){
    
        d.getElementById("txtNombre").value = ""
        d.getElementById("txtApellidoPaterno").value = ""
        d.getElementById("txtApellidoMaterno").value = ""
        d.getElementById("txtGenero").value = ""
        d.getElementById("txtFechaNacimiento").value = ""
        d.getElementById("txtCalle").value = ""
        d.getElementById("txtNumero").value = ""
        d.getElementById("txtColonia").value = ""
        d.getElementById("txtCp").value = ""
        d.getElementById("txtCiudad").value = ""
        d.getElementById("txtEstado").value = ""
        d.getElementById("txtTelefonoCasa").value = ""
        d.getElementById("txtTelefonoMovil").value = ""
        d.getElementById("txtCorreo").value = ""
    
}

export function llenarTabla() {
    let url = "../servicio/cliente/getAll";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let contenido = "";
                let isActive = "";
                let colorActive = ""
                data.map(cliente => {
                    let {idPersona,
                        nombre,
                        apellidoPaterno,
                        apellidoMaterno,
                        genero,
                        fechaNacimiento,
                        calle,
                        numero,
                        colonia,
                        cp,
                        ciudad,
                        estado,
                        telCasa,
                        telMovil,
                        email
                    } = cliente.persona;
                    let {idCliente, numeroUnico, estatus, persona} = cliente;

                    let idsCliente = [idCliente, idPersona];

                    estatus ? isActive = "Activo" : isActive = "Inactivo";
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"

                    contenido +=
                            `<tr>
                                <td>${nombre} ${apellidoPaterno} ${apellidoMaterno}</td>
                                <td>${genero}</td>
                                <td>${telCasa}</td>
                                <td>${telMovil}</td>
                                <td>${email}</td>
                                <td style="color: ${colorActive}">${isActive}</td>
                                <td>${numeroUnico}</td>
                                <td><img style="cursor: pointer" onclick="cm.modificar(${idsCliente})" src="../media/icons/escritura.png" width="30"/></td>
                                <td> <img style="cursor: pointer" onclick="cm.eliminar(${idCliente})" src="../media/icons/reciclar.png" width="30"/> </td>
                            </tr>`
                })
                d.getElementById("tbodyClientes").innerHTML = contenido;
            })
}

function limpiarFormularioDetalle() {
    document.getElementById("nombre").value = "";
    document.getElementById("apellidoPaterno").value = "";
    document.getElementById("apellidoMaterno").value = "";
    document.getElementById("rfc").value = "";
    document.getElementById("telefonoCasa").value = "";
    document.getElementById("telefonoMovil").value = "";
    document.getElementById("correo").value = "";
}

function invertirFecha(fecha) {

    let limite = fecha.length - 1
    let newDate = ""

    while (limite !== 0) {

        newDate += fecha[limite]
        limite--
    }

    console.log(fecha)
    console.log(newDate)
}

export function insertar(e) {

    let url = "../servicio/cliente/save"
    let persona = {
        idPersona: 0,
        nombre: d.getElementById("txtNombre").value,
        apellidoPaterno: d.getElementById("txtApellidoPaterno").value,
        apellidoMaterno: d.getElementById("txtApellidoMaterno").value,
        genero: d.getElementById("txtGenero").value,
        fechaNacimiento: d.getElementById("txtFechaNacimiento").value,
        calle: d.getElementById("txtCalle").value,
        numero: d.getElementById("txtNumero").value,
        colonia: d.getElementById("txtColonia").value,
        cp: d.getElementById("txtCp").value,
        ciudad: d.getElementById("txtCiudad").value,
        estado: d.getElementById("txtEstado").value,
        telCasa: d.getElementById("txtTelefonoCasa").value,
        telMovil: d.getElementById("txtTelefonoMovil").value,
        email: d.getElementById("txtCorreo").value}
    let cliente = {
        idCliente: 0,
        numeroUnico: 0,
        estatus: 0,
        persona
    }
    let datos = {
        datosCliente: JSON.stringify(cliente)
    }

    let params = new URLSearchParams(datos);
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(message => {
                const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 2000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                })

                Toast.fire({
                    icon: 'success',
                    title: 'Cliente Guardado!'
                })
                limpiar()
            })
            .catch(err => console.log(err))

}

export function enviarModificaciones(idCliente, idPersona) {
    let idsCliente = [idCliente, idPersona];
    let url = "../servicio/cliente/save";

    let persona = {
        idPersona: idPersona,
        nombre: d.getElementById("m-nombre").value,
        apellidoPaterno: d.getElementById("m-apellidoPaterno").value,
        apellidoMaterno: d.getElementById("m-apellidoMaterno").value,
        genero: d.getElementById("m-genero").value,
        fechaNacimiento: d.getElementById("m-fechaNacimiento").value,
        calle: d.getElementById("m-calle").value,
        numero: d.getElementById("m-numero").value,
        colonia: d.getElementById("m-colonia").value,
        cp: d.getElementById("m-cp").value,
        ciudad: d.getElementById("m-ciudad").value,
        estado: d.getElementById("m-estado").value,
        telCasa: d.getElementById("m-telCasa").value,
        telMovil: d.getElementById("m-telMovil").value,
        email: d.getElementById("m-email").value};

    let cliente = {
        idCliente: idCliente,
        numeroUnico: 0,
        estatus: 1,
        persona};

    let datos = {datosCliente: JSON.stringify(cliente)};

    let params = new URLSearchParams(datos);
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            .then(res => {
                quitarVentanaModal()
                
                const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                })

                Toast.fire({
                    icon: 'info',
                    title: 'Cliente Modificado!'
                })
        
                llenarTabla();
            })
}

export function modificar(idCliente, idPersona) {
    let idsCliente = [idCliente, idPersona];
    let url = "../servicio/cliente/getAll?filtro" + idCliente;

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                let cliente = data[idCliente - 1];
                let persona = cliente.persona;

                let inputs = "";
                for (const key in persona) {
                    if (key !== "idPersona") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${persona[key]}">`
                    }
                }

                inputs += `<div><button style="background-color:#00b168; color:white;" class="btn m-lg-3"
                           onclick="cm.enviarModificaciones(${idCliente},${idPersona})"        
                           >Guardar</button>
                          <button style="background-color:#f52100; color:white;" class="btn text-light m-lg-3" onclick="cm.quitarVentanaModal()">Cancelar</button></div>`

                d.getElementById("inputs-clientes").innerHTML = inputs

                setDetalleVisible("modal")
            })
}

export function eliminar(idCliente) {
    let url = "../servicio/cliente/deleteCliente?idCliente=" + idCliente;
    fetch(url)
            .then(res => res.ok ? res : Promise.reject(res))
            .then(message => {
                llenarTabla()
        const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                })

                Toast.fire({
                    icon: 'info',
                    title: 'Cliente Eliminado!'
                })
    })
            .catch(err => console.log(err)) 
}

export function quitarVentanaModal(e) {
    setDetalleVisible("tables")
}

export function setDetalleVisible(valor) {
//Si valor es verdadero, mostramos el panel de detalles y ocultamos el panel del catalogo.
    if (valor === "menu")
    {
        d.getElementById("menu-clientes").style.display = "";
        d.getElementById("form-clientes").style.display = "none";
        d.getElementById("tablas-clientes").style.display = "none";
        d.getElementById("clientes-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-clientes").style.display = "none";
        d.getElementById("form-clientes").style.display = "";
        d.getElementById("tablas-clientes").style.display = "none";
        d.getElementById("clientes-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-clientes").style.display = "none";
        d.getElementById("form-clientes").style.display = "none";
        d.getElementById("tablas-clientes").style.display = "";
        d.getElementById("clientes-ventana").style.display = "none"
    } else
    {
        d.getElementById("menu-clientes").style.display = "none";
        d.getElementById("form-clientes").style.display = "none";
        d.getElementById("tablas-clientes").style.display = "";
        d.getElementById("clientes-ventana").style.display = ""
    }
}

function limpiar(){
        d.getElementById("txtNombre").value = "";
        d.getElementById("txtApellidoPaterno").value = "";
        d.getElementById("txtApellidoMaterno").value = "";
        d.getElementById("txtGenero").value = "";
        d.getElementById("txtFechaNacimiento").value = "";
        d.getElementById("txtCalle").value = "";
        d.getElementById("txtNumero").value = "";
        d.getElementById("txtColonia").value = "";
        d.getElementById("txtCp").value = "";
        d.getElementById("txtCiudad").value = "";
        d.getElementById("txtEstado").value = "";
        d.getElementById("txtTelefonoCasa").value = "";
        d.getElementById("txtTelefonoMovil").value = "";
        d.getElementById("txtCorreo").value = "";
}