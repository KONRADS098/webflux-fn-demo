package com.example.demo.prompt;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromptDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private String author;
    private List<String> tags;
}
