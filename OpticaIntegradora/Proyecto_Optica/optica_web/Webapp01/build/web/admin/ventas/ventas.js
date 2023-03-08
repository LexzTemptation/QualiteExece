const d = document;
const w = window;
let listaDetalleProducto = [];
let productoRec = [];
let grandTotal = 0;
let cntTblVenta = "";
let i = 0;
let productos = [];
let idsProductos = [];

export function inicializar(valor) {
    configureTableFilter(d.getElementById("txtBuscarProd"),
            d.getElementById("tblVentas"));
    buscar();
}

export function buscar() {
    let url = "../servicio/venta/getAll";
    fetch(url)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                let contenido = "";

                productoRec = data;

                data.map(producto => {
                    let {idProducto,
                        codigoBarras,
                        nombre,
                        marca,
                        precioCompra,
                        precioVenta,
                        existencias,
                        estatus} = producto;

                    contenido += `<tr>
                                    <td>${idProducto}</td>
                                    <td>${codigoBarras}</td>
                                    <td>${nombre}</td>
                                    <td>${marca}</td>
                                    <td>${precioVenta}</td>
                                    <td id="ex-${nombre}">${existencias}</td>
                                    <td>${estatus}</td>
                                    <td><button class="btn bg-success" onclick="vm.agregarProducto(${idProducto})">
                                        <i class="bi bi-bag-plus-fill text-black-50"></i>
                                    </button></td>
                                  </tr>`;
                })
                d.getElementById("tbodyVentas").innerHTML = contenido;
            })
}

export function agregarProducto(idProductoRec) {
    productoRec.map(data => {
        if (data.idProducto === idProductoRec) {
            let producto = data;

            let idCant = "cant-" + producto.nombre;
            let idDesc = "desc-" + producto.nombre;
            let idTota = "tot-" + producto.nombre;

            cntTblVenta += `<tr>
                                <td>${producto.codigoBarras}</td>
                                <td>${producto.nombre}</td>
                                <td>${producto.precioVenta}</td>
                                <td><input placeholder="Cantidad" value="1" id="${idCant}" 
onChange="vm.calcularTotal(${producto.idProducto})" 
type="number" style="width: 90%"></td>
                                <td><input placeholder="Descuento %" value="0" id="${idDesc}"
onChange="vm.calcularTotal(${producto.idProducto})" 
type="number" style="width: 90%"></td>
                                <td><input value ="${producto.precioVenta}" id="${idTota}" style="width: 90%"/></td>
                            </tr>`;
            idsProductos.push(producto.idProducto);

            d.getElementById("tbodyRealVenta").innerHTML = cntTblVenta;
            setGrandTotal();
        }
    });
}

export function realizarVenta() {
    productoRec.map(data => {
        let producto = data;
        for (let i = 0; i < idsProductos.length; i++) {
            if (producto.idProducto === idsProductos[i]) {
                let idCant = "cant-" + producto.nombre;
                let idDesc = "desc-" + producto.nombre;
                let idTota = "tot-" + producto.nombre;

                let datos = {
                    producto: producto,
                    cantidad: d.getElementById(idCant).value,
                    precioUnitario: d.getElementById(idTota).value,
                    descuento: d.getElementById(idDesc).value
                };
                productos.push(datos);
            }
        }
    });
    let jsonEmpleado = w.localStorage.getItem("Empleado");
    let objEmpleado = JSON.parse(jsonEmpleado);
    let detalleVentaProducto = {
        venta: {
            idVenta: 0,
            empleado: objEmpleado,
            clave: 0
        },
        productos: productos
    };
    let datos = {
        datosVenta: JSON.stringify(detalleVentaProducto)
    };

    let params = new URLSearchParams(datos);

    fetch("../servicio/venta/insertarVenta", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
        },
        body: params
    }).then(res => {
        cntTblVenta = "";
        d.getElementById("tbodyRealVenta").innerHTML = cntTblVenta;
        d.getElementById("lblTotal").innerHTML = grandTotal;

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
        });
        Toast.fire({
            icon: 'success',
            title: '¡Venta realizada! $' + grandTotal
        });
        buscar();
        
        grandTotal = 0;
    });
    
}

export function calcularTotal(idProd) {
    productoRec.map(data => {
        let total = 0;
        if (data.idProducto === idProd) {
            let producto = data;

            let idCant = "cant-" + producto.nombre;
            let idDesc = "desc-" + producto.nombre;
            let idTota = "tot-" + producto.nombre;

            let descuentoJ = d.getElementById(idDesc).value;
            let precioJ = producto.precioVenta;
            let cantidadJ = d.getElementById(idCant).value;

            total = cantidadJ * (precioJ - (precioJ * (descuentoJ * 0.01)));

            d.getElementById(idTota).value = total;
            setGrandTotal();
        }
    });
}

export function setGrandTotal() {
    let grandTotal = 0;
    productoRec.map(data => {
        let producto = data;
        let idTota = "tot-" + producto.nombre;
        grandTotal += parseFloat(d.getElementById(idTota).value, 10);
        console.log(grandTotal);
        d.getElementById("lblTotal").innerHTML = grandTotal;
    });
}