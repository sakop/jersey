package de.vogella.jersey.jaxb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("foo")
public class Foo {

    @GET
    public Viewable getFoo() {
        return new Viewable("/test", new Person("sakop", 26));
    }
}

class Person {
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
