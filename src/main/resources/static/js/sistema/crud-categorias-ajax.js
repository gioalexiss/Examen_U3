//CRUD Usado AJAX
//Variables globales
let idCategoriaEliminar=0;

function obtenerCategorias() {
    $.ajax({
        method:"GET",
        url: "/v1/api/categorias",
        data: {},
        success: function( resultado ) {
            if(resultado.estado===1){
                let tabla=$('#example').DataTable();
                categorias = resultado.categorias;

                categorias.forEach(categoria =>{
                    let botones ='<button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="seleccionarCategoriaActualizar('+categoria.id+');">Edit</button>';
                    botones = botones + ' <button type="button" class="btn btn-danger mb-2" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="seleccionarCategoriaEliminar('+categoria.id+');">Delete</button>\n';
                    tabla.row.add([
                        categoria.id,
                        categoria.nombre,
                        botones
                    ]).node().id='renglon_'+categoria.id;
                    tabla.draw()
                })
            }
        },
        error:function (xhr,error,mensaje){

        }
    });
}

function guardarCategoria(){
    nombre_categoria = document.getElementById("nombre_categoria").value;
    //Validar de forma simple los campos - EXPRESIONES REGULARES

    if(validarNombreCategoria(nombre_categoria)){
        $.ajax({
            url: "/v1/api/categorias",
            contentType:"application/json",
            method:"POST",
            data: JSON.stringify({
                nombre:nombre_categoria

            }),
            success: function( resultado ) {
                if(resultado.estado==1){
                    let botones ='<button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="seleccionarCategoriaActualizar('+resultado.categoria.id+');">Edit</button>';
                    botones = botones + ' <button type="button" class="btn btn-danger mb-2" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="seleccionarCategoriaEliminar('+resultado.categoria.id+');">Delete</button>\n';

                    let tabla = $('#example').DataTable();
                    tabla.row.add([
                        resultado.categoria.id,
                        resultado.categoria.nombre,
                        botones
                    ]).node().id='renglon_'+resultado.categoria.id;

                    tabla.draw()
                    //Limpiar Formulario

                    document.getElementById("nombre_categoria").value = "";
                    //Ocultar la Modal JQuery
                    $('#basicModal').modal('hide');
                    alert(resultado.mensaje);
                }else{
                    //Todo mal
                    alert(resultado.mensaje);
                }
            },
            error:function (xhr,error,mensaje) {
                //Se dispara la funcion si no conexion al servidor
                alert("Error de comunicacion: "+error);
            }
        });
    }else{

    }
}

function seleccionarCategoriaActualizar(id) {
    idCategoriaActualizar=id;
    $.ajax({
        method:"GET",
        url: "/v1/api/categorias/actualizar/"+idCategoriaActualizar,
        data: {},
        success: function( resultado ) {
            if(resultado.estado===1){
                let categoria = resultado.categoria;
                $('#nombre_categoria_editar').val(categoria.nombre);
            }else{
                alert(resultado.mensaje);
            }
        },
        error:function (xhr,error, mensaje) {
            alert(mensaje);
        }
    });

}

function actualizarCategoria() {
    //1.- Obtener los datos que existen en el modal
    nombre_categoria=$('#nombre_categoria_editar').val();
    if(validarNombreCategoriaEditar(nombre_categoria)){
        $.ajax({
            url: "/v1/api/categorias/actualizar/"+idCategoriaActualizar,
            contentType:"application/json",
            method:"POST",
            data: JSON.stringify({
                id:idCategoriaActualizar,
                nombre:nombre_categoria
            }),
            success: function( resultado ) {
                if(resultado.estado==1){
                    let tabla = $('#example').DataTable();
                    datos = tabla.row("#renglon_"+idCategoriaActualizar).data()
                    datos[1]=nombre_categoria;
                    tabla.row("#renglon_"+idCategoriaActualizar).data(datos);
                    $('#editModal').modal('hide');

                    alert(resultado.mensaje);
                }else{
                    //Todo mal
                    alert(resultado.mensaje);
                }
            },
            error:function (xhr,error,mensaje) {
                alert("Error de comunicacion: "+error);
            }
        });
    }else{
    }

}

function seleccionarCategoriaEliminar(id){
    let datosCategoria=$('#example').DataTable().row('#renglon_'+id).data()
    $('#nombre_eliminar').text(datosCategoria[1])
    idCategoriaEliminar=id
}

function eliminarCategoria() {
    $.ajax({
        method: "POST",
        url: "/v1/api/categorias/eliminar",
        contentType:"application/json",
        data:JSON.stringify({
            id:idCategoriaEliminar,
        }),
        success: function( resultado ) {
            if(resultado.estado===1){
                //Eliminar el renglon del DataTable
                $('#example').DataTable().row('#renglon_'+idCategoriaEliminar).remove().draw();
                alert(resultado.mensaje);
            }else{
                alert(resultado.mensaje)
            }
        },
        error:function (xhr,error,mensaje){
            alert("Error de comunicacion "+error)
        }
    });
}

function validarNombreCategoria(nombre) {
    const errores = [];
    const regex = /^[a-zA-Z0-9\s]+$/;

    if (nombre.trim().length <= 3) {
        errores.push("El nombre debe tener más de 3 caracteres.");
    }

    if (!regex.test(nombre)) {
        errores.push("El nombre no debe contener caracteres especiales.");
    }

    const alerta = document.getElementById("alerta-categoria");

    if (errores.length > 0) {
        alerta.innerHTML = errores.join("<br>");
        alerta.classList.remove("d-none");
        return false;
    } else {
        alerta.classList.add("d-none");
        return true;
    }
}
function validarNombreCategoriaEditar(nombre) {
    const errores = [];
    const regex = /^[a-zA-Z0-9\s]+$/;

    if (nombre.trim().length <= 3) {
        errores.push("El nombre debe tener más de 3 caracteres.");
    }

    if (!regex.test(nombre)) {
        errores.push("El nombre no debe contener caracteres especiales.");
    }

    const alerta = document.getElementById("alerta-editar-categoria");

    if (errores.length > 0) {
        alerta.innerHTML = errores.join("<br>");
        alerta.classList.remove("d-none");
        return false;
    } else {
        alerta.classList.add("d-none");
        return true;
    }
}




