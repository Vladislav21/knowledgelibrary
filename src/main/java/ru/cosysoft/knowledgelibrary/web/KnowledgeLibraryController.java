package ru.cosysoft.knowledgelibrary.web;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knowledge-library")
public class KnowledgeLibraryController {

    @PostMapping("/publish")
    public String publishKnowledge() {
        return "Hello from publishKnowledge";
    }

    @GetMapping
    public String findKnowledge(
        @RequestParam(value = "tags", required = false) final Collection<String> tags,
        @RequestParam(value = "keyword", required = false) final String keyword
    ) {
        return "Hello from findKnowledge";
    }
}
