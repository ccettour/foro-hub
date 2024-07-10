package com.ccettour.foro_hub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    String mensaje;
    LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    Status status;
    Long idUsuario;
    String nombreCurso;


    public Topico(DatosCrearTopico datos) {
        this.titulo=datos.titulo();
        this.mensaje=datos.mensaje();
        this.fecha=LocalDateTime.now();
        this.status=Status.ABIERTO;
        this.idUsuario=datos.idUsuario();
        this.nombreCurso=datos.nombreCurso();
    }

    public void actualizar(DatosActualizarTopico datos) {
        if(datos.titulo()!=null){
            this.titulo=datos.titulo();
        }
        if(datos.mensaje()!=null){
            this.mensaje=datos.mensaje();
        }
        if(datos.nombreCurso()!=null){
            this.nombreCurso=datos.nombreCurso();
        }
        if(datos.status()!=null){
            this.status=datos.status();
        }
    }
}