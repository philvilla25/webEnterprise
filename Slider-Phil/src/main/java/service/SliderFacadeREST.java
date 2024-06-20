/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import cst8218.vill0419.slider.phil.entity.Slider;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Phil
 */
@Stateless
@Path("cst8218.vill0419.slider.phil.entity.slider")
public class SliderFacadeREST extends AbstractFacade<Slider> {
    @PersistenceContext(unitName = "my_persistence_slider")
    private EntityManager em;
    private static Map<Integer, Slider> sliderStore = new HashMap<>();
    
    @Override
    protected EntityManager getEntityManager() {
    return em;
    }
    
    public SliderFacadeREST() {
        super(Slider.class);
    }

   @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPost(Slider entity, @Context UriInfo uriInfo) {
        super.create(entity);
        
        URI location = URI.create(uriInfo.getRequestUri().getPath() + "/" + entity.getId());
        return Response.status(Response.Status.CREATED).location(location).entity(entity).build();
    }

//    @POST
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response updateSlider(@PathParam("id") Long id, Slider entity) {
//        if (entity.getId() != null && !entity.getId().equals(id)) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("ID in the body does not match ID in the URL").build();
//        }
//
//        Slider existingSlider = super.find(id);
//        if (existingSlider == null) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Slider with ID " + id + " does not exist").build();
//        }
//
//        entity.setId(id); // Ensure the ID in the entity is set correctly
//        existingSlider.update(entity);
//        super.edit(existingSlider);
//
//        return Response.ok(existingSlider).build();
//    }

    
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Slider entity) {
        Slider existingSlider = super.find(id);
        
        if (existingSlider == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Slider with ID " + id + " not found").build();
        }

        entity.setId(id);  // Ensure the ID in the entity is correct
        super.edit(entity);
        
        return Response.ok(entity).build();
    }
       
  
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        Slider slider = super.find(id);
        
        if (slider == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Slider with ID " + id + " not found").build();//404 return not 
        }
        
        return Response.ok(slider).build(); //200 ok
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findAll() {     
           return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {  
        //String count = String.valueOf(super.count());
        int count = super.count();
        
        if(count == 0){
        return Response.status(Response.Status.BAD_REQUEST).entity("No slider found").build();
        }
        return Response.ok(count).build();
    } 

//   @POST
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response updateSlider(@PathParam("id") Long id, Slider entity) {
//        if (entity.getId() != null && !entity.getId().equals(id)) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("ID in the body does not match ID in the URL").build();
//        }
//
//        Slider existingSlider = super.find(id);
//        if (existingSlider == null) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Slider with ID " + id + " does not exist").build();
//        }
//
//        entity.setId(id); // Ensure the ID in the entity is set correctly
//        existingSlider.update(entity);
//        super.edit(existingSlider);
//
//        return Response.ok(existingSlider).build();
//    }
}
