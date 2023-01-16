let d = document

//Funcion para inicializae el modulo.
export function inicializar(lugar)
{
    configureTableFilter(d.getElementById("txtSearch"),
                         d.getElementById("tblEmpleados"));
    //Ocultamos el panel de detalles:
    setDetalleVisible(lugar);
}

export function llenarTabla()
{

    let url = "../servicio/empleado/getAll"

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {

                let contenido = ""
                let isActive = ""
                let colorActive = ""

                data.map(empleado => {
                    let {idPersona, nombre, apellidoPaterno, apellidoMaterno, genero, telCasa, telMovil, email} = empleado.persona

                    let {estatus, numeroUnico, idEmpleado} = empleado

                    let {idUsuario} = empleado.usuario

                    let idsEmpleado = [idEmpleado, idPersona, idUsuario]

                    estatus ? isActive = "Activo" : isActive = "Inactivo"
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"

                    contenido += `<tr>
                <td>${nombre} ${apellidoPaterno} ${apellidoMaterno}</td>
                <td>${genero}</td>
                <td>${telCasa}</td>
                <td>${telMovil}</td>
                <td>${email}</td>
                <td style="color: ${colorActive}">${isActive}</td>
                <td>${numeroUnico}</td>
                <td><img style="cursor: pointer" onclick="em.modificar(${idsEmpleado})" src="../media/icons/escritura.png" width="30"/> </td>
                <td><img style="cursor: pointer" onclick="em.eliminar(${idsEmpleado})" src="../media/icons/reciclar.png" width="30"/> </td>
              </tr>`
                })

                d.getElementById("tbodyEmpleados").innerHTML = contenido;

            })
            .catch(err => console.log(err))
}

export function insertar(e) {
    
    e.preventDefault()

    let url = "../servicio/empleado/save"

    let datosFormulario = Object.fromEntries(new FormData(e.target))

    let {nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, calle, numero,
        colonia, cp, ciudad, estado, telCasa, telMovil, nombreUser, contrasenia, rol,
        numeroUnico, genero, email} = datosFormulario

    let usuario = {idUsuario: 0, nombre: nombreUser, contrasenia, rol}

    let persona = {idPersona: 0, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento,
        calle, numero, colonia, cp, ciudad, estado, telCasa, telMovil, email}

    let empleado = {idEmpleado: 0, numeroUnico, usuario, estatus: 0, persona}

    let datos = {datosEmpleado: JSON.stringify(empleado)}

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
    .catch(err => console.log(err))

}

export function enviarModificaciones(e, idEmpleado, idPersona, idUsuario) {

    e.preventDefault()
    
    let url = "../servicio/empleado/save"

    let datosFormulario = Object.fromEntries(new FormData(e.target))

    let {nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, calle, numero,
        colonia, cp, ciudad, estado, telCasa, telMovil, nombreUser, contrasenia,
        genero, email} = datosFormulario

    let usuario = {idUsuario, nombre: nombreUser, contrasenia, rol: "Administrador"}

    let persona = {idPersona, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento,
        calle, numero, colonia, cp, ciudad, estado, telCasa, telMovil, email}

    let empleado = {idEmpleado, usuario, persona}

    let datos = {datosEmpleado: JSON.stringify(empleado)}

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

export function modificar(idEmpleado, idPersona, idUsuario) {

    let idsEmpleado = [idEmpleado, idPersona, idUsuario]

    let url = "../servicio/empleado/getAll?filtro=" + idEmpleado

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {

                let empleado = data[0]

                let persona = empleado["persona"]


                let inputs = ""

                for (const key in persona) {

                    if (key !== "idPersona") {

                        inputs += `<input style="width: 48%" class="form-control mt-3" name="${key}" type="text" placeholder="${key}" value="${persona[key]}">`
                    }


                }

                let usuario = empleado["usuario"]

                let {nombre, contrasenia} = usuario

                inputs += `<input style="width: 48%" class="form-control mt-3" name="nombreUser" type="text" placeholder="nombre" value="${nombre}">`
                inputs += `<input style="width: 48%" class="form-control mt-3" name="contrasenia" type="text" placeholder="contraseña" value="${contrasenia}">`

                inputs += `<input style="width:97px; height:40px; background-color:#00b168; color:white;" type="submit" class="btn btn-success mt-2" value="Modificar"/>
                <p style="background-color:#f52100; color:white;" class="btn text-light btn-warning mt-2" onclick="em.quitarVentanaModal()">cancelar</p>`

                let formModal = `<form onsubmit="em.enviarModificaciones(event,${idsEmpleado})" class="card-body d-flex flex-row justify-content-between flex-wrap" id="inputs-empleados">
                                 ${inputs} 
                                 </form>`

                d.getElementById("card").innerHTML = formModal

                setDetalleVisible("modal")
            })
            .catch(err => console.log(err))
}

//Elimina un accesorio
export function eliminar(idEmpleado)
{
    let url = "../servicio/empleado/deleteEmpleado?idEmpleado=" + idEmpleado


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
        d.getElementById("menu-empleados").style.display = "";
        d.getElementById("form-empleados").style.display = "none";
        d.getElementById("tablas-empleados").style.display = "none";
        d.getElementById("empleados-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-empleados").style.display = "none";
        d.getElementById("form-empleados").style.display = "";
        d.getElementById("tablas-empleados").style.display = "none";
        d.getElementById("empleados-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-empleados").style.display = "none";
        d.getElementById("form-empleados").style.display = "none";
        d.getElementById("tablas-empleados").style.display = "";
        d.getElementById("empleados-ventana").style.display = "none"
    } else
    {
        d.getElementById("menu-empleados").style.display = "none";
        d.getElementById("form-empleados").style.display = "none";
        d.getElementById("tablas-empleados").style.display = "";
        d.getElementById("empleados-ventana").style.display = ""
    }
}


