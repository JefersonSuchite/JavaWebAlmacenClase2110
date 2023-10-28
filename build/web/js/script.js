document.getElementById("cargarProductosButton").addEventListener("click", function() {
    cargarProductos();
});

document.getElementById("formularioProducto").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que se envíe el formulario por defecto
    guardarProducto();
});
document.getElementById("cargarProductosButton").addEventListener("click", function() {
    cargarProductos();
});

document.getElementById("formularioProducto").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que se envíe el formulario por defecto
    guardarProducto();
});
function cargarProductos() {
    fetch('MostrarProductos') // Hacer una solicitud GET al servlet MostrarProductos
        .then(response => response.text())
        .then(data => {
            document.getElementById('tablaProductos').innerHTML = data;
        })
        .catch(error => {
            console.error('Error al cargar productos: ', error);
        });
}

function guardarProducto() {
    const nombre = document.getElementById("nombre").value;
    const precio = document.getElementById("precio").value;
    const existencia = document.getElementById("existencia").value;

    const formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('precio', precio);
    formData.append('existencia', existencia);

    fetch('AgregarProducto', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            cargarProductos(); // Cargar la tabla actualizada después de guardar
        })
        .catch(error => {
            console.error('Error al guardar el producto: ', error);
        });
}
