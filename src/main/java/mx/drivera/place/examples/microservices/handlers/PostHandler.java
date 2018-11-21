package mx.drivera.place.examples.microservices.handlers;

import static io.undertow.util.StatusCodes.BAD_REQUEST;
import static io.undertow.util.StatusCodes.UNPROCESSABLE_ENTITY;
import static com.jsoniter.JsonIterator.deserialize;
import static io.undertow.util.StatusCodes.CREATED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import mx.drivera.place.examples.microservices.domain.UserBuilder;

public class PostHandler implements HttpHandler {
    private static final int CORE_POOL_SIZE = 16;
    private static final int MAXIMUM_POOL_SIZE = 32;
    private static final int KEEP_ALIVE_TIME = 200;
    private static final int BLOCKING_QUEUE_CAPACITY = 32;

    private static ExecutorService EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(BLOCKING_QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        exchange.dispatch(EXECUTOR, () -> {
            /*
             * Undertow provides full support for blocking IO. It is not advisable to use
             * blocking IO in an XNIO worker thread, so you will need to make sure that the
             * request has been dispatched to a worker thread pool before attempting to read
             * or write.
             */
            exchange.startBlocking();

            final StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            UserBuilder user = null;

            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                if (stringBuilder.toString().trim().isEmpty()) {
                    exchange.setStatusCode(BAD_REQUEST).getResponseSender()
                            .send("This service accepts 'User' JSON object only.");
                    return;
                }
                user = deserialize(stringBuilder.toString(), UserBuilder.class);
                if (user.getFullName() == null || user.getFullName().isEmpty()) {
                    exchange.setStatusCode(BAD_REQUEST).getResponseSender()
                            .send("At least the user name must be provided.");
                    return;
                }
                exchange.setStatusCode(CREATED).endExchange();
            } catch (IOException | RuntimeException ex) {
                // To Do add some Log4j
                System.out.println("Can not read the body '{}'");
                ex.printStackTrace();
                exchange.setStatusCode(UNPROCESSABLE_ENTITY).getResponseSender()
                        .send("Invalid 'user' check JSON and API.");
                return;
            }
        });
    }
}