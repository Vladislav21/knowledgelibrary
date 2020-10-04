package ru.cosysoft.knowledgelibrary.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cosysoft.knowledgelibrary.web.payload.ProjectPublication;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitLabProject implements GitLabPayload {
    private String name;
    private String description;
    @JsonProperty("namespace_id")
    private Integer namespaceId;
    @JsonProperty("import_url")
    private String importUrl;

    public static GitLabProject of(final ProjectPublication projectPublication) {
        return new GitLabProject(
            projectPublication.getName(),
            projectPublication.getDescription(),
            projectPublication.getNamespace().getNamespaceId(),
            projectPublication.getImportUrl()
        );
    }
}
