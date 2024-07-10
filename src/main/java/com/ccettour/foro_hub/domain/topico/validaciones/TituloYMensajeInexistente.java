package com.ccettour.foro_hub.domain.topico.validaciones;

import com.ccettour.foro_hub.domain.topico.DatosCrearTopico;
import com.ccettour.foro_hub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TituloYMensajeInexistente implements ValidadorDeTopico{
    @Autowired
    TopicoRepository repository;

    @Override
    public void validar(DatosCrearTopico datos) {
        if(repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje())!=null){
            throw new ValidationException("Ya existe un tópico con esos datos.");
        }
    }
}
