package UI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created by Marthin on 2016-11-27.
 */
public class RestClient {
    private Client client;
    private final String URL = "http://localhost:8080";
    private String path;

    public RestClient() {
        client = Client.create();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ClientResponse get() {
        WebResource webResource = client.resource(URL + path);
        return webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    public ClientResponse post(String json){
        WebResource webResource = client.resource(URL+path);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
        return response;
    }

    public String getPath() {
        return path;
    }

    public String getURL() {
        return URL;
    }
}
