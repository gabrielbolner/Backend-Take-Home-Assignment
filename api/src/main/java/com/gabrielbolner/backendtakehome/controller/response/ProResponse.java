package com.gabrielbolner.backendtakehome.controller.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ProResponse {
    private Float score;
    private String selected_project;
    private List<String> eligible_projects;
    private List<String> ineligible_projects;
}
