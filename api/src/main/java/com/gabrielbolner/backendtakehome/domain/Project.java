package com.gabrielbolner.backendtakehome.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Project {
    private final String name;
    private final Float score;
}
