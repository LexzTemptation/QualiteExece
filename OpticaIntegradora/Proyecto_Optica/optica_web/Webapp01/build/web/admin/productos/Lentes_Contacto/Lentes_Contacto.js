let d = document
let ventanaModal = ""
let tablas = d.getElementById("tablas-lentesC")
let currentContent
let currentBase64 = ""

let lentes_contacto = [];

//Funcion para inicializae el modulo.
export function inicializar(lugar)
{
    //Ocultamos el panel de detalles:
    setDetalleVisible(lugar);
}

/**
 * Esta función llena una tabla HTML a partir del arreglo de accesorios.
 * @returns {undefined}
 */

export function llenarTabla()
{
    let url = "../servicio/lente_contacto/getAll";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let contenido = ""
                let isActive = null
                let colorActive = null;

                data.map(lente_contacto => {
                    //desctructuramos producto y lente_contacto
                    let {idProducto, codigoBarras, nombre, marca, precioCompra, precioVenta, existencias, estatus} = lente_contacto.producto;
                    let {idLenteContacto, producto, keratometria, fotografia} = lente_contacto;
                    let idsLenteContacto = [idProducto, idLenteContacto];

                    estatus ? isActive = "Activo" : isActive = "Inactivo"
                    estatus ? colorActive = "#00b168" : colorActive = "#f52100"


                    contenido += `<tr>
                                    <td>${idLenteContacto}</td>
                                    <td>${nombre}</td>
                                    <td>${marca}</td>
                                    <td>${existencias}</td>
                                    <td>${keratometria}</td>
                                    <td><img src="data:image/png;base64,${fotografia}" width="100"/></td>
                                    <td style="style="color: ${colorActive}">${isActive}</td>
                                    <td>${codigoBarras}</td>
                                    <td><img style="cursor: pointer" onclick="lc.modificar(${idsLenteContacto})" src="../media/icons/escritura.png" width="30"/> </td>
                                    <td><img style="cursor: pointer" onclick="lc.eliminar(${idLenteContacto})" src="../media/icons/reciclar.png" width="30"/> </td>
                                  </tr>`
                });
                //Insertamos el contenido dentro del cuerpo de la tabla:
                d.getElementById("tbodyLentesC").innerHTML = contenido;
            });

}


function limpiarFormularioDetalle()
{
    d.getElementById("txtNombreLentes").value = "";
    d.getElementById("txtMarca").value = "";
    d.getElementById("txtPrecioCompra").value = "";
    d.getElementById("txtPrecioVenta").value = "";
    d.getElementById("txtExistencias").value = "";
    d.getElementById("txtKeratometria").value = "";
    d.getElementById("tx").value = "";
    d.getElementById("txtexistencias").value = "";
    d.getElementById("txtprecioVenta").value = "";
    d.getElementById("txtprecioCompra").value = "";
}

export function insertar() {
    let url = "../servicio/lente_contacto/save";
    let foto = d.getElementById("txtFoto");

    if (foto.files && foto.files[0]) {

        let reader = new FileReader();

        reader.onload = function (e) {

            let fotocb64 = e.target.result;
            let foto = fotocb64.substring(fotocb64.indexOf(",") + 1, fotocb64.length)

            let producto = {
                idProducto: 0,
                codigoBarras: 0,
                nombre: d.getElementById("txtNombreLentes").value,
                marca: d.getElementById("txtMarca").value,
                precioCompra: d.getElementById("txtPrecioCompra").value,
                precioVenta: d.getElementById("txtPrecioVenta").value,
                existencias: d.getElementById("txtExistencias").value,
                estatus: 0
            };

            let lente_contacto = {
                idLenteContacto: 0,
                producto,
                keratometria: d.getElementById("txtKeratometria").value,
                fotografia: foto
            };

            let datos = {
                //convertimos lente_contacto a texto en forma de un JSON
                datosLenteContacto: JSON.stringify(lente_contacto)
            }

            let params = new URLSearchParams(datos)
            
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
                },
                body: params
            })
                    //obtener respuesta que se obtiene del backend
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
                    .then(res => console.log(res))
        }

        reader.readAsDataURL(foto.files[0])
    }

}


