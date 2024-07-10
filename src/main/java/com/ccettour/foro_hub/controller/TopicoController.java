package com.ccettour.foro_hub.controller;

import com.ccettour.foro_hub.domain.topico.*;
import com.ccettour.foro_hub.service.TopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity crear(@RequestBody @Valid DatosCrearTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = service.crear(datos);

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosDetalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topico> topicos = repository.findAll(pageable);
        Page<DatosListadoTopico> datosListadoTopicos = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(datosListadoTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> mostrarDatosTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
        Topico topico = repository.getReferenceById(id);
        Topico actualizado = service.actualizar(topico, datos);
        return ResponseEntity.ok(new DatosListadoTopico(actualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topico = repository.findById(id);
        if(topico.isPresent()){
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El topico no existe");
        }
        return ResponseEntity.noContent().build();
    }

}
