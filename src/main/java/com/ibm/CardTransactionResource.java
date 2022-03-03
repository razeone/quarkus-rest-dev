package com.ibm;

import java.util.List;
import java.sql.Timestamp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.WebApplicationException;

import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.model.CardTransaction;
import com.ibm.repository.CardTransactionRepository;

import io.quarkus.panache.common.Sort;

@Path("/card-transaction")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardTransactionResource {

    @Inject
    CardTransactionRepository cardTransactionRepository;

    @GET
    public List<CardTransaction> get() {
        return cardTransactionRepository.listAll(Sort.by("timestamp"));
    }

    @GET
    @Path("{id}")
    public CardTransaction get(@PathParam("id") Long id) {
        CardTransaction cardTransaction = cardTransactionRepository.findById(id);
        if (cardTransaction == null) {
            throw new WebApplicationException("CardTransaction not found", Response.Status.NOT_FOUND);
        }
        return cardTransactionRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(CardTransaction cardTransaction) {
        if(cardTransaction.getid() != null) {
            throw new WebApplicationException("Id must be null", 422);
        }
        cardTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        cardTransactionRepository.persist(cardTransaction);
        return Response.ok(cardTransaction).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public CardTransaction update(@PathParam("id") Long id, CardTransaction cardTransaction) {
        if(cardTransaction.getCommerceName() == null)  {
            throw new WebApplicationException("Commerce name was not set on request", 422);
        }

        if(cardTransaction.getAmount() == null)  {
            throw new WebApplicationException("Ammount was not set on request", 422);
        }

        if(cardTransaction.getCardNumber() == null)  {
            throw new WebApplicationException("Card Number was not set on request", 422);
        }

        if(cardTransaction.getCustomerId() == null)  {
            throw new WebApplicationException("Customer Id was not set on request", 422);
        }

        if(cardTransaction.getAccountId() == null)  {
            throw new WebApplicationException("Account Id was not set on request", 422);
        }

        if(cardTransaction.getStatus() == null)  {
            throw new WebApplicationException("Status was not set on request", 422);
        }

        if(cardTransaction.getType() == null)  {
            throw new WebApplicationException("Type was not set on request", 422);
        }

        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);

        if(existingCardTransaction == null) {
            throw new NotFoundException();
        }

        existingCardTransaction.setCommerceName(cardTransaction.getCommerceName());
        existingCardTransaction.setAmount(cardTransaction.getAmount());
        existingCardTransaction.setCardNumber(cardTransaction.getCardNumber());
        existingCardTransaction.setCustomerId(cardTransaction.getCustomerId());
        existingCardTransaction.setAccountId(cardTransaction.getAccountId());
        existingCardTransaction.setStatus(cardTransaction.getStatus());
        existingCardTransaction.setType(cardTransaction.getType());

        return existingCardTransaction;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);
        if(existingCardTransaction == null) {
            throw new WebApplicationException("Card transaction with id " + id + " does not exist", Response.Status.NOT_FOUND);
        }
        cardTransactionRepository.delete(existingCardTransaction);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
