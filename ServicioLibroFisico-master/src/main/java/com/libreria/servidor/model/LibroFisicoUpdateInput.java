package com.libreria.servidor.model;

import java.time.LocalDateTime;


public record LibroFisicoUpdateInput(String titulo,
                                     String autor,
                                     double precio,
                                     int numeroPaginas,
                                     LocalDateTime fechaImpresion,
                                     TipoTapa tipoTapa) {
}
