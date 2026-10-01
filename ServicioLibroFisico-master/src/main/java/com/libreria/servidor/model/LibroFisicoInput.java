package com.libreria.servidor.model;

import java.time.LocalDateTime;


public record LibroFisicoInput(String isbn,
                               String titulo,
                               String autor,
                               double precio,
                               int numeroPaginas,
                               LocalDateTime fechaImpresion,
                               TipoTapa tipoTapa) {
}
