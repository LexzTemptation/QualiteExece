let d = document

//Funcion para inicializae el modulo.
export function inicializar(lugar)
{
    configureTableFilter(d.getElementById("txtSearch"),
            d.getElementById("tblSoluciones"));
    //Ocultamos el panel de detalles:
    setDetalleVisible(lugar);
}

export function llenarTabla() {

    let token = w.localStorage.getItem("token")
    let url = "../servicio/soluciones/getAll?token=" + token

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {

                let contenido = ""
                let isActive = null
                let colorActive = null

                data.map(solucion => {
                    let {idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus} = solucion.producto

                    let {idSolucion} = solucion

                    let idsSolucion = [idProducto, idSolucion]

                    estatus ? isActive = "Activo" : isActive = "Inactivo"
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"

                    contenido += `<tr>
                <td>${marca}</td>
                <td>${nombre}</td>
                <td>${precioVenta}</td>
                <td>${precioCompra}</td>
                <td>${codigoBarras}</td>
                <td>${existencias}</td>
                <td style="color: ${colorActive}">${isActive}</td>
                <td><img style="cursor: pointer" onclick="sl.modificar(${idsSolucion})" src="../media/icons/escritura.png" width="30"/> </td>
                <td><img style="cursor: pointer" onclick="sl.eliminar(${idProducto})" src="../media/icons/reciclar.png" width="30"/> </td>
              </tr>`
                })

                d.getElementById("tbodySoluciones").innerHTML = contenido;

            })
            .catch(err => {
                d.getElementById("tbodySoluciones").innerHTML = "";

                Swal.fire({
                    title: 'Lo sentimos...',
                    text: 'Acceso denegado',
                    icon: "error",
                })
            })

}

export function insertar(e) {

    e.preventDefault()

    let currentToken = w.localStorage.getItem("token")

    let url = "../servicio/soluciones/save"

    let datosFormulario = Object.fromEntries(new FormData(e.target))

    let {nombre, marca, existencias, precioCompra, precioVenta} = datosFormulario

    let producto = {idProducto: 0, nombre, marca, existencias, precioCompra, precioVenta, codigoBarras: null}

    let solucion = {idSolucion: 0, producto}

    let datos = {datosSolucion: JSON.stringify(solucion), token: currentToken}

    let params = new URLSearchParams(datos)

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
                    timer: 3000,
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

                e.target.reset()
            })
            .catch(err => {
                console.log(err)
                Swal.fire({
                    title: 'Lo sentimos...',
                    text: 'Acceso denegado',
                    icon: "error",
                })
            })

}

export function enviarModificaciones(e, idProducto, idSolucion) {

    let url = "../servicio/soluciones/save"

    e.preventDefault()

    let datosFormulario = Object.fromEntries(new FormData(e.target))

    let {nombre, marca, precioCompra, precioVenta, existencias, codigoBarras} = datosFormulario

    let producto = {idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus: 1}

    let solucion = {idSolucion, producto}

    let datos = {datosSolucion: JSON.stringify(solucion)}

    let params = new URLSearchParams(datos)

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            .then(res => res.ok ? res.json() : Promise.reject(res))
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
                    title: 'Registro Modificado!'
                })

                setDetalleVisible("tables")

            })
            .catch(err => console.log(err))

    setDetalleVisible("tables")
}

export function quitarVentanaModal(e) {

    setDetalleVisible("tables")
}

export function modificar(idProducto, idSolucion) {

    let idsSolucion = [idProducto, idSolucion]
    let token = w.localStorage.getItem("token")

    let url = "../servicio/soluciones/getAll?filtro=" + idSolucion + "&token=" + token

    console.log(url)

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                console.log(data)
                let producto = data[0]["producto"]
                
                let text = "^[a-zA-ZñÑáÁéÉíÍóÓúÚ\s]+$"
                let number = "^[0-9]+$"
                

                let inputs = ""

                for (const key in producto) {

                    if (key !== "idProducto" && key !== "estatus") {
                        let regExp = text
                        
                        if(key === "codigoBarras") regExp = "^[0-9a-zA-ZñÑáÁéÉíÍóÓúÚ\s-]+$"
                        
                        if(key === "existencias" || key === "precioCompra" || key === "precioVenta") regExp = number

                        inputs += `<input style="width: 48%" 
                                          class="form-control mt-3" 
                                          name="${key}" type="text" 
                                          pattern="${regExp}"
                                          placeholder="${key}" value="${producto[key]}">`

                    } 

                }

                inputs += `<button style="width:97px; height:40px; background-color:#00b168; color:white;" class="btn mt-2">Modificar</button>
                <p class="btn text-light mt-2"; style="background-color:#f52100; width:97px; height:40px;" onclick="sl.quitarVentanaModal()">Cancelar</p>`

                let formModal = `<form onsubmit="sl.enviarModificaciones(event,${idsSolucion})" class="card-body d-flex flex-row justify-content-between flex-wrap" id="inputs-empleados">
                                 ${inputs} 
                                 </form>`

                d.getElementById("card").innerHTML = formModal

                setDetalleVisible("modal")


            })
            .catch(err => console.log(err))

}

//Elimina un accesorio
export function eliminar(idProducto) {

    let url = "../servicio/soluciones/deleteSolucion?idProducto=" + idProducto

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
                        .then(res => res.ok ? res.json() : Promise.reject(res))
                        .then(message => {
                            llenarTabla()
                        })
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
        d.getElementById("menu-soluciones").style.display = "";
        d.getElementById("form-soluciones").style.display = "none";
        d.getElementById("tablas-soluciones").style.display = "none";
        d.getElementById("soluciones-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-soluciones").style.display = "none";
        d.getElementById("form-soluciones").style.display = "";
        d.getElementById("tablas-soluciones").style.display = "none";
        d.getElementById("soluciones-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-soluciones").style.display = "none";
        d.getElementById("form-soluciones").style.display = "none";
        d.getElementById("tablas-soluciones").style.display = "";
        d.getElementById("soluciones-ventana").style.display = "none"
    } 
    else if(valor === "busqueda"){
        
        d.getElementById("menu-soluciones").style.display = "none";
        d.getElementById("form-soluciones").style.display = "none";
        d.getElementById("tablas-soluciones").style.display = "none";
        d.getElementById("soluciones-ventana").style.display = "none"
        
        console.log("hola")
    }
    else
    {
        d.getElementById("menu-soluciones").style.display = "none";
        d.getElementById("form-soluciones").style.display = "none";
        d.getElementById("tablas-soluciones").style.display = "";
        d.getElementById("soluciones-ventana").style.display = ""
    }
}




