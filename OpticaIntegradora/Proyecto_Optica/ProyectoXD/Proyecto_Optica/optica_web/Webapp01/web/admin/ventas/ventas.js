let d = document

let currentProduct
let currentPrecioUnitario
let vp = []
let totalDeCompra = 0

let btnEnviar = d.getElementById("btnEnviar")

export function inicializar() {
    console.log("echo")
}

export async function hacerBusqueda() {

    let codigo = d.getElementById("codigoBarras").value

    let url = `../servicio/ventas/getAll?filtro=${codigo}`

    let res = await fetch(url)

    let data = await res.json()

    insertarRegistro(data)
}

function insertarRegistro(data) {

    let {idProducto, nombre, codigoBarras, precioVenta} = data[0]

    currentProduct = data[0]

    let contenido = `
    
        <tr>
            <td>${codigoBarras}</td>
            <td>${nombre}</td>
            <td>$${precioVenta}$</td>
            <td><input id="cantidad" value="1" type="number" placeholder="cantidad deseada"/></td>
            <td><input id="descuento" value="0" type="number" placeholder="Descuento"/></td>
            <td> <button class="btn btn-success" onclick="v.agregar()">Agregar</button> </td>
        </tr>
    `

    d.getElementById("tbodyVentas").innerHTML = contenido

}

export async function agregar() {

    let {idProducto, nombre, codigoBarras, precioVenta, existencias} = currentProduct

    let cantidad = d.getElementById("cantidad").value

    if (cantidad > existencias) {

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
                    icon: 'warning',
                    title: `Solo contamos con ${existencias} productos`
                })

    } else {
        
        let descuento = d.getElementById("descuento").value

        let precio_unitario = precioVenta * cantidad
        
        let descuentoUnidad = ( precio_unitario * (descuento/100))
        
        precio_unitario = (precio_unitario - descuentoUnidad).toFixed(2)
        
        let datos = {producto: currentProduct, cantidad, precio_unitario, descuento}

        vp.push(datos)

        let contenido = `
        <tr>
            <td>${codigoBarras}</td>
            <td>${nombre}</td>
            <td>$${precioVenta}</td>
            <td>${cantidad}</td>
            <td>${descuento}%</td>
            <td>$${precio_unitario}$</td>
        </tr>
    `

        d.getElementById("tbodyVentasCompra").innerHTML += contenido

    }

}


btnEnviar.addEventListener("click", e => {

    let url = "../servicio/ventas/insertarVenta"

    let DetalleVentaP = {
        venta: {
            idVenta: 0,
            empleado: {
                idEmpleado: localStorage.getItem("id"),
                numeroUnico: "asd",
                usuario: {},
                estatus: 1,
                persona: {}
            },
            clave: new Date().toLocaleTimeString() + Math.random().toString()
        },
        vp
    }

    let datos = {datosVenta: JSON.stringify(DetalleVentaP)}

    let params = new URLSearchParams(datos)

    mostrarTotalCompra()

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    })
            .then(res => console.log(res))
})


function mostrarTotalCompra() {

    vp.map(compra => {

        totalDeCompra += parseInt(compra.precio_unitario)
    })

    let contenido = `
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>${totalDeCompra}</td>
        </tr>
    `

    d.getElementById("tbodyVentasCompra").innerHTML += contenido
}