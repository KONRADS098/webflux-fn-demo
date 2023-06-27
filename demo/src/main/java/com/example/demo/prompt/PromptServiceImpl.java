package com.example.demo.prompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PromptServiceImpl implements PromptService {
    // for testing purposes, we will use a static list with some hardcoded prompts
    private List<PromptDto> prompts = new ArrayList<>(List.of(
        new PromptDto(1L, "Prompt 1", "Description 1", "Content 1", "Author 1", List.of("tag1", "tag2")),
        new PromptDto(2L, "Prompt 2", "Description 2", "Content 2", "Author 2", List.of("tag1", "tag2")),
        new PromptDto(3L, "Prompt 3", "Description 3", "Content 3", "Author 3", List.of("tag1", "tag2")),
        new PromptDto(4L, "Prompt 4", "Description 4", "Content 4", "Author 4", List.of("tag1", "tag2")),
        new PromptDto(5L, "Prompt 5", "Description 5", "Content 5", "Author 5", List.of("tag1", "tag2"))
    ));

    @Override
    public Mono<PromptDto> save(PromptDto promptDto) {
        this.prompts.add(promptDto);
        return Mono.just(promptDto);
    }

    @Override
    public Mono<PromptDto> findById(Long id) {
        return Mono.justOrEmpty(prompts.stream().filter(p -> p.getId().equals(id)).findFirst());
    }

    @Override
    public Flux<PromptDto> findAll() {
        return Flux.fromIterable(prompts);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        this.prompts.removeIf(p -> p.getId().equals(id));
        return Mono.empty();
    }
}