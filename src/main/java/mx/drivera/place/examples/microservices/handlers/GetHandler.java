package mx.drivera.place.examples.microservices.handlers;

import static com.jsoniter.output.JsonStream.serialize;
import static io.undertow.util.StatusCodes.OK;
import static mx.drivera.place.examples.microservices.util.ResponseHeaders.APPLICATION_JSON_UTF8;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import mx.drivera.place.examples.microservices.domain.UserBuilder;

public class GetHandler implements HttpHandler {
    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, APPLICATION_JSON_UTF8);
        exchange.setStatusCode(OK).getResponseSender()
                .send(serialize(
                    new UserBuilder()
                    .setFullName("Juan Hernandez Hernandez")
                    .setEmail("jhernandez@linko.mx")
                    .setPhone("0000000000")));
    }
}