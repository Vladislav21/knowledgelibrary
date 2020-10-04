package ru.cosysoft.knowledgelibrary.web.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cosysoft.knowledgelibrary.external.SharingProjectType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPublication implements PojoPayload {
    private String name;
    private String description;
    @JsonProperty("import_url")
    private String importUrl;
    private SharingProjectType namespace;
}
