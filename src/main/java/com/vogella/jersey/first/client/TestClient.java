package com.vogella.jersey.first.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource service = client.resource("http://localhost/jersey/rest");
        service = service.path("hello");
        System.out.println(service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class));
        System.out.println(service.accept(MediaType.TEXT_XML).get(String.class));
    }
}
