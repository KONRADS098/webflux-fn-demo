package com.example.demo.prompt;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PromptService {
    Mono<PromptDto> save(PromptDto promptDto);

    Mono<PromptDto> findById(Long id);

    Flux<PromptDto> findAll();

    Mono<Void> deleteById(Long id);
}
