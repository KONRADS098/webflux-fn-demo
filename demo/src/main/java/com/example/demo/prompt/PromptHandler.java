package com.example.demo.prompt;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PromptHandler {
    private final PromptService promptService;

    public Mono<ServerResponse> savePrompt(ServerRequest serverRequest) {
        Mono<PromptDto> promptDtoMono = serverRequest.bodyToMono(PromptDto.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return promptDtoMono.flatMap(promptDto -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(promptService.save(promptDto), PromptDto.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> findPromptById(ServerRequest serverRequest) {
        Long promptId = Long.valueOf(serverRequest.pathVariable("id"));
        Mono<PromptDto> promptDtoMono = promptService.findById(promptId);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return promptDtoMono.flatMap(promptDto -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(promptDto))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> findAllPrompts(ServerRequest serverRequest) {
        Flux<PromptDto> allPrompts = promptService.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(allPrompts, PromptDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deletePromptById(ServerRequest serverRequest) {
        Long promptId = Long.valueOf(serverRequest.pathVariable("id"));
        Mono<Void> deletePrompt = promptService.deleteById(promptId);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return deletePrompt.then(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                // how can you make the body empty?
                .bodyValue(void.class))
                .switchIfEmpty(notFound);
    }
}
