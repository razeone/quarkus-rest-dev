package com.ibm.controller;

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

import java.util.HashMap;
import java.util.Map;

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

    private static final String CARD_TRANSACTION_NOT_FOUND = " CardTransaction not found";
    private HashMap<String, String> error = new HashMap<>();
 
    @GET
    public Response listCardTransactions() {
        return Response.status(Status.OK).entity(cardTransactionService.getAllCardTransactions()).build();
    }

    @GET
    @Path("{id}")
    public Response getCardTransaction(@PathParam("id") Long id) {
        CardTransaction cardTransaction = cardTransactionService.getCardTransactionById(id);
        if (cardTransaction == null) {
            setError(id.toString() + CARD_TRANSACTION_NOT_FOUND);
            return Response.status(Status.NOT_FOUND).entity(getError()).build();
        }
        return Response.status(Status.OK).entity(cardTransaction).build();
    }

    @POST
    @Transactional
    public Response createCardTransaction(CardTransaction cardTransaction) {
        try{
            cardTransactionService.createCardTransaction(cardTransaction);
        } catch(IllegalArgumentException e) {
            setError(e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        } catch(Exception e) {
            setError(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
        return Response.status(Status.CREATED).entity(cardTransaction).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateCardTransaction(@PathParam("id") Long id, CardTransaction cardTransaction) {
        try {
            cardTransactionService.updateCardTransaction(id, cardTransaction);
            return Response.status(Status.OK).entity(cardTransaction).build();
        } catch (IllegalArgumentException e) {
            setError(id.toString() + CARD_TRANSACTION_NOT_FOUND);
            return Response.status(Status.NOT_FOUND).entity(getError()).build();
        } catch (IllegalStateException e) {
            setError(e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(getError()).build();
        } catch(Exception e) {
            setError(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCardTransaction(@PathParam("id") Long id) {
        try {
            cardTransactionService.deleteCardTransaction(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            setError(id.toString() + CARD_TRANSACTION_NOT_FOUND);
            return Response.status(Status.NOT_FOUND).entity(getError()).build();
        } catch(Exception e) {
            setError(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError()).build();
        }
    }

    public void setError(String error) {
        this.error.put("error", error);
    }

    public Map<String, String> getError() {
        return error;
    }

}
