package com.gabrielbolner.backendtakehome.validator;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import org.springframework.stereotype.Service;

@Service
public class ProValidator {

    public static final String INVALID_AGE = "Age must be a non-negative integer.";
    public static final String INVALID_EDUCATION = "Invalid education level.";
    public static final String INVALID_PAST_EXPERIENCES = "Invalid past experiences data.";
    public static final String INVALID_INTERNET_TEST = "Invalid internet test data.";
    public static final String INVALID_WRITING_SCORE = "Writing score must be between 0 and 1.";
    public static final String INVALID_REFERRAL_CODE = "Invalid referral code.";

    public void validate(Object target) {
        ProRequest proRequest = (ProRequest) target;

        if (proRequest.getAge() < 0) {
            throw new IllegalArgumentException(INVALID_AGE);
        }

        String educationLevel = proRequest.getEducationLevel();
        if (!"no_education".equals(educationLevel) && !"high_school".equals(educationLevel) && !"bachelors_degree_or_high".equals(educationLevel)) {
            throw new IllegalArgumentException(INVALID_EDUCATION);
        }

        if (proRequest.getPastExperiences() == null) {
            throw new IllegalArgumentException(INVALID_PAST_EXPERIENCES);
        }

        if (proRequest.getInternetTest() == null) {
            throw new IllegalArgumentException(INVALID_INTERNET_TEST);
        }

        float writingScore = proRequest.getWritingScore();
        if (writingScore < 0 || writingScore > 1) {
            throw new IllegalArgumentException(INVALID_WRITING_SCORE);
        }

        String referralCode = proRequest.getReferralCode();
        if (referralCode != null && !referralCode.equals("token1234")) {
            throw new IllegalArgumentException(INVALID_REFERRAL_CODE);
        }
    }
}
