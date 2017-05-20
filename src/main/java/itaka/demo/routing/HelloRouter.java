package itaka.demo.routing;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by isao.takahashi on 2017/05/20.
 */
@Component
public class HelloRouter {

    private static final String PATH = "/hello";

    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET(PATH), this::hello)
                .andRoute(GET(PATH + "/{name}"), this::helloName)
                .andRoute(GET(PATH + "/hoge"), this::helloHoge);
    }

    private Mono<ServerResponse> hello(ServerRequest req) {
        return ok().body(Flux.just("Hello", "WebFlux"), String.class);
    }

    private Mono<ServerResponse> helloName(ServerRequest req) {
        return ok().body(Flux.just("Hello", req.pathVariable("name")), String.class);
    }

    private Mono<ServerResponse> helloHoge(ServerRequest req) {
        return ok().body(Flux.just("Hoge", "Hoge"), String.class);
    }
}
