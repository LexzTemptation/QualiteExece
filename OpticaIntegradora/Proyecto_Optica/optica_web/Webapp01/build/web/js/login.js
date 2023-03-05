const w = window

w.addEventListener("DOMContentLoaded", e => {


    w.localStorage.setItem("isLogin", "false");

})

function cambio() {
    console.log("cambio")
}
async function encriptar(texto) {
    const encoder = new TextEncoder();
    const data = encoder.encode(texto);
    const hash = await crypto.subtle.digest('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(hash));
    const hashHex = hashArray.map((b) => b.toString(16).padStart(2, '0')).join('');
    return hashHex;
}
function login()
{

    let usuario = document.getElementById("txtUsuario").value;
    let password = document.getElementById("txtPassword").value;

    //http://localhost:8080/Optica_Web

    let url2 = `../Optica_Web/servicio/empleado/login?user=${usuario}&password=${password}`

    fetch(url2)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {


                w.localStorage.setItem("userName", data["persona"]["nombre"])
                w.localStorage.setItem("apellidoPaterno", data["persona"]["apellidoPaterno"])

                w.localStorage.setItem("isLogin", "true")
                window.location.replace('./admin/index.html');


                w.localStorage.setItem("usuario", JSON.stringify(data));
                w.localStorage.setItem("lastToken", data.usuario.lastToken);
                w.localStorage.setItem("Empleado", JSON.stringify(data));
                
                const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 2000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                        console.log(data);
                    }
                })


            })
            .catch(error => {
                alert(error.message)
            }
            )

}