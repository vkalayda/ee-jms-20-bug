package com.test.rest;

import com.test.message.MessageSender11;
import com.test.message.MessageSender20;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("test")
public class TestResource {

    @Inject
    MessageSender20 sender20;

    @Inject
    MessageSender11 sender11;

    @GET
    @Path("jms20")
    public String sendJms20() {
        sender20.send();

        return "OK";
    }

    @GET
    @Path("jms11")
    public String sendJms11() {
        sender11.send();

        return "OK";
    }

}
