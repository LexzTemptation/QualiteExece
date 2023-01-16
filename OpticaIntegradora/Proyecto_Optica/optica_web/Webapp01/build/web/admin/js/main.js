const w = window
let cm = null
let em = null
let ac = null
let az = null
let sl = null
let lc = null

let d = document

const burger = d.getElementById("burger")
const menu = d.getElementById("menu")
const navItem = d.getElementsByClassName("nav-link")

w.addEventListener("DOMContentLoaded", e => {

    let isLogin = w.localStorage.getItem("isLogin");

    let userName = w.localStorage.getItem("userName")
    let apellidoPaterno = w.localStorage.getItem("apellidoPaterno")

    let nombre_completo = `${userName} ${apellidoPaterno}`

    if (isLogin === "false")
        w.location.pathname = "/Optica_Web/index.html";

    document.getElementById("userName").textContent = nombre_completo

    let windows = navigator.userAgent.toLowerCase().match(/windows/)
    let linux = navigator.userAgent.toLowerCase().match(/linux/)
    let mac = navigator.userAgent.toLowerCase().match(/mac/)

    if (!windows && !linux && !mac) {
        burger.style.display = ""
    }
})

d.addEventListener("keypress", e => {

    if (e.key === "q") {

        if (e.originalTarget.localName !== "input") {
            menu.classList.toggle("move");

        }
    }
})

burger.addEventListener("click", e => {
    menu.classList.toggle("move2");
})

for (let i = 0; i < navItem.length; i++) {
    navItem[i].classList.add("text-light")
}

function cargarModulo(modulo)
{

    switch (modulo) {
        case "clientes":
            fetch("clientes/clientes.html")
                    .then(response => {
                        return response.text();
                    })
                    .then(function (html)
                    {
                        d.getElementById('root').innerHTML = html
                        import('../clientes/clientes.js')
                                .then(obj => {
                                    cm = obj
                                    cm.inicializar("menu")
                                })

                    })
            break;

        case "empleados":
            fetch("empleados/empleados.html")
                    .then(response => {
                        return response.text()
                    })
                    .then(function (html) {
                        d.getElementById("root").innerHTML = html
                        import("../empleados/empleados.js")
                                .then(obj => {
                                    em = obj
                                    em.inicializar("menu")
                                })
                    })

            break;

        case "accesorios":
            fetch("productos/accesorios/accesorios.html")
                    .then(response => {
                        return response.text()
                    })
                    .then(function (html) {

                        d.getElementById("root").innerHTML = html
                        import("../productos/accesorios/accesorios.js")
                                .then(obj => {
                                    ac = obj
                                    ac.inicializar("menu")
                                })

                    })

            break;

        case "armazon":
            fetch("productos/armazon/armazon.html")
                    .then(response => {
                        return response.text()
                    })
                    .then(function (html) {

                        d.getElementById("root").innerHTML = html
                        import("../productos/armazon/armazon.js")
                                .then(obj => {
                                    az = obj
                                    az.inicializar("menu")
                                })
                    })

            break;

        case "soluciones":
            fetch("productos/soluciones/soluciones.html")
                    .then(response => {
                        return response.text()
                    })
                    .then(function (html) {

                        d.getElementById("root").innerHTML = html
                        import("../productos/soluciones/soluciones.js")
                                .then(obj => {
                                    sl = obj
                                    sl.inicializar("menu")
                                })
                    })

            break;

        case "lentesC":
            fetch("productos/Lentes_Contacto/Lentes_Contacto.html")
                    .then(response => {
                        return response.text()
                    })
                    .then(function (html) {

                        d.getElementById("root").innerHTML = html
                        import("../productos/Lentes_Contacto/Lentes_Contacto.js")
                                .then(obj => {
                                    lc = obj
                                    lc.inicializar("menu")
                                })
                    })

            break;


    }
}


