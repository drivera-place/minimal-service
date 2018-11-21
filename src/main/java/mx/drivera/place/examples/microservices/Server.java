package mx.drivera.place.examples.microservices;

import io.undertow.util.Methods;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import mx.drivera.place.examples.microservices.handlers.GetHandler;
import mx.drivera.place.examples.microservices.handlers.PostHandler;

public class Server {

    private static String HOST = "localhost";
    private static Integer PORT = 8099;
    
    public static void main(final String[] args) {
        Undertow.builder()
                .addHttpListener(PORT, HOST)
                .setHandler(routes())
                .build()
                .start();
    }

    private static HttpHandler routes() {
        return Handlers.routing().add(Methods.GET, "/api/v1/users", new GetHandler())
                                 .add(Methods.POST, "/api/v1/users", new PostHandler());
      }
}