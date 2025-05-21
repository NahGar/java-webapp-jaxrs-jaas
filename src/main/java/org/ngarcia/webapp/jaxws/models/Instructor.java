package org.ngarcia.webapp.jaxws.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructores")
public class Instructor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //@JsonIgnoreProperties({"instructor"}) //para evitar recursividad ignora el atributo
  //en ocasiones aparecen errores relacionados a handler y hibernateLazyInitializer
  @JsonIgnoreProperties({"instructor","handler","hibernateLazyInitializer"}) //para evitar recursividad ignora el atributo
  @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
  private List<Curso> cursos;

  private String nombre;
  private String apellido;

  public Instructor() {
    this.cursos = new ArrayList<>();
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

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public List<Curso> getCursos() {
    return cursos;
  }

  public void setCursos(List<Curso> cursos) {
    this.cursos = cursos;
  }
}
