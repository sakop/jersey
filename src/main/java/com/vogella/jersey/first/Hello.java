package com.vogella.jersey.first;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.vogella.jersey.first.client.TestObj;

import de.vogella.jersey.jaxb.model.Todo;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {
    @Context
    UriInfo uri;

    // This method is called if TEXT_PLAIN is request
    //    @GET
    //    @Produces(MediaType.TEXT_PLAIN)
    //    public String sayPlainTextHello() {
    //        return "Hello Jersey";
    //    }

    // This method is called if XML is request
    @GET
    @Produces({MediaType.TEXT_XML,"text/xxx"})
    public List<String> sayXMLHello() {
        List<String> arrList = new ArrayList<String>();
        arrList.add("<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>");
        return arrList;
    }

    @Path("to")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Todo sayTodo() {
        return new Todo();
    }


    @Context
    HttpHeaders header;

    // This method is called if HTML is request
    @GET
    @Path("/test/aa/{number: \\d+}/{age}")
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello(@PathParam("number") TestObj t,@PathParam("age")double age) {
        System.out.println(uri.getPathParameters());
        System.out.println(uri.getAbsolutePath());
        System.out.println(age);

        System.out.println(header.getRequestHeaders());
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + t.getI() + "</body></h1>" + "</html> ";
    }

    @HEAD
    @Produces(MediaType.TEXT_XML)
    public String sayHead(){
        return "wqewqewq";
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response printHello(MultivaluedMap<String, String> params) {
        Response r = null;
        try {
            r = Response.created(new URI("hello")).status(302).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(uri.getAbsolutePath());
        return r;
    }

}

