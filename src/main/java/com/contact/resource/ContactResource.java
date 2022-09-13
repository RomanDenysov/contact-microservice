package com.contact.resource;

import com.contact.model.dto.ContactDTO;
import com.contact.service.ContactService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("contacts")
@Produces("application/json")
@Consumes("application/json")
public class ContactResource {
    @Inject
    ContactService contactService;

    @GET
    @Path("{contactId}")
    public ContactDTO getSingleContact(@PathParam("contactId") Long contactId) {
        return contactService.getContactById(contactId);
    }

    @POST
    public Response createContact(Long userId) {
        contactService.createContact(userId);
        ContactDTO contact = contactService.getCreatedContact(userId);
        return Response.ok(contact).status(201).build();
    }

    @PUT
    @Path("{contactId}")
    public Response updateContact(@PathParam("contactId") Long contactId,
                                      ContactDTO contact) {
        contactService.updateContact(contactId, contact);
        return Response.ok().build();
    }

    @DELETE
    @Path("{contactId}")
    public Response deleteContact(@PathParam("contactId") Long contactId) {
        contactService.deleteContact(contactId);
        return Response.noContent().build();
    }
}
