package com.libreria.servidor.controller;

import com.libreria.servidor.model.LibroFisico;
import com.libreria.servidor.model.LibroFisicoInput;
import com.libreria.servidor.model.LibroFisicoUpdateInput;
import com.libreria.servidor.model.TipoTapa;
import com.libreria.servidor.servicios.ServicioLibroFisico;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class LibroFisicoController {

    private final ServicioLibroFisico servicio;

    public LibroFisicoController(ServicioLibroFisico servicio) {
        this.servicio = servicio;
    }

    // ---------- Queries ----------

    @QueryMapping
    public List<LibroFisico> librosFisicos() {
        return servicio.listar();
    }

    @QueryMapping
    public List<LibroFisico> librosFisicosFiltrados(@Argument String autor, @Argument TipoTapa tipoTapa) {
        return servicio.filtrar(autor, tipoTapa);
    }

    @QueryMapping
    public Optional<LibroFisico> libroFisicoPorIsbn(@Argument String isbn) {
        return servicio.buscarPorIsbn(isbn);
    }

    // ---------- Mutations ----------

    @MutationMapping
    public LibroFisico crearLibroFisico(@Argument("input") LibroFisicoInput input) {
        return servicio.crear(input);
    }

    @MutationMapping
    public LibroFisico actualizarLibroFisico(@Argument String isbn,
                                             @Argument("input") LibroFisicoUpdateInput input) {
        return servicio.actualizar(isbn, input);
    }

    @MutationMapping
    public LibroFisico eliminarLibroFisico(@Argument String isbn) {
        return servicio.eliminar(isbn);
    }


    @SchemaMapping(typeName = "LibroFisico", field = "totalPagar")
    public double totalPagar(LibroFisico libro) {
        return libro.totalPagar();
    }
}
