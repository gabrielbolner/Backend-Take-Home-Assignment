package com.gabrielbolner.backendtakehome.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Pro {
    private int age;
    private String education_level;
    private Map<String, Boolean> past_experiences;
    private Map<String, Float> internet_test;
    private Float writing_score;
    private String referral_code;
}
