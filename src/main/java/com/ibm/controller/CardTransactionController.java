package com.ibm.controller;

import java.sql.Timestamp;

import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import javax.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;

import com.ibm.model.CardTransaction;
import com.ibm.service.CardTransactionService;

@ApplicationScoped
@Path("/api/card-transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardTransactionController {
    
    @Inject
    CardTransactionService cardTransactionService;

    private static final String CARD_TRANSACTION_NOT_FOUND = "CardTransaction not found";
    private static final String NOT_SET_ON_REQUEST = "Not set on request";
 
    @GET
    public Response listCardTransactions() {
        return Response.status(Status.OK).entity(cardTransactionService.getAllCardTransactions()).build();
    }

    @GET
    @Path("{id}")
    public Response getCardTransaction(@PathParam("id") Long id) {
        CardTransaction cardTransaction = cardTransactionService.getCardTransactionById(id);
        if (cardTransaction == null) {
            return Response.status(Status.NOT_FOUND).entity(CARD_TRANSACTION_NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(cardTransaction).build();
    }

    @POST
    @Transactional
    public Response createCardTransaction(CardTransaction cardTransaction) {
        if(cardTransaction.getid() != null) {
            return Response.status(Status.BAD_REQUEST).entity("Id not required").build();
        }
        cardTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        cardTransactionService.createCardTransaction(cardTransaction);
        return Response.status(Status.CREATED).entity(cardTransaction).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateCardTransaction(@PathParam("id") Long id, CardTransaction cardTransaction) {
        try {
            cardTransactionService.updateCardTransaction(id, cardTransaction);
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND).entity(CARD_TRANSACTION_NOT_FOUND).build();
        } catch (IllegalStateException e) {
            return Response.status(Status.BAD_REQUEST).entity(NOT_SET_ON_REQUEST).build();
        }
        return Response.status(Status.OK).entity(cardTransaction).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletecardTransaction(@PathParam("id") Long id) {
        try {
            cardTransactionService.deleteCardTransaction(id);
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND).entity(CARD_TRANSACTION_NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
