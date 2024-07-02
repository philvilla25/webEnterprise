/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.vill0419.slider.phil.service;

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
import java.util.List;

/*
 * Description: REST API
 * Student Name: Philogene Villanueva
 * Due Date: 2024-06-20
 * Program/Course/Section:  24S_CST8218 
 * 
 */
@Stateless
@Path("cst8218.vill0419.slider.phil.entity.slider")
public class SliderFacadeREST extends AbstractFacade<Slider> {

    @PersistenceContext(unitName = "my_persistence_slider")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SliderFacadeREST() {
        super(Slider.class);
    }

    /**
     * Creates a new Slider and returns a response with the created entity.
     *
     * @param entity the Slider object to be created
     * @param uriInfo context information about the request URI
     * @return Response containing the created Slider and the location URI
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createPost(Slider entity, @Context UriInfo uriInfo) {
        // Create the new Slider entity using the superclass method
        super.create(entity);

        // Construct the URI for the newly created Slider
        URI location = URI.create(uriInfo.getRequestUri().getPath() + "/" + entity.getId());

        // Return a response with status 201 Created, the location URI, and the created entity
        return Response.status(Response.Status.CREATED).location(location).entity(entity).build();
    }

    /**
     * Updates the Slider with the specified ID with new values from the request
     * body.
     *
     * @param id the ID of the Slider to be updated
     * @param entity the Slider object containing new values
     * @return Response containing the updated Slider or an error message
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateSlider(@PathParam("id") Long id, Slider entity) {
        // Check if the ID in the body does not match the ID in the URL
        if (entity.getId() != null && !entity.getId().equals(id)) {
            // Return a 400 Bad Request response with an error message
            return Response.status(Response.Status.BAD_REQUEST).entity("ID in the body does not match ID in the URL").build();
        }

        // Find the existing Slider with the specified ID
        Slider existingSlider = super.find(id);
        if (existingSlider == null) {
            // Return a 400 Bad Request response if the Slider does not exist
            return Response.status(Response.Status.BAD_REQUEST).entity("Slider with ID " + id + " does not exist").build();
        }

        // Ensure the ID in the entity is set correctly to the ID from the URL
        entity.setId(id);

        // Update the existing Slider with the new values from the entity
        existingSlider.update(entity);

        // Edit the updated Slider using the superclass method
        super.edit(existingSlider);

        // Return a 200 OK response with the updated Slider entity
        return Response.ok(existingSlider).build();
    }

    /**
     * Updates the Slider with the specified ID with new values from the request
     * body.
     *
     * @param id the ID of the Slider to be updated
     * @param entity the Slider object containing new values
     * @return Response containing the updated Slider or a not found error
     * message
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Slider entity) {
        // Find the existing Slider with the specified ID
        Slider existingSlider = super.find(id);

        // Check if the existing Slider was found
        if (existingSlider == null) {
            // Return a 404 Not Found response if the Slider does not exist
            return Response.status(Response.Status.NOT_FOUND).entity("Slider with ID " + id + " not found").build();
        }

        // Ensure the ID in the entity is set correctly to the ID from the URL
        entity.setId(id);

        // Update the existing Slider with the new values from the entity
        super.edit(entity);

        // Return a 200 OK response with the updated Slider entity
        return Response.ok(entity).build();
    }

    /**
     * Removes the Slider with the specified ID.
     *
     * @param id the ID of the Slider to be removed
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        // Find the existing Slider with the specified ID and remove it using the superclass method
        super.remove(super.find(id));
    }

    /**
     * Retrieves the Slider with the specified ID.
     *
     * @param id the ID of the Slider to be retrieved
     * @return Response containing the found Slider or a not found error message
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        // Find the existing Slider with the specified ID
        Slider slider = super.find(id);

        // Check if the existing Slider was found
        if (slider == null) {
            // Return a 404 Not Found response if the Slider does not exist
            return Response.status(Response.Status.NOT_FOUND).entity("Slider with ID " + id + " not found").build();
        }

        // Return a 200 OK response with the found Slider entity
        return Response.ok(slider).build();
    }

    /**
     * Retrieves all Sliders.
     *
     * @return List of all Sliders
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findAll() {
        // Return all Sliders using the superclass method
        return super.findAll();
    }

    /**
     * Retrieves a range of Sliders.
     *
     * @param from the starting index of the range
     * @param to the ending index of the range
     * @return List of Sliders within the specified range
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        // Return the range of Sliders using the superclass method
        return super.findRange(new int[]{from, to});
    }

    /**
     * Retrieves the total count of Sliders.
     *
     * @return Response containing the count of Sliders or a bad request error
     * message
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        // Get the count of all Sliders using the superclass method
        int count = super.count();

        // Check if the count is zero
        if (count == 0) {
            // Return a 400 Bad Request response if no Sliders are found
            return Response.status(Response.Status.BAD_REQUEST).entity("No slider found").build();
        }

        // Return a 200 OK response with the count of Sliders
        return Response.ok(count).build();
    }

}
