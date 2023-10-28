package com.gabrielbolner.backendtakehome.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Pro {
    private int age;
    private String educationLevel;
    private Map<String, Boolean> pastExperiences;
    private Map<String, Double> internetTest;
    private double writingScore;
    private String referralCode;
}
