package com.libreria.servidor.model;

import java.time.LocalDateTime;

public class LibroFisico extends Libro {


    public static final double RECARGO_TAPA_DURA = 0.10;

    private int numeroPaginas;
    private LocalDateTime fechaImpresion;
    private TipoTapa tipoTapa;

    public LibroFisico() {
    }

    public LibroFisico(String isbn, String titulo, String autor, double precio,
                       int numeroPaginas, LocalDateTime fechaImpresion, TipoTapa tipoTapa) {
        super(isbn, titulo, autor, precio);
        this.numeroPaginas = numeroPaginas;
        this.fechaImpresion = fechaImpresion;
        this.tipoTapa = tipoTapa;
    }


    @Override
    public double totalPagar() {
        double total = getPrecio();
        if (tipoTapa == TipoTapa.DURA) {
            total += getPrecio() * RECARGO_TAPA_DURA;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public LocalDateTime getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(LocalDateTime fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public TipoTapa getTipoTapa() {
        return tipoTapa;
    }

    public void setTipoTapa(TipoTapa tipoTapa) {
        this.tipoTapa = tipoTapa;
    }

    @Override
    public String toString() {
        return "LibroFisico{isbn='" + getIsbn() + "', titulo='" + getTitulo() + "', autor='" + getAutor()
                + "', precio=" + getPrecio() + ", numeroPaginas=" + numeroPaginas
                + ", fechaImpresion=" + fechaImpresion + ", tipoTapa=" + tipoTapa + "}";
    }
}