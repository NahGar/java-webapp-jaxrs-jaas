package org.ngarcia.webapp.jaxws.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ngarcia.webapp.jaxws.models.Curso;
import org.ngarcia.webapp.jaxws.services.CursoService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/cursos")
//@Produces(MediaType.APPLICATION_XML) //jakarta.ws.rs
@Produces(MediaType.APPLICATION_JSON) //jakarta.ws.rs
public class CursoRestController {

    @Inject
    private CursoService service;

    @GET
    public List<Curso> listar() {
        return service.listar();
    }
    //@GET
    //public Response listar() {
    //    return Response.ok(service.listar()).build();
    //}

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Curso> opt = service.porId(id);
        if(opt.isPresent()) {
            return Response.ok(opt.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //curso va en el body y automáticamente lo carga
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Curso curso) {
        try {
            return Response.ok(service.guardar(curso)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, Curso curso) {
        try {
            Optional<Curso> opt = service.porId(id);
            if(opt.isPresent()) {
                Curso nuevoCurso = opt.get();
                nuevoCurso.setNombre(curso.getNombre());
                nuevoCurso.setDescripcion(curso.getDescripcion());
                nuevoCurso.setInstructor(curso.getInstructor());
                nuevoCurso.setDuracion(curso.getDuracion());
                return Response.ok(service.guardar(nuevoCurso)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        try {
            Optional<Curso> opt = service.porId(id);
            if(opt.isPresent()) {
                service.eliminar(id);
                //return Response.noContent().entity("Curso eliminado").build(); //204
                return Response.accepted("Curso eliminado").build(); //204
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