export function enviarModificaciones(idProducto ,idLenteContacto, codigoBarras) {
    let url = "../servicio/lente_contacto/save";
    let producto = {
        idProducto: idProducto,
        codigoBarras: codigoBarras,
        nombre: d.getElementById("m-nombre").value,
        marca: d.getElementById("m-marca").value,
        precioCompra: d.getElementById("m-precioCompra").value,
        precioVenta: d.getElementById("m-precioVenta").value,
        existencias: d.getElementById("m-existencias").value,
        estatus: 1
    };

    let lente_contacto = {
        idLenteContacto: idLenteContacto,
        producto,
        keratometria: d.getElementById("m-keratometria").value,
        fotografia: currentBase64
    };
    let datos = {
        datosLenteContacto: JSON.stringify(lente_contacto)
    };
    let params = new URLSearchParams(datos);
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            .then(res => llenarTabla());

    quitarVentanaModal();
}

export function quitarVentanaModal() {

    setDetalleVisible("tables")
}

export function modificar(idProducto, idLenteContacto, codigoBarras) {
    let url = "../servicio/lente_contacto/getAll?filtro=" + idLenteContacto;
    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                let lente_contacto = data[idLenteContacto - 1];
                
                currentBase64 = lente_contacto.fotografia
                let producto = lente_contacto.producto;
                let inputs = "";

                for (const key in producto) {
                    if (key !== "idProducto") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${producto[key]}">`
                    }
                }
                for (const key in lente_contacto) {
                    if (key !== "idLenteContacto" && key !== "fotografia") {
                        inputs += `<input style="width: 30%;" class="border m-lg-3" id="m-${key}" type="text" placeholder="${key}" value="${lente_contacto[key]}">`
                    }
                }
                
                inputs += `<img src="data:image/png;base64,${currentBase64}" width="100"/>`
                
                inputs += "<input type='file' id='m-foto'/>"
                
                inputs += `<button class="btn mt-2" style="width:97px; height:40px; background-color:#00b168; color:white;" onClick="lc.enviarModificaciones(${idProducto},${idLenteContacto},${codigoBarras})">Guardar</button>
                           <button class="btn mt-2" style="width:97px; height:40px; background-color:#f52100; color:white;" onClick="lc.quitarVentanaModal()">Cancelar</p>`


                d.getElementById("inputs-lentes").innerHTML = inputs;
                setDetalleVisible("modal");
            })
            .catch(err => console.log(err));
}

//Elimina un accesorio
export function eliminar(idLenteContacto) {
    let url = "../servicio/lente_contacto/delete?idLenteContacto=" + idLenteContacto;
    Swal.fire({
        title: 'Borrar registro',
        text: 'Seguro que quieres borrar este registro',
        icon: "warning",
        showCancelButton: true,
        preConfirm: res => {
            if (res) {
                fetch(url)
                        .then(res => res.ok ? res : Promise.reject(res))
                        .then(message => llenarTabla())
                        .catch(err => console.log(err));
                Swal.fire({
                    title: "Todo ha salido bien",
                    text: "Se ha borrado el registro deseado",
                    icon: "success"
                })
                llenarTabla();
            }
        }
    });


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
        d.getElementById("menu-lentesC").style.display = "";
        d.getElementById("form-lentesC").style.display = "none";
        d.getElementById("tablas-lentesC").style.display = "none";
        d.getElementById("lentesC-ventana").style.display = "none"
    } else if (valor === "insertar")
    {
        d.getElementById("menu-lentesC").style.display = "none";
        d.getElementById("form-lentesC").style.display = "";
        d.getElementById("tablas-lentesC").style.display = "none";
        d.getElementById("lentesC-ventana").style.display = "none"
    } else if (valor === "tables")
    {
        llenarTabla()
        d.getElementById("menu-lentesC").style.display = "none";
        d.getElementById("form-lentesC").style.display = "none";
        d.getElementById("tablas-lentesC").style.display = "";
        d.getElementById("lentesC-ventana").style.display = "none"
    } else
    {
        d.getElementById("menu-lentesC").style.display = "none";
        d.getElementById("form-lentesC").style.display = "none";
        d.getElementById("tablas-lentesC").style.display = "";
        d.getElementById("lentesC-ventana").style.display = ""
    }
}

function sanitization(text){
    return text.replaceAll(/[()`\\";._-]/ig, "");
}

function normalizar(text){
    text = text.toUpperCase();
    text = text.replaceAll("Á", "A");
    text = text.replaceAll("É", "E");
    text = text.replaceAll("Í", "I");
    text = text.replaceAll("Ó", "O");
    text = text.replaceAll("Ú", "U");
    return text;
}

function formatText(text){
    return sanitization(normalizar(text));
}
