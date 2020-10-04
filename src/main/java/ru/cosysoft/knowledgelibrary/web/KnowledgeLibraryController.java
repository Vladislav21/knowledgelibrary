package ru.cosysoft.knowledgelibrary.web;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.cosysoft.knowledgelibrary.external.GitLabResponse;
import ru.cosysoft.knowledgelibrary.external.Projects;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Projects> findKnowledge(
        @RequestParam(value = "tags", required = false) final Collection<String> tags,
        @RequestParam(value = "keyword", required = false) final String keyword
    ) {
        final String projectList = this.apiUrl + "projects";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", this.accessToken);
        final HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);

        final ParameterizedTypeReference<Collection<Projects>> parameterizedTypeReference =
            new ParameterizedTypeReference<Collection<Projects>>() {
            };
        final ResponseEntity<Collection<Projects>> projectsListResult =
            this.restTemplate.exchange(projectList, HttpMethod.GET, httpEntity, parameterizedTypeReference);
        final Collection<Projects> projects = projectsListResult.getBody();

        if (CollectionUtils.isEmpty(projects)) {
            return projects;
        }
        final List<Projects> projectsWithKnowledgeSharing =
            projects.stream()
                .filter(project -> project.getHttp_url_to_repo().contains("knowledge-sharing"))
                .filter(project -> this.isValidReadme(project, keyword))
                .collect(Collectors.toList());

        return projectsWithKnowledgeSharing;
    }

    @SneakyThrows
    private boolean isValidReadme(final Projects projects, final String keyword) {
        if (StringUtils.isEmpty(keyword)) return true;
        final String projectList = this.apiUrl + "projects/" + projects.getId() + "/repository/files/README.md?ref=master";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", this.accessToken);
        final HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        final ResponseEntity<GitLabFile> projectsListResult =
            this.restTemplate.exchange(projectList, HttpMethod.GET, httpEntity, GitLabFile.class);
        final GitLabFile gitLabFile = projectsListResult.getBody();
        if (gitLabFile == null) {
            return false;
        }
        final byte[] content = Base64.getDecoder().decode(gitLabFile.getContent());
        final String contentAsStr = new String(content, StandardCharsets.UTF_8);
        return contentAsStr.contains(keyword);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class GitLabFile implements GitLabResponse {
        private String file_name;
        private String encoding;
        private String content;
    }
}
