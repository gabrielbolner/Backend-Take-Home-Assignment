package com.gabrielbolner.backendtakehome.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProResponse {
    private Float score;
    private String selected_project;
    private List<String> eligible_projects;
    private List<String> ineligible_projects;
}
