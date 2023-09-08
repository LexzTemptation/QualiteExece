
function login()
{
    let usuario = document.getElementById("txtUsuario").value;
    let password = document.getElementById("txtPassword").value;
    
    window.location.replace('admin/index.html');
}

function logout()
{
    window.location.replace('OQEXE/index.html');
}

function calcularFactorial()
{
    let n = 5;
    let factorial = 1;
    
    for(let i = 1; i < n; i++)
        factorial = factorial * i;
    
    alert("El factorical de " + n + " es " + factorial);
}

function calcularCosto()
{
    let numAlumnos = parseInt(document.getElementById("txtNumeroAlumno").value);
    let costoAlumno = 0;
    let costoTotal = 0;
    
    if (numAlumnos >= 100)
    {
        costoAlumno = 65.0;
        costoTotal = costoAlumno * numAlumnos;
    }
    else if (numAlumnos >= 50)
    {
        costoAlumno = 70;
        costoTotal = costoALumno * numAlumnos;
    }
    else if (numAlumnos >= 30)
    {
        costoAlumno = 95;
        costoTotal = costoAlumno * numAlumnos;
    }
    else
    {
        costoTotal = 4000;
        costoALumno = costoTotal / numAlumnos;
    }
    document.getElementById("resultado").value = "El costo total del viaje es de:" + costoTotal + ", " + "El costo por alumno es de: " + costoAlumno;
}