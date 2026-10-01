package com.libreria.servidor.servicios;

import com.libreria.servidor.excepciones.LibroException;
import com.libreria.servidor.model.LibroFisico;
import com.libreria.servidor.model.LibroFisicoInput;
import com.libreria.servidor.model.LibroFisicoUpdateInput;
import com.libreria.servidor.model.TipoTapa;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lógica de negocio del CRUD de LibroFisico.
 * La información se guarda en memoria principal en una lista (ArrayList).
 * Los métodos son synchronized porque varios clientes pueden hacer peticiones al mismo tiempo.
 */
@Service
public class ServicioLibroFisico {

    private final List<LibroFisico> libros = new ArrayList<>();

    public ServicioLibroFisico() {
        // Datos de prueba
        libros.add(new LibroFisico("978-0307474728", "Cien años de soledad", "Gabriel García Márquez",
                65000, 496, LocalDateTime.of(2017, 3, 15, 9, 30), TipoTapa.BLANDA));
        libros.add(new LibroFisico("978-8497592208", "El amor en los tiempos del cólera", "Gabriel García Márquez",
                72000, 464, LocalDateTime.of(2015, 8, 1, 14, 0), TipoTapa.DURA));
        libros.add(new LibroFisico("978-8420471839", "La vorágine", "José Eustasio Rivera",
                48000, 384, LocalDateTime.of(2019, 11, 20, 10, 15), TipoTapa.BLANDA));
        libros.add(new LibroFisico("978-9584276193", "Delirio", "Laura Restrepo",
                55000, 352, LocalDateTime.of(2020, 5, 5, 8, 45), TipoTapa.DURA));
    }

    // ---------- Listar ----------

    /** Devuelve todos los libros físicos. */
    public synchronized List<LibroFisico> listar() {
        return new ArrayList<>(libros);
    }


    public synchronized List<LibroFisico> filtrar(String autor, TipoTapa tipoTapa) {
        String autorBuscado = normalizar(autor);
        return libros.stream()
                .filter(l -> autorBuscado.isEmpty() || normalizar(l.getAutor()).contains(autorBuscado))
                .filter(l -> tipoTapa == null || l.getTipoTapa() == tipoTapa)
                .toList();
    }

    /** Pasa a minúsculas y quita tildes: "García" -> "garcia". */
    private static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        return Normalizer.normalize(texto.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }


    public synchronized Optional<LibroFisico> buscarPorIsbn(String isbn) {
        if (isbn == null) {
            return Optional.empty();
        }
        String buscado = isbn.trim();
        return libros.stream()
                .filter(l -> l.getIsbn().equalsIgnoreCase(buscado))
                .findFirst();
    }



    public synchronized LibroFisico crear(LibroFisicoInput in) {
        if (in.isbn() == null || in.isbn().isBlank()) {
            throw new LibroException("El ISBN es obligatorio.");
        }
        if (buscarPorIsbn(in.isbn()).isPresent()) {
            throw new LibroException("Ya existe un libro físico con el ISBN " + in.isbn().trim() + ".");
        }
        validar(in.titulo(), in.autor(), in.precio(), in.numeroPaginas(), in.fechaImpresion(), in.tipoTapa());

        LibroFisico libro = new LibroFisico(in.isbn().trim(), in.titulo().trim(), in.autor().trim(),
                in.precio(), in.numeroPaginas(), in.fechaImpresion(), in.tipoTapa());
        libros.add(libro);
        return libro;
    }



    public synchronized LibroFisico actualizar(String isbn, LibroFisicoUpdateInput in) {
        LibroFisico libro = buscarPorIsbn(isbn)
                .orElseThrow(() -> new LibroException("No existe un libro físico con el ISBN " + isbn + "."));
        validar(in.titulo(), in.autor(), in.precio(), in.numeroPaginas(), in.fechaImpresion(), in.tipoTapa());

        libro.setTitulo(in.titulo().trim());
        libro.setAutor(in.autor().trim());
        libro.setPrecio(in.precio());
        libro.setNumeroPaginas(in.numeroPaginas());
        libro.setFechaImpresion(in.fechaImpresion());
        libro.setTipoTapa(in.tipoTapa());
        return libro;
    }


    /** Elimina el libro y lo devuelve, para que el cliente confirme qué se borró. */
    public synchronized LibroFisico eliminar(String isbn) {
        LibroFisico libro = buscarPorIsbn(isbn)
                .orElseThrow(() -> new LibroException("No existe un libro físico con el ISBN " + isbn + "."));
        libros.remove(libro);
        return libro;
    }


    private void validar(String titulo, String autor, double precio, int paginas,
                         LocalDateTime fecha, TipoTapa tipoTapa) {
        if (titulo == null || titulo.isBlank()) {
            throw new LibroException("El título es obligatorio.");
        }
        if (autor == null || autor.isBlank()) {
            throw new LibroException("El autor es obligatorio.");
        }
        if (precio <= 0) {
            throw new LibroException("El precio debe ser mayor que cero.");
        }
        if (paginas <= 0) {
            throw new LibroException("El número de páginas debe ser mayor que cero.");
        }
        if (fecha == null) {
            throw new LibroException("La fecha de impresión es obligatoria.");
        }
        if (fecha.isAfter(LocalDateTime.now())) {
            throw new LibroException("La fecha de impresión no puede ser futura.");
        }
        if (tipoTapa == null) {
            throw new LibroException("El tipo de tapa es obligatorio.");
        }
    }
}
