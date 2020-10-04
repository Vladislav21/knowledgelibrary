package ru.cosysoft.knowledgelibrary.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitLabTag implements GitLabResponse {
    private String name;
}
