# CRUD de Categorías (AJAX - JQUERY - JSON - DATATABLE - MYSQL - SPRING BOOT)

Este proyecto implementa un sistema básico de gestión de categorías con operaciones CRUD (Crear, Leer, Actualizar y Eliminar), utilizando:


---

## Tecnologías utilizadas

| Tecnología | Uso                                         |
|-----------|---------------------------------------------|
| Java 23   | Lenguaje principal                          |
| Spring Boot | Backend REST + acceso a BD                  |
| JDBC Template | CRUD de producto                            |
| MySQL     | Base de datos                               |
| jQuery / AJAX | Comunicación frontend-backend               |
| DataTables | Tabla interactiva con búsqueda y paginación |
| Bootstrap 5 | Diseño responsivo y modales                 |
| JSON      | Formato de intercambio de datos             |

---

## Estructura del proyecto
-- examen_3  
├── src/  
│ ├── controllers/  
│ ├── entities/  
│ ├── models/  
│ ├── services/  
│ ├── repositories/  
│ └── main/resources/  
├── static/ # Archivos JS, CSS  
├── templates/  
├── pom.xml  
└── README.md  

## Tabla 'categorias' utilizada
```sql
CREATE TABLE categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50)
);
```

## ¿Como ejecutar el proyecto?
- Java 17  
- Maven
- MySQL
- Un IDE como IntelliJ o VS Code

### Pasos
- Clona el proyecto desde GitHub
- Configuracion de la base de datos en "application.properties"
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base
spring.datasource.username=tu_user
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
- Compila el proyecto
- Accede desde tu navegador usando: "http://localhost:8087"

## Funciones
- Listar categorias con el uso de DataTables
- Crear categoria con validacion
- Editar categoria con validacion en el modal
- Eliminar categoria con confirmacion

## Validaciones
- El nombre de una categoria debe tener más de 3 caracteres
- No se permiten caracteres especiales 
- Estos errores se muestran dentro del modal, ya se en el de agregar o editar

# Autor
Desarrollado por Giovanni Alexis Rodríguez González  
Examen Unidad 3 - UPIIZ IPN