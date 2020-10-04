package ru.cosysoft.knowledgelibrary.web;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/knowledge-library")
@RequiredArgsConstructor
public class KnowledgeLibraryController {

    @Value("${knowledge.storage-api-v4}")
    private String apiUrl;

    @Value("${auth.personal.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate;

    @PostMapping("/publish")
    public String publishKnowledge() {
        return "Hello from publishKnowledge";
    }

    @GetMapping
    public ResponseEntity<String> findKnowledge(
        @RequestParam(value = "tags", required = false) final Collection<String> tags,
        @RequestParam(value = "keyword", required = false) final String keyword
    ) {
        final String projectList = this.apiUrl + "projects";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", this.accessToken);
        final HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        final ResponseEntity<String> projectsListResult =
            this.restTemplate.exchange(projectList, HttpMethod.GET, httpEntity, String.class);
        return projectsListResult;
    }
}
