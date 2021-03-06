package cn.wolfcode.rbac.domain;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Permission {
    private Long id;

    private String name;

    private String expression;

}