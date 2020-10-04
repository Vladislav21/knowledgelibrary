package ru.cosysoft.knowledgelibrary.web.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cosysoft.knowledgelibrary.external.KnowledgeSharingType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPublication implements PojoPayload {
    private String name;
    private String description;
    private String import_url;
    private KnowledgeSharingType namespace;
}
