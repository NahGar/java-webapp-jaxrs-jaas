package org.ngarcia.webapp.jaxws.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

//@XmlRootElement //para que sepa convertir a XML (no necesario en REST)
@Entity
@Table(name="cursos")
public class Curso {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    //private String instructor;

    //@JsonbTransient //para que no incluya instructor
    //@JsonIgnore //para que no incluya instructor (requiere resteasy)
    //en ocasiones aparecen errores relacionados a handler y hibernateLazyInitializer
    //@JsonIgnoreProperties({"cursos"}) //para evitar recursividad ignora el atributo
    @JsonIgnoreProperties({"cursos","handler","hibernateLazyInitializer"}) //para evitar recursividad ignora el atributo
    @ManyToOne( fetch = FetchType.LAZY)
    private Instructor instructor;

    private Double duracion;

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
}
