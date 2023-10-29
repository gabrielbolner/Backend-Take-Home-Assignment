package com.gabrielbolner.backendtakehome.domain;

import lombok.*;

@Getter
@Setter
public class Pro {
    private int age;
    private String education_level;
    private PastExperiences past_experiences;
    private InternetTest internet_test;
    private Float writing_score;
    private String referral_code;
}
