
const w = window

w.addEventListener("DOMContentLoaded", e => {

    w.localStorage.setItem("isLogin", "false");

})

function cambio() {
    console.log("cambio")
}

function login()
{

    let usuario = document.getElementById("txtUsuario").value;
    let password = document.getElementById("txtPassword").value;
    
    //http://localhost:8080/Optica_Web

    let url2 = `./servicio/empleado/login?user=${usuario}&password=${password}`

    fetch(url2)
            .then(res => res.ok ? res.json() : Promise.reject(res))
            .then(data => {
                
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

               w.localStorage.setItem("isLogin", "true")
               window.location.replace('./admin/index.html');

            })

}

function logout()
{
    window.location.replace('OQEXE/index.html');
}