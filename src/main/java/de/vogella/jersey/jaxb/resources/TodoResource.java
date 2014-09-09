package de.vogella.jersey.jaxb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.vogella.jersey.jaxb.model.Todo;

public class TodoResource {

    @GET
    @Produces(MediaType.APPLICATION_XML)
  public Todo getXML(){
      Todo todo = new Todo();
      todo.setSummary("To wash clothes");
      todo.setDescription("very dirty");
      return todo;
  }
}
