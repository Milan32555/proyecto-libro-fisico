# Proyecto Librería: Cliente + Microservicio GraphQL

Este repositorio contiene una solución completa para la gestión de libros físicos en una librería. Está compuesto por dos partes:

- Un cliente de escritorio en C# con Windows Forms: `ClienteLibroFisico`
- Un microservicio desarrollado en Java/Spring Boot con GraphQL: `ServicioLibroFisico-master`

La arquitectura está dividida en dos capas:

1. El cliente consume las operaciones del servidor GraphQL.
2. El servidor expone los servicios y maneja la lógica de negocio y validaciones.

---

## 1. Descripción general

La aplicación permite administrar libros físicos con acciones como:

- Insertar libro
- Consultar libro por ISBN
- Actualizar libro
- Eliminar libro
- Listar libros
- Filtrar por autor y tipo de tapa

El cliente se conecta al servicio GraphQL que corre en `http://localhost:8081/graphql`.

---

## 2. Estructura del proyecto

```text
Proyecto/
├── ClienteLibroFisico/
│   ├── ClienteLibroFisico.sln
│   ├── ClienteLibroFisico/
│   │   ├── Modelo/
│   │   ├── Servicios/
│   │   ├── Vistas/
│   │   ├── Program.cs
│   │   └── App.config
│   └── README.md
│
├── ServicioLibroFisico-master/
│   ├── pom.xml
│   ├── mvnw
│   ├── src/
│   └── target/
│
└── README.md
```

---

## 3. Requisitos

### Cliente .NET

- Windows 10 o 11
- .NET Framework 4.8
- Visual Studio 2022 o Visual Studio Build Tools

### Servidor Java

- Java 17
- Maven
- Spring Boot 4.1.1

---

## 4. Cómo ejecutar el proyecto

### 4.1 Ejecutar el microservicio

Abre una terminal en la carpeta `ServicioLibroFisico-master` y ejecuta:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
mvnw.cmd clean package
mvnw.cmd spring-boot:run
```

El servicio quedará disponible en:

```text
http://localhost:8081/graphql
```

También puede habilitarse GraphiQL desde la configuración del proyecto.

### 4.2 Ejecutar el cliente

Abre la solución:

```text
ClienteLibroFisico/ClienteLibroFisico.sln
```

Compila y ejecuta la aplicación desde Visual Studio.

> Asegúrate de que el servidor esté levantado antes de usar las operaciones del cliente.

---

## 5. Tecnologías usadas

### Cliente

- C#
- Windows Forms
- .NET Framework 4.8
- GraphQL.Client
- System.Text.Json

### Servidor

- Java 17
- Spring Boot 4
- Spring GraphQL
- Maven
- Lombok

---

## 6. Funcionalidades principales

- Registrar libros con información completa
- Validar ISBN, título, autor, precio y fechas
- Consultar libros por ISBN
- Actualizar información de un libro existente
- Eliminar libros con confirmación
- Listar todos los libros
- Filtrar libros por autor o tipo de tapa

---

## 7. Nota importante

El cliente está diseñado para consumir el microservicio en localhost. Si el servidor cambia de puerto o dirección, debe ajustarse en:

```text
ClienteLibroFisico/ClienteLibroFisico/Servicios/LibroFisicoService.cs
```

---

## 8. Autoría

Proyecto académico desarrollado para la asignatura de Diseño de Soluciones.

Integrantes:

- Sara Zambrano Ortiz
- Alejandra González Cortes
- Misael Gallo Tangarife
- Santiago Guimel Bahena

---

## 9. Licencia

Este proyecto es de uso académico y está destinado a fines educativos dentro de la universidad.
