package com.ccettour.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha());
    }
}
