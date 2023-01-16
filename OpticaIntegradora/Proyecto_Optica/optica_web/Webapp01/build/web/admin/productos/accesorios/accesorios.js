let d = document
let ventanaModal = ""
let tablas = d.getElementById("tablas-clientes")
let currentContent

let accesorios = [];

//Funcion para inicializae el modulo.
export function inicializar(lugar)
{
    configureTableFilter(d.getElementById("txtSearch"),
                         d.getElementById("tblAccesorios"));
    //Ocultamos el panel de detalles:
    setDetalleVisible(lugar);
}

/**
 * Esta función llena una tabla HTML a partir del arreglo de accesorios.
 * @returns {undefined}
 */

export function llenarTabla() {
    let url = "../servicio/accesorio/getAll"

    //hacemos peticion al servicio
    fetch(url)
            .then(res => res.json())
            .then(data => {

                let contenido = ""
                let isActive = ""
                let colorActive = ""

                //map itera, pasa por cada uno de los objetos
                data.map(accesorio => {
                    //destructuramos accesorrio,producto 
                    let {idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus} = accesorio.producto;
                    let {idAccesorio, producto} = accesorio;
                    let idsAccesorio = [idAccesorio, idProducto]

                    estatus ? isActive = "Activo" : isActive = "Inactivo"
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"

                    contenido += `<tr>
                    <td>${nombre}</td>
                    <td>${marca}</td>
                    <td>${codigoBarras}</td>
                    <td>${precioVenta}</td>
                    <td>${precioCompra}</td>
                    <td>${existencias}</td>
                    <td style="color: ${colorActive}">${isActive}</td>
                    <td> <img style="cursor: pointer" onclick="ac.modificar(${idsAccesorio})" src="../media/icons/escritura.png" width="30"/> </td>
                    <td><img style="cursor: pointer" onclick="ac.eliminar(${idAccesorio})" src="../media/icons/reciclar.png" width="30"/> </td>
                    </tr>`
                })


                d.getElementById("tbodyAccesorios").innerHTML = contenido;
            })
}

function limpiarFormularioDetalle() {
}

export function insertar() {
    let url = "../servicio/accesorio/save"

    //creamos los objetos de producto y accesorio y le pasamos los valores de los txt
    let producto = {
        idProducto: 0,
        codigoBarras: null,
        nombre: d.getElementById("txtNombre").value,
        marca: d.getElementById("txtMarca").value,
        precioCompra: d.getElementById("txtPrecioCompra").value,
        precioVenta: d.getElementById("txtPrecioVenta").value,
        existencias: d.getElementById("txtExistencias").value,
        estatus: 0
    }
    let accesorio = {
        idAccesorio: 0,
        producto
    }
    let datos = {
        //convertimos acesorio a una cadena de texto json
        datosAccesorio: JSON.stringify(accesorio)
    }
    //
    let params = new URLSearchParams(datos)
    //hacemos peticion al servicio
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            //obtenemos la respuesta del backend
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
                    title: 'Registro Insertado!'
                })
                limpiar()
            })
            .catch(err => console.log(err))
}
function limpiar(){
    d.getElementById("txtNombre").value = "",
    d.getElementById("txtMarca").value = "",
    d.getElementById("txtPrecioCompra").value = "",
    d.getElementById("txtPrecioVenta").value = "",
    d.getElementById("txtExistencias").value = ""
}

export function enviarModificaciones(idAccesorio, idProducto, codigoBarras) {
    //llamamos el servicio de save
    let url = "../servicio/accesorio/save"

    //creamos los objetos de producto y accesorio
    let producto = {
        idProducto: idProducto,
        codigoBarras: codigoBarras,
        nombre: d.getElementById("m-nombre").value,
        marca: d.getElementById("m-marca").value,
        precioCompra: d.getElementById("m-precioCompra").value,
        precioVenta: d.getElementById("m-precioVenta").value,
        existencias: d.getElementById("m-existencias").value,
        estatus: 1
    }
    let accesorio = {
        idAccesorio: idAccesorio,
        producto
    }
    let datos = {
        //convertimos acesorio a una cadena de texto json
        datosAccesorio: JSON.stringify(accesorio)
    }

    let params = new URLSearchParams(datos)

    //hacemos peticion al servicio
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })

            //llenamos la tabla con los datos actualizados
            .then(res => {
                console.log(res)
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
                    title: 'Registro Modificado!'
                })

                setDetalleVisible("tables")

            })
            .catch(err => console.log(err))

    setDetalleVisible("tables")
           

    //cerramos la ventana modal
    quitarVentanaModal()

}

export function quitarVentanaModal(e) {

    setDetalleVisible("tables")
}

export function modificar(idAccesorio, idProducto, codigoBarras) {
    let url = "../servicio/accesorio/getAll?filtro=" + idAccesorio

    //hacemos peticion al servicio
    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {

                let accesorio = data[idAccesorio - 1]


                let producto = accesorio.producto
                let codigoBarras = producto.codigoBarras

                let inputs = ""
                for (const key in producto) {
                    if (key !== "idProducto" && key !== "estatus" && key !== "codigoBarras") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${producto[key]}">`
                    }
                }
                inputs += `<button style="width:97px; height:40px; background-color:#00b168; color:white;" class="btn btn-success m-lg-3 mt-2" onClick="ac.enviarModificaciones(${idAccesorio},${idProducto},'${codigoBarras}')">Guardar</button>
                <button style="width:97px; height:40px; background-color:#f52100; color:white;" class="btn text-light btn-warning m-lg-3 mt-2" onClick="ac.quitarVentanaModal()">Cancelar</p>`

                d.getElementById("inputs-accesorios").innerHTML = inputs
                setDetalleVisible("modal")
            })
            .catch(err => console.log(err))
}

//Elimina un accesorio
export function eliminar(idAccesorio) {
    let url = "../servicio/accesorio/deleteAccesorio?idAccesorio=" + idAccesorio
    
    Swal.fire({
        title: 'Borrar registro',
        text: 'Seguro que quieres borrar este registro',
        icon: "warning",
        showCancelButton: true,
        preConfirm: response => {

            if (response) {
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
                    title: 'Registro Eliminado!'
                })
                
    fetch(url)
            .then(res => res.ok ? res : Promise.reject(res))
            .then(message => llenarTabla())
            .catch(err => console.log(err))
     }
        }
    })

}

/*
 * Nuestra u oculta el panel de detalles del accesorio.
 */
export function setDetalleVisible(valor)
{
    //Si valor es verdadero, mostramos el panel de detalles y ocultamos el panel del catalogo.
    //Si valor es verdadero, mostramos el panel de detalles y ocultamos el panel del catalogo.
    if (valor === "menu")
    {
        d.getElementById("menu-accesorios").style.display = "";
        d.getElementById("form-accesorios").style.display = "none";
        d.getElementById("tablas-accesorios").style.display = "none";
        d.getElementById("accesorios-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-accesorios").style.display = "none";
        d.getElementById("form-accesorios").style.display = "";
        d.getElementById("tablas-accesorios").style.display = "none";
        d.getElementById("accesorios-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-accesorios").style.display = "none";
        d.getElementById("form-accesorios").style.display = "none";
        d.getElementById("tablas-accesorios").style.display = "";
        d.getElementById("accesorios-ventana").style.display = "none"
    } else
    {
        d.getElementById("menu-accesorios").style.display = "none";
        d.getElementById("form-accesorios").style.display = "none";
        d.getElementById("tablas-accesorios").style.display = "";
        d.getElementById("accesorios-ventana").style.display = ""
    }
}

