package com.gabrielbolner.backendtakehome.controller.request;

import com.gabrielbolner.backendtakehome.domain.InternetTest;
import com.gabrielbolner.backendtakehome.domain.PastExperiences;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProRequest {
    private int age;
    private String education_level;
    private PastExperiences past_experiences;
    private InternetTest internet_test;
    private Float writing_score;
    private String referral_code;
}