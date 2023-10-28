package com.gabrielbolner.backendtakehome.controller.request;

import com.gabrielbolner.backendtakehome.domain.InternetTest;
import com.gabrielbolner.backendtakehome.domain.PastExperiences;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProRequest {
    private int age;
    private String educationLevel;
    private PastExperiences pastExperiences;
    private InternetTest internetTest;
    private Float writingScore;
    private String referralCode;
}