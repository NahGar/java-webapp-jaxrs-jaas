package org.ngarcia.webapp.jaxws.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.ngarcia.webapp.jaxws.models.Curso;

import java.util.List;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        String sql = "select c from Curso c left outer join fetch c.instructor";
        return em.createQuery(sql,Curso.class).getResultList();
    }

    @Override
    public Curso guardar(Curso curso) {
        if (curso.getId() != null && curso.getId() > 0) {
            em.merge(curso);
        }
        else {
            em.persist(curso);
        }
        return curso;
    }

    @Override
    public Curso porId(Long id) {
        //return em.find(Curso.class,id);
        String sql = "select c from Curso c left outer join fetch c.instructor where c.id=:id";
        return em.createQuery(sql,Curso.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public void eliminar(Long id) {
        Curso c = porId(id);
        em.remove(c);
    }
}
