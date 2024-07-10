package com.ccettour.foro_hub.domain.topico;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        Long id,
        @NotNull(message = "El título es obligatorio")
        @NotEmpty
        String titulo,
        @NotNull(message = "El mensaje es obligatorio")
        @NotEmpty
        String mensaje,
        @NotNull(message = "El id de usuario es obligatorio")
        Long idUsuario,
        Status status,
        @NotNull(message = "El nombre del curso es obligatorio")
        @NotEmpty
        String nombreCurso) {
}
