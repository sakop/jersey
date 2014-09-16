package com.vogella.jersey.first.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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
        service = service.path("hello/to");
        System.out.println(service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class));
        System.out.println(service.accept(MediaType.TEXT_HTML).get(String.class));

        service = client.resource("http://localhost/jersey/rest/hello");
        MultivaluedMap<String, String> headers = service.accept(MediaType.TEXT_XML).head().getHeaders();
        for (String key : headers.keySet()) {
            System.out.println(key + ":" + headers.get(key));
        }

        service = client.resource("http://localhost/jersey/rest/hello");
        System.out.println(service.accept("text/xxx").get(String.class));

    }
}
