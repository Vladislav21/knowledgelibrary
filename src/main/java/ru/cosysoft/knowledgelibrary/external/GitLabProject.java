package ru.cosysoft.knowledgelibrary.external;

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
    private Integer namespace_id;
    private String import_url;

    public GitLabProject(final ProjectPublication projectPublication) {
        this.name = projectPublication.getName();
        this.description = projectPublication.getDescription();
        this.namespace_id = projectPublication.getNamespace().getNamespaceId();
        this.import_url = projectPublication.getImport_url();
    }
}
