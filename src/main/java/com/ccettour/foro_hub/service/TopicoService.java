package com.ccettour.foro_hub.service;

import com.ccettour.foro_hub.domain.topico.DatosActualizarTopico;
import com.ccettour.foro_hub.domain.topico.DatosCrearTopico;
import com.ccettour.foro_hub.domain.topico.Topico;
import com.ccettour.foro_hub.domain.topico.TopicoRepository;
import com.ccettour.foro_hub.domain.topico.validaciones.ValidadorDeTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    List<ValidadorDeTopico> validadores;
    @Autowired
    private TopicoRepository repository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.repository = topicoRepository;
    }

    public Topico crear(DatosCrearTopico datos){
        validadores.forEach(v->v.validar(datos));

        Topico topico = repository.save(new Topico(datos));

        return topico;
    }

    public Topico actualizar(Topico topico, DatosActualizarTopico datos){
        var titulo = topico.getTitulo();
        var mensaje= topico.getMensaje();

        if(datos.titulo()!=null){
            titulo=datos.titulo();
        }
        if(datos.mensaje()!=null){
            mensaje=datos.mensaje();
        }

        if(repository.findByTituloAndMensaje(titulo, mensaje)!=null){
            throw new ValidationException("Ya existe un tópico con esos datos.");
        }

        topico.actualizar(datos);

        return topico;
    }
}
