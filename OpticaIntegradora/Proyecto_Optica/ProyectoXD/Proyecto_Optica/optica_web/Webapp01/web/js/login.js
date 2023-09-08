
let currentEmpleado = {
    idEmpleado:"", numeroUnico:"", 
    usuario: {
        idUsuario:"",
        nombre:"",
        contrasenia:"",
        rol:"",
        lastToken:"",
        dateLastToken:""
    },
    estatus:"",
    persona:{
        idPersona:"",
        nombre:"",
        apellidoPaterno:"",
        apellidoMaterno:"",
        genero:"",
        fechaNacimiento:"",
        calle:"",
        numero:"",
        colonia:"",
        cp:"",
        ciudad:"",
        estado:"",
        telCasa:"",
        telMovil:"",
        email:""
    }
}

const w = window

async function encriptar(texto){
    const encoder = new TextEncoder()
    const data = encoder.encode(texto)
    const hash = await crypto.subtle.digest("SHA-256", data)
    const hashArray = Array.from(new Uint8Array(hash))
    const hashHex = hashArray.map(b => b.toString(16).padStart(2, "0")).join("")
    return hashHex
}

w.addEventListener("DOMContentLoaded", e => {

    w.localStorage.setItem("isLogin", "false");

})

async function login()
{

    let usuario = document.getElementById("txtUsuario").value;
    let password = document.getElementById("txtPassword").value;
    
    let crypPassword = await encriptar(password)

    let url2 = `./servicio/empleado/login?user=${usuario}&password=${password}`

    fetch(url2)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                
                currentEmpleado.persona.calle = "Mision de la independencia"
                
                if(data.message){
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
                    icon: 'warning',
                    title: data.message
                })
                } 

               w.localStorage.setItem("userName", data["persona"]["nombre"])
               w.localStorage.setItem("apellidoPaterno", data["persona"]["apellidoPaterno"])
               w.localStorage.setItem("id", data["usuario"]["idUsuario"])

               w.localStorage.setItem("isLogin", "true")
               window.location.replace('./admin/index.html');

            })

}