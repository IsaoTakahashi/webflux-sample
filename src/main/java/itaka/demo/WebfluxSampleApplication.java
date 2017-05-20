package itaka.demo;

import itaka.demo.routing.HelloRouter;
import itaka.demo.routing.StreamRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WebfluxSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxSampleApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routes(HelloRouter helloRouter, StreamRouter streamRouter) {
        return helloRouter.route()
                .and(streamRouter.route());
    }
}
