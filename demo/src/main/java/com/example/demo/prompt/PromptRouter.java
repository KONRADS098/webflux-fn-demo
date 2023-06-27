package com.example.demo.prompt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PromptRouter {

    @Bean
    RouterFunction<ServerResponse> routes(PromptHandler promptHandler) {
        return RouterFunctions.route()
                .POST("/prompts", promptHandler::savePrompt)
                .GET("/prompts/{id}", promptHandler::findPromptById)
                .GET("/prompts", promptHandler::findAllPrompts)
                .DELETE("/prompts/{id}", promptHandler::deletePromptById)
                .build();
    }
}
