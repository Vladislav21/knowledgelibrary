package ru.cosysoft.knowledgelibrary.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projects implements GitLabResponse {
    private Long id;
    private String description;
    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;
    @JsonProperty("readme_url")
    private String readmeUrl;
    @JsonProperty("tag_list")
    private Collection<String> tagList;
}
