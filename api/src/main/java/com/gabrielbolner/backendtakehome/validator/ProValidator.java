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

        String education_level = proRequest.getEducation_level();
        if (!"no_education".equals(education_level) && !"high_school".equals(education_level) && !"bachelors_degree_or_high".equals(education_level)) {
            throw new IllegalArgumentException(INVALID_EDUCATION);
        }

        if (proRequest.getPast_experiences() == null) {
            throw new IllegalArgumentException(INVALID_PAST_EXPERIENCES);
        }

        if (proRequest.getInternet_test() == null) {
            throw new IllegalArgumentException(INVALID_INTERNET_TEST);
        }

        float writing_score = proRequest.getWriting_score();
        if (writing_score < 0 || writing_score > 1) {
            throw new IllegalArgumentException(INVALID_WRITING_SCORE);
        }

        String referral_code = proRequest.getReferral_code();
        if (referral_code != null && !referral_code.equals("token1234")) {
            throw new IllegalArgumentException(INVALID_REFERRAL_CODE);
        }
    }
}
