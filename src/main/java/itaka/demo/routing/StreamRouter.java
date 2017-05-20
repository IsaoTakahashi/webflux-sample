package itaka.demo.routing;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by isao.takahashi on 2017/05/20.
 */
@Component
public class StreamRouter {

    private static final String PATH = "/stream";

    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET(PATH), this::infiniteStream);
    }

    private Mono<ServerResponse> infiniteStream(ServerRequest req) {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        Flux<Integer> flux = Flux.fromStream(stream).delayElements(Duration.ofSeconds(1));

        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Integer.class);
    }
}
