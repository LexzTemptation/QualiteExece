let d = document
let ventanaModal = ""
let tablas = d.getElementById("tablas-clientes")
let currentContent
let currentBase64


//Funcion para inicializae el modulo.
export function inicializar(lugar)
{

    configureTableFilter(d.getElementById("txtSearch"),
            d.getElementById("tblArmazones"));
    // inputFileArmazon = d.getElementById("inputFileImagenArmazon");
    // inputFileArmazon.onchange = function(evt){cargarFotografia(inputFileArmazon);};
    //Ocultamos el panel de detalles:

    setDetalleVisible(lugar);
}

/**
 * Esta función llena una tabla HTML a partir del arreglo de accesorios.
 * @returns {undefined}
 */


/**function cargarFotografia(objetoInputFile){
 
 //Revisamos que el usuario haya revisado un archivo:
 if(objetoInputFile.files && objetoInputFile.files[0]){
 let reader = new FileReader();
 
 
 // Agregamos un oyente al lector del archivo para que .
 // en cuanto el usuario cargue una imagen esta se lea 
 // y se convierta en forma automatica en una cadena de base 64
 reader.onload = function (e)
 {
 let fotocb64 = e.target.result;
 d.getElementById("imgFoto").src = fotocb64;
 d.getElementById("txtCodigoImagen").value =
 fotocb64.substring(fotocb64.indexOf(",") + 1, fotocb64.length);
 };
 
 // Leemos el archivo que seleccionó el usuario y lo
 // convertimos en una cadena con la base 64
 reader.readAsDataURL(objetoInputFile.files[0]);
 }
 }**/
export function llenarTabla() {
    let url = "../servicio/armazon/getAll"

    fetch(url)
            .then(res => res.json())
            .then(data => {
                let contenido = ""
                let isActive = ""
                let colorActive = ""

                data.map(armazon => {
                    let{idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus} = armazon.producto
                    let{idArmazon, modelo, color, dimensiones, descripcion, fotografia} = armazon
                    let idsArmazon = [idProducto, idArmazon]

                    estatus ? isActive = "Activo" : isActive = "Inactivo"
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"

                    contenido += `<tr> 
                <td>${nombre}</td>
                <td>${marca}</td>
                <td>${descripcion}</td>
                <td>${precioVenta}</td>
                <td>${precioCompra}</td>
                <td>${codigoBarras}</td>
                <td>${existencias}</td>
                <td>${modelo}</td>
                <td>${color}</td>
                <td>${dimensiones}</td>
                <td style="color: ${colorActive}">${isActive}</td>
                <td><img src="data:image/png;base64,${fotografia}" width="100"/></td>
                <td> <img style="cursor: pointer" onclick="az.modificar(${idsArmazon})" src="../media/icons/escritura.png" width="30"/> </td>
                <td> <img style="cursor: pointer" onclick="az.eliminar(${idsArmazon})" src="../media/icons/reciclar.png" width="30"/> </td>
                </tr>`
                })
                d.getElementById("tbodyArmazones").innerHTML = contenido;

            })

}

//Buscamos la posicion del accesorio por su ID
function buscarPosicionClientePorId(id)
{
}

function limpiarFormularioDetalle()
{
    d.getElementById("txtnombre").value = "";
    d.getElementById("txtmarca").value = "";
    d.getElementById("txtdescripcion").value = "";
    d.getElementById("txtmodelo").value = "";
    d.getElementById("txtcolor").value = "";
    d.getElementById("txtfotografia").value = "";
    d.getElementById("txtdimensiones").value = "";
    d.getElementById("txtexistencias").value = "";
    d.getElementById("txtprecioVenta").value = "";
    d.getElementById("txtprecioCompra").value = "";
}

export function insertar() {

    let url = "../servicio/armazon/save"

    let objetoInputFile = d.getElementById("txtfotografia")

    //Revisamos que el usuario haya revisado un archivo:
    if (objetoInputFile.files && objetoInputFile.files[0]) {
        let reader = new FileReader();

        // Agregamos un oyente al lector del archivo para que .
        // en cuanto el usuario cargue una imagen esta se lea 
        // y se convierta en forma automatica en una cadena de base 64
        reader.onload = function (e) {
            let fotocb64 = e.target.result;
            let foto = fotocb64.substring(fotocb64.indexOf(",") + 1, fotocb64.length)

            let producto = {
                idProducto: 0,
                codigoBarras: 0,
                nombre: d.getElementById("txtnombre").value,
                marca: d.getElementById("txtmarca").value,
                precioCompra: d.getElementById("txtprecioCompra").value,
                precioVenta: d.getElementById("txtprecioVenta").value,
                existencias: d.getElementById("txtexistencias").value,
                estatus: 0
            }

            let armazon = {
                idArmazon: 0,
                producto,
                modelo: d.getElementById("txtmodelo").value,
                color: d.getElementById("txtcolor").value,
                dimensiones: d.getElementById("txtdimensiones").value,
                descripcion: d.getElementById("txtdescripcion").value,
                fotografia: foto
            }

            let datos = {
                datosArmazon: JSON.stringify(armazon)
            }
            let params = new URLSearchParams(datos)

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
                },
                body: params
            }).then(res => {
                
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

            
            limpiarFormularioDetalle()
        });

        }

        // Leemos el archivo que seleccionó el usuario y lo
        // convertimos en una cadena con la base 64
        reader.readAsDataURL(objetoInputFile.files[0]);
    }
}

