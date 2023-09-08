let d = document

let datosCliente, datosExamenVista, datosMica, datosMaterial, datosArmazon, datosTratamiento

let lentesAgregadosServidor = []
let lentesAgregadosTable = []

let lvp = []

let DetalleVentaPresupuesto = {
    venta: {
        idVenta: 0,
        empleado: {
            idEmpleado: localStorage.getItem("id")
        },
        clave: new Date().toLocaleTimeString() + Math.random().toString()
    },
    lvp
}

export async function inicializar() {

    await cargarTodosLosDatos()

    cargarClientes()
    cargarMicas()
    cargarMateriales()
    cargarTratamientos()
    cargarArmazones()
}


async function cargarTodosLosDatos() {

    //datos Cliente
    let urlC = "../servicio/cliente/getAll"
    let resC = await fetch(urlC)
    datosCliente = await resC.json()


    //datos ExamenVista
    let urlE = "../servicio/examenVista/getAll"
    let resE = await fetch(urlE)
    datosExamenVista = await resE.json()

    //datos Mica
    let urlM = "../servicio/mica/getAll"
    let resM = await fetch(urlM)
    datosMica = await resM.json()

    //datos Material
    let urlMA = "../servicio/material/getAll"
    let resMA = await fetch(urlMA)
    datosMaterial = await resMA.json()

    //datos Tratamiento
    let urlT = "../servicio/tratamiento/getAll"
    let resT = await fetch(urlT)
    datosTratamiento = await resT.json()

    //datos Armazon
    let urlA = "../servicio/armazon/getAll"
    let resA = await fetch(urlA)
    datosArmazon = await resA.json()

}

export async function cargarExamenVista(target) {

    let value = target.value

    let selectsExamenV = d.getElementById("selects-examenV")

    let contenido = "<option>Examen de la Vista</option>"

    datosExamenVista.map(mica => {

        let {idExamenVista, idCliente, clave} = mica

        if (idCliente == value) {
            contenido += `
            
                <option value=${idExamenVista}>${clave}</option>
            `
        }
    })

    selectsExamenV.innerHTML = contenido

}


async function cargarArmazones() {

    let selectsArmazones = d.getElementById("selects-armazones")

    let contenido

    datosArmazon.map(armazon => {

        let {idArmazon} = armazon

        let {nombre, precioVenta} = armazon["producto"]

        contenido += `
            
            <option data-precio="${precioVenta}" value="${idArmazon}">${nombre} ($${precioVenta})</option>
            
        `
    })

    selectsArmazones.innerHTML += contenido
}

async function cargarClientes() {

    let selectCliente = d.getElementById("selects-cliente")

    let contenido

    datosCliente.map(cliente => {

        let {idCliente} = cliente

        let {nombre, apellidoPaterno, apellidoMaterno} = cliente["persona"]

        contenido += `
        
            <option id="${nombre}" value=${idCliente}>${nombre} ${apellidoPaterno} ${apellidoMaterno}</option>
        `

    })

    selectCliente.innerHTML += contenido

}

async function cargarMicas() {

    let selectsMica = d.getElementById("selects-micas")

    let contenido

    datosMica.map(mica => {

        let {idTipoMica, nombre, precioVenta} = mica

        contenido += `

            <option data-precio="${precioVenta}" value=${idTipoMica}>${nombre} ($${precioVenta})</option>
        `
    })

    selectsMica.innerHTML += contenido
}

async function cargarMateriales() {

    let selectsMaterial = d.getElementById("selects-materiales")

    let contenido

    datosMaterial.map(material => {

        let {idMaterial, nombre, precioVenta} = material

        contenido += `
            
            <option data-precio="${precioVenta}" value=${idMaterial}>${nombre} ($${precioVenta})</option>
        `
    })

    selectsMaterial.innerHTML += contenido
}

async function cargarTratamientos() {

    let areaTratamientos = d.getElementById("area-tratamientos")

    let contenido = ""

    datosTratamiento.map(tratamiento => {

        let {idTratamiento, nombre, precioVenta} = tratamiento

        contenido += `
            
             <div style="margin-right: 5px">
            
                <input class="form-check-input" type="checkbox" data-precio="${precioVenta}" data-idT="${idTratamiento}" id="${nombre}"/>
                    <label class="form-check-label" for="${nombre}">
                        ${nombre}
                    </label>
             </div>
        `
    })

    areaTratamientos.innerHTML = contenido
}

