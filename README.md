# Proyecto Librería: Cliente + Servicio GraphQL

Este repositorio contiene dos proyectos que trabajan juntos para gestionar libros físicos:

- Cliente de escritorio en C# y Windows Forms: `ClienteLibroFisico`
- Microservicio backend en Java con Spring Boot y GraphQL: `ServicioLibroFisico-master`

El cliente no guarda datos localmente; toda la información se maneja a través del backend en `http://localhost:8081/graphql`.

---

## 1. Objetivo del proyecto

La aplicación permite:

- Insertar libros físicos
- Consultar un libro por ISBN
- Actualizar información del libro
- Eliminar un libro
- Listar todos los libros
- Filtrar por autor y por tipo de tapa

---

## 2. Requisitos previos

### Para el backend (Java / Spring Boot)

- Java 17
- Maven (o usar el wrapper incluido `mvnw` / `mvnw.cmd`)
- Internet para descargar dependencias la primera vez

### Para el cliente (C# / Windows Forms)

- Windows 10 o 11
- .NET Framework 4.8
- Visual Studio 2022 Community o Build Tools

> Si no se tiene Visual Studio, se puede compilar con MSBuild desde la terminal, pero lo más recomendado es abrir la solución con Visual Studio.

---

## 3. Estructura del repositorio

```text
Proyecto/
├── README.md
├── ClienteLibroFisico/
│   ├── ClienteLibroFisico.sln
│   ├── ClienteLibroFisico/
│   │   ├── Modelo/
│   │   ├── Servicios/
│   │   ├── Vistas/
│   │   ├── Program.cs
│   │   ├── App.config
│   │   └── ...
│   └── README.md
│
└── ServicioLibroFisico-master/
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    ├── src/
    └── target/
```

---

## 4. Paso a paso para ejecutar el proyecto

## 4.1 Iniciar el backend

1. Abrir una terminal en la carpeta `ServicioLibroFisico-master`.
2. Ejecutar:

### En Linux / macOS

```bash
./mvnw clean package
./mvnw spring-boot:run
```

### En Windows PowerShell

```powershell
cd .\ServicioLibroFisico-master
mvnw.cmd clean package
mvnw.cmd spring-boot:run
```

3. Esperar a que Spring Boot termine de iniciar.
4. Verificar que el servicio esté activo en:

```text
http://localhost:8081/graphql
```

5. También queda habilitado GraphiQL en la misma URL, si el navegador lo permite.

> Si no aparece ninguna respuesta, revisar que Java 17 esté instalado y que el puerto 8081 no esté ocupado.

---

## 4.2 Verificar que el servicio responde

Desde el navegador o una herramienta como Postman, puede probar esta URL:

```text
http://localhost:8081/graphql
```

Si el backend está bien levantado, la API GraphQL estará lista para recibir consultas y mutaciones.

---

## 4.3 Ejecutar el cliente Windows Forms

1. Abrir la solución:

```text
ClienteLibroFisico/ClienteLibroFisico.sln
```

2. Cargar el proyecto en Visual Studio 2022.
3. Esperar a que restaure los paquetes NuGet.
4. Presionar `F5` para ejecutar la aplicación.

### Si se quiere compilar desde consola

```powershell
$msbuild = & "${env:ProgramFiles(x86)}\Microsoft Visual Studio\Installer\vswhere.exe" -latest -products * -find MSBuild\**\Bin\MSBuild.exe
& $msbuild "ClienteLibroFisico\ClienteLibroFisico.sln" -t:restore -p:RestorePackagesConfig=true
& $msbuild "ClienteLibroFisico\ClienteLibroFisico.sln"
```

Luego ejecutar:

```powershell
\ClienteLibroFisico\ClienteLibroFisico\bin\Debug\ClienteLibroFisico.exe
```

---

## 5. Importante: orden correcto de ejecución

Para que la aplicación funcione correctamente, siempre se debe ejecutar en este orden:

1. Levantar el backend Java
2. Esperar a que esté en `http://localhost:8081/graphql`
3. Abrir el cliente C#
4. Usar las funcionalidades del menú

Si el backend no está activo, el cliente mostrará que no puede conectarse al servidor.

---

## 6. Configuración del servidor en el cliente

La URL del backend está definida en:

```text
ClienteLibroFisico/ClienteLibroFisico/Servicios/LibroFisicoService.cs
```

Constante:

```csharp
public const string URL_SERVIDOR = "http://localhost:8081/graphql";
```

Si cambias el puerto o la dirección del backend, debes actualizar esa constante en el cliente.

---

## 7. Funcionalidades disponibles

El cliente ofrece estas operaciones:

- Insertar libro
- Consultar libro por ISBN
- Actualizar un libro
- Eliminar un libro
- Listar todos los libros
- Filtrar por autor y/o tipo de tapa

---

## 8. Tecnologías usadas

### Backend

- Java 17
- Spring Boot 4.1.1
- Spring GraphQL
- Maven
- Lombok

### Frontend

- C#
- .NET Framework 4.8
- Windows Forms
- GraphQL.Client
- System.Text.Json

---

## 9. Solución de problemas comunes

### Error: no se puede conectar al servidor

- Verifica que el backend esté ejecutándose
- Revisa la URL `http://localhost:8081/graphql`
- Confirma que el puerto 8081 no está ocupado por otra aplicación

### Error: Java no encontrado

Instala Java 17 y verifica con:

```powershell
java -version
```

### Error: Visual Studio no restaura paquetes

Reinicia Visual Studio y vuelve a abrir la solución, o ejecuta la restauración desde la opción de NuGet.

### Error: no compila por .NET Framework

Instala el .NET Framework 4.8 o usa una máquina con Windows con el SDK de Visual Studio configurado correctamente.

---

## 10. Creditos

Proyecto académico desarrollado para la asignatura de Diseño de Soluciones.

Integrantes:

- Sara Zambrano Ortiz
- Alejandra González Cortes
- Misael Gallo Tangarife
- Santiago Guimel Bahena

---

## 11. Nota final

Para que el proyecto funcione correctamente, hay que levantar primero el backend y luego abrir el cliente. Si se sigue ese orden, la aplicación puede ejecutarse de forma estable y conectarse al servicio GraphQL sin problemas.
