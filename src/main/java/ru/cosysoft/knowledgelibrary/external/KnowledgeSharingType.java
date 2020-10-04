package ru.cosysoft.knowledgelibrary.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KnowledgeSharingType {
    PROOF(107),
    GOOD_IDEA(106),
    OTHER(113);

    private final Integer namespaceId;
}