export function enviarModificaciones(idArmazon, idProducto) {
    let url = "../servicio/armazon/save"

    let objetoInputFile = d.getElementById("m-foto")

    //Revisamos que el usuario haya revisado un archivo:
    if (objetoInputFile.files && objetoInputFile.files[0]) {
        let reader = new FileReader();

        // Agregamos un oyente al lector del archivo para que .
        // en cuanto el usuario cargue una imagen esta se lea 
        // y se convierta en forma automatica en una cadena de base 64
        reader.onload = function (e) {
            let fotocb64 = e.target.result;
            let foto = fotocb64.substring(fotocb64.indexOf(",") + 1, fotocb64.length)

            let producto = {
                idProducto: idProducto,
                codigoBarras: "",
                nombre: d.getElementById("m-nombre").value,
                marca: d.getElementById("m-marca").value,
                precioCompra: d.getElementById("m-precioCompra").value,
                precioVenta: d.getElementById("m-precioVenta").value,
                existencias: d.getElementById("m-existencias").value,
                estatus: 1
            }

            let armazon = {
                idArmazon: idArmazon,
                producto,
                modelo: d.getElementById("m-modelo").value,
                color: d.getElementById("m-color").value,
                dimensiones: d.getElementById("m-dimensiones").value,
                descripcion: d.getElementById("m-descripcion").value,
                fotografia: foto

            }
            let datos = {
                datosArmazon: JSON.stringify(armazon)
            }
            let params = new URLSearchParams(datos)

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
                },
                body: params
            })
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



        };

        // Leemos el archivo que seleccionó el usuario y lo
        // convertimos en una cadena con la base 64
        reader.readAsDataURL(objetoInputFile.files[0]);

    } else {

        let producto = {
            idProducto: idProducto,
            codigoBarras: "",
            nombre: d.getElementById("m-nombre").value,
            marca: d.getElementById("m-marca").value,
            precioCompra: d.getElementById("m-precioCompra").value,
            precioVenta: d.getElementById("m-precioVenta").value,
            existencias: d.getElementById("m-existencias").value,
            estatus: 1
        }

        let armazon = {
            idArmazon: idArmazon,
            producto,
            modelo: d.getElementById("m-modelo").value,
            color: d.getElementById("m-color").value,
            dimensiones: d.getElementById("m-dimensiones").value,
            descripcion: d.getElementById("m-descripcion").value,
            fotografia: currentBase64

        }
        let datos = {
            datosArmazon: JSON.stringify(armazon)
        }
        let params = new URLSearchParams(datos)

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
            },
            body: params
        })
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




}

export function quitarVentanaModal(e) {

    setDetalleVisible("tables")
}

export function modificar(idProducto, idArmazon) {
    let url = "../servicio/armazon/getAll?filtro=" + idArmazon

    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                let armazon = data[idArmazon - 1]

                currentBase64 = armazon.fotografia
                let producto = armazon.producto
                let inputs = ""

                for (const key in armazon) {
                    if (key !== "idArmazon" && key !== "producto" && key !== "fotografia") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${armazon[key]}">`
                    }
                }
                for (const key in producto) {
                    if (key !== "idProducto" && key !== "estatus" && key !== "codigoBarras") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${producto[key]}">`
                    }
                }

                inputs += `<img src="data:image/png;base64,${currentBase64}" width="100"/>`

                inputs += "<input type='file' id='m-foto'/>"

                inputs += `<button style="width:97px; height:40px; background-color:#00b168; color:white;" class="btn btn-success mt-2" onClick="az.enviarModificaciones(${idArmazon},${idProducto})">Guardar</button>
                <button style="width:97px; height:40px; background-color:#f52100; color:white;" class="btn text-light btn-warning mt-2" onClick="az.quitarVentanaModal()">Cancelar</p>`

                d.getElementById("inputs-armazones").innerHTML = inputs
                setDetalleVisible("modal")
            })
            .catch(err => console.log(err))
}

//Elimina un accesorio
export function eliminar(idArmazon)
{

    let url = "../servicio/armazon/deleteArmazon?idArmazon=" + idArmazon


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
        d.getElementById("menu-armazon").style.display = "";
        d.getElementById("form-armazon").style.display = "none";
        d.getElementById("tablas-armazon").style.display = "none";
        d.getElementById("armazon-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-armazon").style.display = "none";
        d.getElementById("form-armazon").style.display = "";
        d.getElementById("tablas-armazon").style.display = "none";
        d.getElementById("armazon-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-armazon").style.display = "none";
        d.getElementById("form-armazon").style.display = "none";
        d.getElementById("tablas-armazon").style.display = "";
        d.getElementById("armazon-ventana").style.display = "none"
    } else
    {
        d.getElementById("menu-armazon").style.display = "none";
        d.getElementById("form-armazon").style.display = "none";
        d.getElementById("tablas-armazon").style.display = "";
        d.getElementById("armazon-ventana").style.display = ""
    }
}