export function agregarLente() {

    let tratamientosElegidos = []
    let tratamientosElegidosServer = []

    let cliente = d.getElementById("selects-cliente")
    let examenV = d.getElementById("selects-examenV")
    let armazon = d.getElementById("selects-armazones")
    let descuento = d.getElementById("descuento").value
    let mica = d.getElementById("selects-micas")
    let material = d.getElementById("selects-materiales")
    let alturaOblea = d.getElementById("oblea").value
    let cantidad = d.getElementById("cantidad").value
    
    let idA = armazon.value, idMi = mica.value, idMa = material.value, idEx = examenV.value
    
    //Traer checkBoxes

    let containerC = d.getElementById("area-tratamientos");
    let checkBoxes = containerC.querySelectorAll("input")

    let totalTratamientos = 0

    checkBoxes.forEach(box => {

        if (box.checked) {

            let id = box.id, precio = parseInt(box.dataset.precio), idTratamiento = box.dataset.idt

            tratamientosElegidos.push({id, precio})

            tratamientosElegidosServer.push({idPresupuestoLentes: 0, idTratamiento})

            totalTratamientos += precio
        }
    })

    //Creacion De Los Datos de la Tabla 

    let clienteTexto = cliente.options[cliente.selectedIndex].text

    let examenVistaTexto = examenV.options[examenV.selectedIndex].text

    let micaTexto = mica.options[mica.selectedIndex].text, micaPrecio = mica.options[mica.selectedIndex].dataset.precio

    let armazonTexto = armazon.options[armazon.selectedIndex].text, armazonPrecio = armazon.options[armazon.selectedIndex].dataset.precio

    let materialTexto = material.options[material.selectedIndex].text, materialPrecio = material.options[material.selectedIndex].dataset.precio

    let precioTotalPorLente = (parseInt(micaPrecio) + parseInt(materialPrecio) + parseFloat(armazonPrecio) + totalTratamientos) * (cantidad)

    let descuentoLente = (precioTotalPorLente * (descuento / 100))

    precioTotalPorLente -= descuentoLente

    precioTotalPorLente = precioTotalPorLente.toFixed(2)

    let datosTable = {
        cliente: clienteTexto,
        examenV: examenVistaTexto,
        descuento,
        mica: `${micaTexto}`,
        material: `${materialTexto}`,
        tratamientos: tratamientosElegidos,
        armazon: armazonTexto,
        alturaOblea,
        cantidad,
        precioTotalPorLente
    }

    lentesAgregadosTable.push(datosTable)

    limpiarFormulario()
    
    
    //Creacion De Los Datos del Server

    let currentLente =
            {
                presupuesto: {
                    idPresupuesto: 0,
                    idExamenVista: idEx,
                    clave: new Date().toLocaleTimeString() + Math.random().toString(),
                    presupuestoLentes: {
                        idPresupuestoLentes: 0,
                        alturaOblea,
                        idTipoMica: idMi,
                        idMaterial: idMa,
                        idArmazon: idA,
                        lplt: tratamientosElegidosServer
                    }
                },
                cantidad, precioUnitario: precioTotalPorLente, descuento
            }

    lvp.push(currentLente)  

}


export async function hacerCompra() {

    let url = "../servicio/ventas/insertarVentaLentes"

    console.log(DetalleVentaPresupuesto)

    let datos = {datosVentaLentes: JSON.stringify(DetalleVentaPresupuesto)}

    let params = new URLSearchParams(datos)

    let res = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })

    console.log(res)
    
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
                    title: `Compra echa`
                })

}

export function ventanaModal() {

    let ventana = d.getElementById("vetanModalLentes")
    let table = d.getElementById("tbodyLentes")

    ventana.style.display = "block"

    let contenido = ""
    let contenidoTratamientos = ""

    lentesAgregadosTable.map(lente => {

        let {cliente, examenV, descuento, mica, material, armazon, alturaOblea, cantidad, tratamientos, precioTotalPorLente} = lente

        tratamientos.forEach(tratamiento => {

            let {id, precio} = tratamiento

            contenidoTratamientos += `${id} ($${precio})
                                      <br/>
                                      
                                       `
        })

        contenido += `

            <tr class="bg-white"> 
                
                <td class="text-black">${cliente}</td>
                <td class="text-black">${examenV}</td>
                <td class="text-black">${descuento}</td>
                <td class="text-black">${mica}</td>
                <td class="text-black">${material}</td>
                <td class="text-black">${armazon}</td>
                <td>${contenidoTratamientos}</td>
                <td class="text-black">${alturaOblea}</td>
                <td class="text-black">${cantidad}</td>
                <td class="text-black">$${precioTotalPorLente}</td>
            </tr>
        `

        contenidoTratamientos = ""

    })

    table.innerHTML = contenido

}

function limpiarFormulario() {

    d.getElementById("selects-cliente").value = "Cliente"
    d.getElementById("selects-examenV").value = "Examen de la Vista"
    d.getElementById("selects-micas").value = "Mica"
    d.getElementById("selects-materiales").value = "Material"
    d.getElementById("selects-armazones").value = "Armazon"
    d.getElementById("descuento").value = 0
    d.getElementById("oblea").value = ""
    d.getElementById("cantidad").value = 1

    let containerC = d.getElementById("area-tratamientos");
    let checkBoxes = containerC.querySelectorAll("input")

    checkBoxes.forEach(box => box.checked = false)
}

export function removerVentana() {

    let ventana = d.getElementById("vetanModalLentes")

    ventana.style.display = "none"
}
;