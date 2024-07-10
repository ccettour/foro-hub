package com.ccettour.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    <Optional> Topico findByTituloAndMensaje(String titulo, String mensaje);

}
