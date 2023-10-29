package com.gabrielbolner.backendtakehome.validator;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.factories.ProFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProValidatorTest {

    private ProValidator proValidator;

    @BeforeEach
    void setUp() {
        proValidator = new ProValidator();
    }

    @Test
    void shouldThrowExceptionWhenAgeIsNegative() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setAge(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_AGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEducationLevelIsInvalid() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setEducation_level("invalid_education");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_EDUCATION, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPastExperiencesIsNull() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setPast_experiences(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_PAST_EXPERIENCES, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInternetTestIsNull() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setInternet_test(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_INTERNET_TEST, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWritingScoreIsOutOfRange() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setWriting_score(1.5f);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_WRITING_SCORE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenReferralCodeIsInvalid() {
        ProRequest proRequest = ProFactory.getProRequest();
        proRequest.setReferral_code("invalid_code");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> proValidator.validate(proRequest));
        assertEquals(ProValidator.INVALID_REFERRAL_CODE, exception.getMessage());
    }
}
