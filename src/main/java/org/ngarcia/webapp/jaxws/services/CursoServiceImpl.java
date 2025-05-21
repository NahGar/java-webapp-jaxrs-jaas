package org.ngarcia.webapp.jaxws.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.ngarcia.webapp.jaxws.models.Curso;
import org.ngarcia.webapp.jaxws.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"ADMIN","USER"})
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoRepository repository;

    @Override
    @RolesAllowed({"ADMIN","USER"})
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Curso guardar(Curso curso) {
        System.out.println("CURSO GUARDAR " + curso.getNombre());
        return repository.guardar(curso);
    }

    @Override
    @RolesAllowed({"ADMIN","USER"})
    public Optional<Curso> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
